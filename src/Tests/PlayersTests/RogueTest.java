package Tests.PlayersTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Mage;
import BusinessLayer.Players.Rogue;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {
    private Rogue r1;
    private Monster m1;
    private Monster m2;
    private Monster m3;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        r1 = new Rogue("Jon Snow", 500, 30, 4, 5);
        r1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        m1= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m1.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        m2= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m2.initialize(new Position(0,1), (msg) -> onMessageCallback(msg));
        m3= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m3.initialize(new Position(0,5), (msg) -> onMessageCallback(msg));
        enemies= Arrays.asList(m1,m2,m3);
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void castAbility() {
        r1.castAbility(r1,enemies);
        assertEquals(52,enemies.get(0).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(52,enemies.get(1).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(2).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(95,r1.getEnergy().getResourceAmount(),"Rogue's energy amount is not correct");

    }

    @Test
    void levelUp() {
        r1.levelUp();
        assertEquals(2,r1.getLevel(),"rouge level is not correct");
        assertEquals(520,r1.getHealth().getResourceAmount(),"rouge health is not correct");
        assertEquals(44,r1.getAttack(),"rouge attack points is not correct");
        assertEquals(6,r1.getDefense(),"rouge defense points not correct");
        assertEquals(100,r1.getEnergy().getResourceAmount(),"rouge energy points not correct");
    }


    @Test
    void gainAttack() {
        r1.levelUp();
        assertEquals(44,r1.getAttack(),"rouge attack points is not correct");
    }

    void onMessageCallback(String msg){
        //System.out.println(msg);
    }

    void onDeathCallback(){
    }

    void onPositionCallback(Position position){
    }
}