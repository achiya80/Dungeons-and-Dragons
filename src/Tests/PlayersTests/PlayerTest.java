package Tests.PlayersTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Mage;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;
    private Enemy enemy1;
    private Enemy enemy2;
    private Wall wall;
    private Empty empty;

    @BeforeEach
    void setUp() {

        p1 = new Mage("Jon Snow", 500, 30, 4, 20,2,3,2,3);
        p1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        p2 = new Mage("Jon Snow", 8, 30, 4, 20,2,3,2,3);
        p2.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        enemy1= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        enemy1.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        enemy2= new Monster('s', "Lannister Solider", 10, 8, 3,25, 3);
        enemy2.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        wall = new Wall(new Position(1,1));
        empty = new Empty(new Position(1,1));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void PlayerVisitEmpty(){
        p1.interact(empty);
        assertEquals(new Position(1,0), empty.getPosition(), "Empty position should change");
        assertEquals(new Position(1,1), p1.getPosition(), "Player position should change");
    }
    @Test
    void PlayerVisitWall() {
       p1.interact(wall);
        assertEquals(new Position(1,1), wall.getPosition(), "Wall position should not change");
        assertEquals(new Position(1,0), p1.getPosition(), "Player position should not change");
    }

    @Test
    void PlayerVisitMonster(){
        p1.interact(enemy1);
        assertEquals(53, enemy1.getHealth().getResourceAmount(), "Enemy life was not reduced");
    }

    @Test
    void abilityDamage() {
        enemy1.setDeathCallback(()->onDeathCallback());
        enemy1.setPositionCallback((pos)->onPositionCallback(pos));
        p1.interact(enemy1);
        assertEquals(53 ,enemy1.getHealth().getResourceAmount(), "Enemy health score is not correct");
        p1.interact(enemy1);
        p1.interact(enemy1);
        assertEquals(false ,enemy1.alive(), "Enemy should be dead");
    }

    @Test
    void onKill() {
        enemy2.setDeathCallback(()->onDeathCallback());
        enemy2.setPositionCallback((pos)->onPositionCallback(pos));
        p1.interact(enemy2);
        assertEquals(25 ,p1.getExperience(), "Enemy should be dead");
    }

    @Test
    void onDeath() {
        enemy1.interact(p2);
        assertEquals(4 ,p2.getHealth().getResourceAmount(), "player health score is not correct");
        enemy1.interact(p2);
        assertEquals(0 ,p2.getHealth().getResourceAmount(), "player should be dead");
        assertEquals(false ,p2.alive(), "player should be dead");
    }

    void onMessageCallback(String msg){
    }

    void onDeathCallback(){
    }

    void onPositionCallback(Position position){
    }

    void onEnemyDeathCallback(Enemy e){ }



}