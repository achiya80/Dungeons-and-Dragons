package Tests.EnemiesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Trap;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {


    Trap t;
    Player p;

    @BeforeEach
    void setUp() {
        t = new Trap('Q', "Queen's Trap", 250, 51, 10, 100, 1, 2);
        t.initialize(new Position(5, 5), (msg) -> onMessageCallback(msg));
        t.setDeathCallback(() -> onEnemyDeathCallback(t));
        t.setPositionCallback((pos) -> onPositionCallback(pos));
        p = new Warrior("test Player", 1000,51,6,3);
        p.initialize(new Position(1,2), (msg) -> onMessageCallback(msg), () -> onPlayerDeathCallback(), (pos) -> onMessageCallback(pos.toString()));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }
    @Test
    void performActionHitPlayer() {
        t.initialize(new Position(2,2), (msg) -> onMessageCallback(msg));
        t.performAction(p, null);
        assertEquals(new Position(2,2), t.getPosition(), "trap should not move");
        assertEquals(new Position(1,2), p.getPosition(), "player should not move");
        assertEquals(955, p.getHealth().getResourceAmount(), "player life were not reduced");
    }

    @Test
    void testToString() {
        assertEquals("Q",t.toString(), "trap is visible now");
        t.performAction(p, null);
        assertEquals("Q",t.toString(), "trap is visible now");
        t.performAction(p, null);
        assertEquals(".",t.toString(), "trap is not visible now");
        t.performAction(p, null);
        assertEquals(".",t.toString(), "trap is not visible now");
        t.performAction(p, null);
        assertEquals(".",t.toString(), "trap is not visible now");
        t.performAction(p, null);
        assertEquals("Q",t.toString(), "trap is visible now");

    }



    void onMessageCallback(String msg) {
    }

    void onPlayerDeathCallback() {

    }

    void onEnemyDeathCallback(Enemy e) {
    }

    void onPositionCallback(Position p) {

    }
}