package Tests.ResourcesTests;

import BusinessLayer.Resources.Cooldown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CooldownTest {


    Cooldown c;
    @BeforeEach
    void setUp() {
        c = new Cooldown(2);
    }

    @Test
    void uponLevelingUp() {
        c.addAmount(2);
        c.uponLevelingUp();
        assertEquals(0, c.getResourceAmount(), "the cooldown wasn't update");
    }

    @Test
    void onGameTick() {
        c.addAmount(2);
        c.onGameTick();
        assertEquals(1, c.getResourceAmount(), "the cooldown wasn't update");
        c.onGameTick();
        assertEquals(0, c.getResourceAmount(), "the cooldown wasn't update");

    }

    @Test
    void isAbleToCast() {
        c.addAmount(2);
        assertEquals(false, c.isAbleToCast(), "there is 2 cooldown, can't cast");
        c.reduceAmount(1);
        assertEquals(false, c.isAbleToCast(), "there is 1 cooldown, can't cast");
        c.reduceAmount(1);
        assertEquals(true, c.isAbleToCast(), "there is no cooldown, can cast");
    }
}