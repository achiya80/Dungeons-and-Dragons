package Tests.EnemiesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Boss;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Enemies.Trap;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {


    Enemy e1;
    Enemy e2;
    Enemy e3;
    Enemy e4;
    Enemy e5;
    Player p;
    Wall w;
    Empty e;
    List<Enemy> enemies;
    boolean isPlayerDead;

    @BeforeEach
    void setUp() {
        e1 = new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3);
        e1.initialize(new Position(2,1), (msg) -> onMessageCallback(msg));
        e1.setDeathCallback(() -> onEnemyDeathCallback(e1));
        e1.setPositionCallback((pos) -> onPositionCallback(e1, pos));
        e2 = new Boss('M', "The Mountain", 1000, 60, 25,  500, 6, 5);
        e2.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        e2.setDeathCallback(() -> onEnemyDeathCallback(e2));
        e2.setPositionCallback((pos) -> onPositionCallback(e2, pos));
        e3 = new Boss('C', "Queen Cersei", 100, 10, 1,1000, 1, 8);
        e3.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        e3.setDeathCallback(() -> onEnemyDeathCallback(e3));
        e3.setPositionCallback((pos) -> onPositionCallback(e3, pos));
        e4 = new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4);
        e4.initialize(new Position(5,1), (msg) -> onMessageCallback(msg));
        e4.setDeathCallback(() -> onEnemyDeathCallback(e4));
        e4.setPositionCallback((pos) -> onPositionCallback(e4, pos));
        e5 = new Trap('Q', "Queen's Trap", 250, 51, 10, 100, 3, 10);
        e5.initialize(new Position(5,5), (msg) -> onMessageCallback(msg));
        e5.setDeathCallback(() -> onEnemyDeathCallback(e5));
        e5.setPositionCallback((pos) -> onPositionCallback(e5, pos));
        p = new Warrior("test Player", 1000,51,6,3);
        p.initialize(new Position(1,2), (msg) -> onMessageCallback(msg), () -> onPlayerDeathCallback(), (pos) -> onMessageCallback(pos.toString()));
        enemies = Arrays.asList(e1,e2,e3,e4,e5);
        isPlayerDead = false;
        w = new Wall(new Position(9,9));
        e = new Empty(new Position(9,9));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void performActionMoveRandom() {
        e4.performAction(p, enemies);
        assertEquals(1000, p.getHealth().getResourceAmount(),"enemy hit player but player wasn't on his vision range");
        assertEquals(new Position(5, 1), e4.getPosition(), "enemy moved after action but he should stay in the same position");
    }

    @Test
    void performActionTrackPlayer() {
        e4.initialize(new Position(2,4), (msg) -> onMessageCallback(msg));
        e4.performAction(p, enemies);
        assertEquals(1000, p.getHealth().getResourceAmount(),"enemy hot player but player wasn't on his vision range");
        assertEquals(new Position(2,3), e4.getPosition(), "enemy should move up");
    }

    @Test
    void performActionHitPlayer() {
        e4.initialize(new Position(2,2), (msg) -> onMessageCallback(msg));
        e4.performAction(p, enemies);
        assertEquals(992, p.getHealth().getResourceAmount(),"enemy should have hit player because player is next to him");
    }

    @Test
    void onDeath() {
        p.interact(e3);
        assertEquals(50 ,e3.getHealth().getResourceAmount(), "enemy life was not reduced as planned");
        p.interact(e3);
        assertEquals(0, e3.getHealth().getResourceAmount(), "enemy is not dead");
        assertEquals(false, enemies.contains(e3), "enemy shouldn't be in enemies list after death");
    }


    @Test
    void EnemyVisitWall(){
        e1.interact(w);
        assertEquals(new Position(9,9), w.getPosition(), "wall position should not change");
        assertEquals(new Position(2,1), e1.getPosition(), "enemy position should not change");
    }
    @Test
    void EnemyVisitEmpty(){
        e1.interact(e);
        assertEquals(new Position(2,1), e.getPosition(), "empty position should change");
        assertEquals(new Position(9,9), e1.getPosition(), "enemy position should change");
    }
    @Test
    void TrapVisitPlayer() {
        e5.interact(p);
        assertEquals(955, p.getHealth().getResourceAmount(), "player life was not reduced");
    }

    @Test
    void MonsterVisitPlayer(){
        e4.interact(p);
        assertEquals(992, p.getHealth().getResourceAmount(), "player life was not reduced");
    }



    void onMessageCallback(String msg){
    }

    void onPlayerDeathCallback(){
        isPlayerDead = true;
    }

    void onEnemyDeathCallback(Enemy e){ enemies = enemies.stream().filter(enemy -> enemy != e).collect(Collectors.toList()); }

    void onPositionCallback(Enemy e, Position p){
        e.interact((this.p.getPosition().equals(p)) ? this.p : new Empty(p));
    }
}