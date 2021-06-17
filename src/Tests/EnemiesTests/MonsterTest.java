package Tests.EnemiesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    Monster m;
    Player p;
    @BeforeEach
    void setUp() {
        m = new Monster('k', "Lannister Knight", 200, 14, 8, 50,   6);
        m.initialize(new Position(5,1), (msg) -> onMessageCallback(msg));
        m.setDeathCallback(() -> onEnemyDeathCallback(m));
        m.setPositionCallback((pos) -> onPositionCallback(m, pos));
        p = new Warrior("test Player", 1000,51,6,3);
        p.initialize(new Position(3,2), (msg) -> onMessageCallback(msg), () -> onPlayerDeathCallback(), (pos) -> onPositionCallback(m, pos));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void performAction(){
        m.performAction(p, null);
        assertEquals(new Position(4,1),m.getPosition(), "enemy didn't track player");// move down
        m.performAction(p, null);
        assertEquals(new Position(4,2),m.getPosition(), "enemy didn't track player");// move up
        m.initialize(new Position(7,8), (msg) -> onMessageCallback(msg));
        m.setDeathCallback(() -> onEnemyDeathCallback(m));
        m.setPositionCallback((pos) -> onPositionCallback(m, pos));
        m.performAction(p, null);
        assertEquals(new Position(7,8),m.getPosition(), "enemy didn't track player");// random, stay
    }


    void onMessageCallback(String msg) {
    }

    void onPlayerDeathCallback() {

    }

    void onEnemyDeathCallback(Enemy e) {
    }

    void onPositionCallback(Monster m, Position p) {
        m.interact(new Empty(p));
    }
}