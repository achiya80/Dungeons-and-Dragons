package Tests.PlayersTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Hunter;
import BusinessLayer.Players.Mage;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    private Mage mage1;
    private Mage mage2;
    private Monster m1;
    private Monster m2;
    private Monster m3;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        mage1 = new Mage("Jon Snow", 500, 30, 4, 5,2,3,2,3);
        mage1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        mage2 = new Mage("Jon Snow", 500, 30, 4, 20,2,3,2,3);
        mage2.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        m1= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m1.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        m2= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m2.initialize(new Position(0,2), (msg) -> onMessageCallback(msg));
        m3= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m3.initialize(new Position(0,5), (msg) -> onMessageCallback(msg));
        enemies= Arrays.asList(m1,m2,m3);
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void onPlayerTurn() {
    }

    @Test
    void castAbility1() {
        mage1.castAbility(mage1,enemies);
        assertEquals(80,enemies.get(0).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(1).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(2).getHealth().getResourceAmount(),"Health amount is not correct");
    }
    @Test
    void castAbility2() {
        mage2.castAbility(mage2,enemies);
        assertEquals(80,enemies.get(0).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(78,enemies.get(1).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(2).getHealth().getResourceAmount(),"Health amount is not correct");
    }

    @Test
    void levelUp() {
        mage1.levelUp();
        assertEquals(2,mage1.getLevel(),"hunter level is not correct");
        assertEquals(510,mage1.getHealth().getResourceAmount(),"hunter health is not correct");
        assertEquals(34,mage1.getAttack(),"hunter attack points is not correct");
        assertEquals(5,mage1.getDefense(),"hunter defense points not correct");
        assertEquals(30,mage1.getMana().getResourcePool(),"hunter defense points not correct");
        assertEquals(12,mage1.getMana().getResourceAmount(),"hunter defense points not correct");///////check this
        assertEquals(13,mage1.getMana().getSpellPower(),"hunter defense points not correct");


    }

    @Test
    void gainSpellPower() {
        mage1.levelUp();

        assertEquals(13,mage1.getMana().getSpellPower(),"hunter defense points not correct");
    }

    @Test
    void gainManaPool() {
        mage1.levelUp();
        assertEquals(30,mage1.getMana().getResourcePool(),"hunter defense points not correct");
    }

    @Test
    void describe() {
    }
    void onMessageCallback(String msg){
        //System.out.println(msg);
    }


    void onDeathCallback(){

    }

    void onPositionCallback(Position position){

    }
}