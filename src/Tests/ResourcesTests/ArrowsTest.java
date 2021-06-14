package Tests.ResourcesTests;

import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Arrows;
import PresentationLayer.FileHandler.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrowsTest {


    Arrows a;
    @BeforeEach
    void setUp() {
        a = new Arrows(5, 2);
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
        List<Enemy> e = a.filterRange(new Position(2,4),enemies);
        assertEquals(1, e.size(), "didn't filter right");
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
    void uponLevelingUp() {
        int level = 2;
        a.uponLevelingUp(2);
        assertEquals(25, a.getResourceAmount(), "didn't add amount");
    }

    @Test
    void onGameTick() {
        for(int i = 0;i < 10;i++){
            a.onGameTick(1);
            assertEquals(5, a.getResourceAmount(), "shouldn't add amount");
        }
        a.onGameTick(1);
        assertEquals(6, a.getResourceAmount(), "didn't add amount");
    }


    void onMSG(String msg){

    }

}