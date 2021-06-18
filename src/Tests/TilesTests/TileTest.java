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
import BusinessLayer.Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    private Tile t1;
    private Tile t2;
    private Enemy monster;
    private Enemy trap;
    private Player p;


    @BeforeEach
    void setUp(){
        t1 = new Empty(new Position(1,1));
        t2 = new Wall(new Position(0,0));
        monster = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        monster.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        trap.initialize(new Position(2,1), (msg) -> onMessageCallback(msg));
        trap.setDeathCallback(() -> onDeathCallback());
        trap.setPositionCallback((pos) -> onPositionCallback(pos));
        p = new Warrior("Jon Snow", 500, 30, 4, 3);
        p.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));

        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void compareTo() {
        assertEquals(1, t1.compareTo(p),"compare to was not as expected");
    }

    @Test
    void playerVisitEmpty() {
        p.interact(t1);
        assertEquals(-1, t1.compareTo(p),"compare to was not as expected, player and empty tile didn't switch positions");
    }

    @Test
    void trapVisitEmpty() {
        trap.interact(t1);
        assertEquals(new Position(2,1), trap.getPosition(),"compare to was not as expected, trap and empty switch positions");
        assertEquals(new Position(1,1), t1.getPosition(),"compare to was not as expected, trap and empty tile switch positions");
    }

    @Test
    void MonsterVisitWall() {
        monster.interact(t2);
        assertEquals(new Position(0,1), monster.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");
        assertEquals(new Position(0,0), t2.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");
    }

    @Test
    void playerVisitEnemy() {
        p.interact(monster);
        assertEquals(monster.getHealth().getResourcePool() - p.getAttack() + monster.getDefense(), monster.getHealth().getResourceAmount(), "life of enemy was not reduce");
        assertEquals(new Position(0,1), monster.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");
        assertEquals(new Position(1,0), p.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");
    }

    @Test
    void enemyVisitPlayer() {
        monster.interact(p);
        assertEquals(p.getHealth().getResourcePool() - monster.getAttack() + p.getDefense(), p.getHealth().getResourceAmount(), "life of player was not reduce");
        assertEquals(new Position(0,1), monster.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");
        assertEquals(new Position(1,0), p.getPosition(),"compare to was not as expected, enemy didn't stay at the same position");

    }

    @Test
    void testToString() {
        assertEquals(".",t1.toString(),"tile was not as expected");

        assertEquals("#",t2.toString(),"tile was not as expected");

        assertEquals("s",monster.toString(),"tile was not as expected");

        assertEquals("B",trap.toString(),"tile was not as expected");

        trap.performAction(p, null);

        assertEquals("B",trap.toString(),"tile was not as expected");

        trap.performAction(p, null);

        assertEquals(".",trap.toString(),"tile was not as expected");

        assertEquals("@",p.toString(),"tile was not as expected");


    }

    void onMessageCallback(String msg){
    }


    void onDeathCallback(){

    }

    void onPositionCallback(Position position){

    }

}