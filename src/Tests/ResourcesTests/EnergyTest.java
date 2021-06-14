package Tests.ResourcesTests;

import BusinessLayer.Resources.Energy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyTest {

    Energy e;
    @BeforeEach
    void setUp() {
        e = new Energy(20);
    }

    @Test
    void onGameTick() {
        e.reduceAmount(50);
        e.onGameTick();
        assertEquals(60, e.getResourceAmount(), "energy was not updated");
        e.onGameTick();
        assertEquals(70, e.getResourceAmount(), "energy was not updated");
        e.onGameTick();
        assertEquals(80, e.getResourceAmount(), "energy was not updated");
        e.onGameTick();
        assertEquals(90, e.getResourceAmount(), "energy was not updated");
        e.onGameTick();
        assertEquals(100, e.getResourceAmount(), "energy was not updated");
        e.onGameTick();
        assertEquals(100, e.getResourceAmount(), "energy was updated, but it's maxed out");
    }
}