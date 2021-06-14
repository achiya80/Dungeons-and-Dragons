package Tests.ResourcesTests;

import BusinessLayer.Resources.Mana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManaTest {

    Mana m;
    @BeforeEach
    void setUp() {
        m = new Mana(100, 5, 25, 25);
    }

    @Test
    void uponLevelingUp() {
        m.uponLevelingUp();
        assertEquals(50, m.getResourceAmount(), "the mana amount should update");
    }

    @Test
    void onGameTick() {
        m.onGameTick(2);
        assertEquals(27, m.getResourceAmount(), "the mana amount should update");
    }



}