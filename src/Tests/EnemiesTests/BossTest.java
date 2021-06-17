package Tests.EnemiesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Boss;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {

    Boss b1;
    Boss b2;
    Boss b3;
    Player p;
    List<Enemy> enemies;
    boolean isPlayerDead;


    @BeforeEach
    void setUp() {
        b1 = new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3);
        b1.initialize(new Position(2,1), (msg) -> onMessageCallback(msg));
        b1.setDeathCallback(() -> onEnemyDeathCallback(b1));
        b1.setPositionCallback((pos) -> onPositionCallback(pos));
        b2 = new Boss('M', "The Mountain", 1000, 60, 25,  500, 6, 5);
        b2.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        b2.setDeathCallback(() -> onEnemyDeathCallback(b2));
        b2.setPositionCallback((pos) -> onPositionCallback(pos));
        b3 = new Boss('C', "Queen Cersei", 100, 10, 1,1000, 1, 8);
        b3.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        b3.setDeathCallback(() -> onEnemyDeathCallback(b3));
        b3.setPositionCallback((pos) -> onPositionCallback(pos));
        p = new Warrior("test Player", 500,51,6,3);
        p.initialize(new Position(1,2), (msg) -> onMessageCallback(msg), () -> onPlayerDeathCallback(), (pos) -> onMessageCallback(pos.toString()));
        enemies = Arrays.asList(b1,b2,b3);
        isPlayerDead = false;
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void castAbilityWork() {
        b1.castAbility(p, enemies);
        assertEquals(205, p.getHealth().getResourceAmount(), "player life was not reduced");
    }
    @Test
    void castAbilityKills() {
        p.initialize(new Position(15,15), (msg) -> onMessageCallback(msg),() -> onPlayerDeathCallback(), (pos) -> onMessageCallback(pos.toString()));
        b1.castAbility(p, enemies);
        b1.castAbility(p, enemies);
        assertEquals(0, p.getHealth().getResourceAmount(), "player life was not reduced");
        assertEquals(true, isPlayerDead, "player is not dead");
    }

    @Test
    void onEnemyDeath(){
        p.initialize(new Position(0,0),  (msg) -> onMessageCallback(msg), () -> onPlayerDeathCallback(), (pos) -> onMessageCallback(pos.toString()));
        p.castAbility(p, enemies);
        assertEquals(50 ,b3.getHealth().getResourceAmount(), "enemy life was not reduced as planned");
        p.interact(b3);
        assertEquals(0, b3.getHealth().getResourceAmount(), "enemy is not dead");
        assertEquals(false, enemies.contains(b3), "enemy shouldn't be in enemies list after death");
    }

    @Test
    void performAction(){
        b1.performAction(p, enemies);
        assertEquals(500, p.getHealth().getResourceAmount(),"player life was reduced");
        b1.performAction(p, enemies);
        assertEquals(500, p.getHealth().getResourceAmount(),"player life was reduced");
        b1.performAction(p, enemies);
        assertEquals(500, p.getHealth().getResourceAmount(),"player life was reduced");
        b1.performAction(p, enemies);
        assertEquals(205, p.getHealth().getResourceAmount(),"player life was not reduced");
    }

    void onMessageCallback(String msg){
    }

    void onPlayerDeathCallback(){
        isPlayerDead = true;
    }

    void onEnemyDeathCallback(Enemy e){ enemies = enemies.stream().filter(enemy -> enemy != e).collect(Collectors.toList()); }

    void onPositionCallback(Position p){

    }


}