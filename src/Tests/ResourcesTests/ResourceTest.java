package Tests.ResourcesTests;

import BusinessLayer.Resources.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {


    Resource r;
    @BeforeEach
    void setUp() {
        r = new Resource(100, 60);
    }

    @Test
    void addAmountOverFlow() {
        r.addAmount(45);
        assertEquals(r.getResourcePool(), r.getResourceAmount(), "amount overflow the limits");
    }
    @Test
    void addAmountBasic() {
        r.addAmount(6);
        assertEquals(66, r.getResourceAmount(), "amount overflow the limits");
    }

    @Test
    void reduceAmountBasic() {
        r.reduceAmount(40);
        assertEquals(20, r.getResourceAmount(), "amount overflow the limits");
    }


    @Test
    void reduceAmountOverFlow() {
        r.reduceAmount(70);
        assertEquals(0, r.getResourceAmount(), "amount overflow the limits");
    }

    @Test
    void uponLevelingUp() {
        r.uponLevelingUp();
        assertEquals(r.getResourcePool(), r.getResourceAmount(), "amount overflow the limits");
    }
}