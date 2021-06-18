package Tests.PlayersTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private Warrior w1;
    private Monster m1;
    private Monster m2;
    private Monster m3;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        w1 = new Warrior("Jon Snow", 500, 30, 4, 3);
        w1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        m1= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m1.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        m2= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m2.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        m3= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m3.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        enemies= Arrays.asList(m1,m2,m3);
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void castAbility() {
        w1.castAbility(w1,enemies);
        assertEquals(80+2-w1.getHealth().getResourceAmount()*0.1,enemies.get(2).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(1).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(0).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(3,w1.getCooldown().getResourceAmount(),"CoolDon amount is not correct");
        assertEquals(500,w1.getHealth().getResourceAmount(),"Health amount is not correct");


    }
    @Test
    void castAbilityCoolDown() {
        w1.castAbility(w1,enemies);
        w1.castAbility(w1,enemies);
        assertEquals(80+2-w1.getHealth().getResourceAmount()*0.1,enemies.get(2).getHealth().getResourceAmount(),"warrior could not have done another castAbility");
    }
    @Test
    void levelUp() {
        w1.levelUp();
        assertEquals(2,w1.getLevel(),"warrior level is not correct");
        assertEquals(530,w1.getHealth().getResourceAmount(),"warrior health is not correct");
        assertEquals(42,w1.getAttack(),"warrior attack points is not correct");
        assertEquals(8,w1.getDefense(),"warrior defense points not correct");
    }
    @Test
    void levelUpEnable() {
        w1.castAbility(w1,enemies);
        assertEquals(1,w1.getLevel(),"warrior level is not correct");
    }

    @Test
    void gainHealth() {
        w1.levelUp();
        assertEquals(530,w1.getHealth().getResourceAmount(),"warrior health is not correct");
    }

    @Test
    void gainAttack() {
        w1.levelUp();
        assertEquals(42,w1.getAttack(),"warrior attack points is not correct");
    }

    @Test
    void gainDefense() {
        w1.levelUp();
        assertEquals(8,w1.getDefense(),"warrior defense points not correct");
    }

    void onMessageCallback(String msg){
    }


    void onDeathCallback(){

    }

    void onPositionCallback(Position position){

    }
}