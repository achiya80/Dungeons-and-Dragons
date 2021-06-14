package Tests.ResourcesTests;

import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.AbilityResource;
import BusinessLayer.Resources.Energy;
import PresentationLayer.FileHandler.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbilityResourceTest {

    AbilityResource a;

    @BeforeEach
    void setUp() {
        a = new Energy(20);
    }

    @Test
    void filterRangeAll() {
        TileFactory t = new TileFactory();
        List<Enemy> enemies = Arrays.asList(t.produceEnemy('s',new Position(1,2), (msg) -> onMSG(msg))
        ,t.produceEnemy('s',new Position(2,2), (msg) -> onMSG(msg))
        ,t.produceEnemy('s',new Position(3,2), (msg) -> onMSG(msg)));
        List<Enemy> e = a.filterRange(new Position(2,1),enemies);
        assertEquals(3, e.size(), "didn't filter right");
    }

    @Test
    void filterRangeSome() {
        TileFactory t = new TileFactory();
        List<Enemy> enemies = Arrays.asList(t.produceEnemy('s',new Position(1,2), (msg) -> onMSG(msg))
                ,t.produceEnemy('s',new Position(2,2), (msg) -> onMSG(msg))
                ,t.produceEnemy('s',new Position(3,2), (msg) -> onMSG(msg)));
        List<Enemy> e = a.filterRange(new Position(1,1),enemies);
        assertEquals(2, e.size(), "didn't filter right");
    }

    @Test
    void filterRangeNone() {
        TileFactory t = new TileFactory();
        List<Enemy> enemies = Arrays.asList(t.produceEnemy('s',new Position(1,2), (msg) -> onMSG(msg))
                ,t.produceEnemy('s',new Position(2,2), (msg) -> onMSG(msg))
                ,t.produceEnemy('s',new Position(3,2), (msg) -> onMSG(msg)));
        List<Enemy> e = a.filterRange(new Position(4,4),enemies);
        assertEquals(0, e.size(), "didn't filter right");
    }

    @Test
    void isAbleToCast() {
        assertEquals(true, a.isAbleToCast(),"can cast");
        a.reduceAmount(81);
        assertEquals(false, a.isAbleToCast(),"can't cast");
    }

    @Test
    void onAbilityCast() {
        a.onAbilityCast();
        assertEquals(80, a.getResourceAmount(), "didn't reduce resource");
    }

    void onMSG(String msg){

    }
}