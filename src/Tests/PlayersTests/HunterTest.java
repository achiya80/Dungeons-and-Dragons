package Tests.PlayersTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Monster;
import BusinessLayer.Players.Hunter;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    private Hunter h1;
    private Monster m1;
    private Monster m2;
    private Monster m3;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        h1 = new Hunter("Jon Snow", 500, 30, 4, 3);
        h1.initialize(new Position( 1,0), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        m1= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m1.initialize(new Position(1,1), (msg) -> onMessageCallback(msg));
        m2= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m2.initialize(new Position(0,3), (msg) -> onMessageCallback(msg));
        m3= new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m3.initialize(new Position(0,5), (msg) -> onMessageCallback(msg));
        enemies= Arrays.asList(m1,m2,m3);
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
    }

    @Test
    void castAbility() {
        h1.castAbility(h1,enemies);
        assertEquals(52,enemies.get(0).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(1).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(80,enemies.get(2).getHealth().getResourceAmount(),"Health amount is not correct");
        assertEquals(9,h1.getArrows().getResourceAmount(),"Health amount is not correct");
    }
    @Test
    void castAbilityRowsAmount() {
        h1.getArrows().reduceAmount(9);
        h1.castAbility(h1,enemies);
        assertEquals(52,enemies.get(0).getHealth().getResourceAmount(),"hunter could not have done another castAbility");
        h1.castAbility(h1,enemies);
        assertEquals(52,enemies.get(0).getHealth().getResourceAmount(),"hunter could not have done another castAbility");
    }

    @Test
    void levelUp() {
        h1.levelUp();
        assertEquals(2,h1.getLevel(),"hunter level is not correct");
        assertEquals(520,h1.getHealth().getResourceAmount(),"hunter health is not correct");
        assertEquals(42,h1.getAttack(),"hunter attack points is not correct");
        assertEquals(8,h1.getDefense(),"hunter defense points not correct");
        assertEquals(30,h1.getArrows().getResourceAmount(),"hunter arrows amount points not correct");
    }

    @Test
    void levelUpEnable() {
        h1.castAbility(h1,enemies);
        assertEquals(1,h1.getLevel(),"hunter level is not correct");
    }


    @Test
    void gainAttack() {
        h1.levelUp();
        assertEquals(42,h1.getAttack(),"hunter attack points is not correct");
    }

    @Test
    void gainDefense() {
        h1.levelUp();
        assertEquals(8,h1.getDefense(),"hunter defense points not correct");
    }

    void onMessageCallback(String msg){
    }

    void onDeathCallback(){
    }

    void onPositionCallback(Position position){
    }
}