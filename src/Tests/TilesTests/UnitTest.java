package Tests.TilesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Enemies.Trap;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    private Tile t1;
    private Enemy monster1;
    private Player p1;
    private Enemy monster2;
    private Player p2;
    private Enemy trap;
    @BeforeEach
    void setUp() {
        t1 = new Empty(new Position(1,1));
        monster1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        monster1.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        p1 = new Warrior("Jon Snow", 500, 30, 4, 3);
        p1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        monster2 = new Monster('s', "Lannister Solider", 80, 21, 3,25, 3);
        monster2.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        p2 = new Warrior("Jon Snow", 40, 30, 1, 3);
        p2.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        trap.initialize(new Position(2,1), (msg) -> onMessageCallback(msg));
        trap.setDeathCallback(() -> onDeathCallback());
        trap.setPositionCallback((pos) -> onPositionCallback(pos));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void onDeathEnemy() {
        monster1.setDeathCallback(() -> onDeathCallback());
        p1.interact(monster1);
        p1.interact(monster1);
        p1.interact(monster1);
        assertEquals(false,monster1.alive() ,"Monster should be dead");
    }
    @Test
    void onDeathPlayer() {
        monster2.interact(p2);
        monster2.interact(p2);
        assertEquals(false,p2.alive() ,"player should be dead");
    }


    @Test
    void PlayerInteractEnemy() {
        p1.interact(monster1);
        assertEquals( 53,monster1.getHealth().getResourceAmount() ,"Helth score is not corect");
    }
    @Test
    void EnemyInteractPlayer() {
        monster1.interact(p1);
        assertEquals( 496,p1.getHealth().getResourceAmount() ,"Helth score is not corect");
    }

    void onMessageCallback(String msg){
    }


    void onDeathCallback(){

    }

    void onPositionCallback(Position position){

    }
}
