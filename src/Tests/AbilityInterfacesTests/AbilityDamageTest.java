package Tests.AbilityInterfacesTests;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Enemies.Boss;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.*;
import BusinessLayer.Tiles.Unit;
import PresentationLayer.FileHandler.TileFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbilityDamageTest {

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;


    @BeforeEach
    void setUp() {
        p1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        p2 = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        p3 = new Rogue("Arya Stark", 150, 40, 2, 20);
        p4 = new Hunter("Ygritte", 220, 30, 2, 6);
        p5 = new Spy("Black Widow", 150,15, 7, 7);
    }

    @Test
    void generateDamageWarrior() {

        int generated = p1.getAbilityDamage().generateDamage();
        int expected = p1.getHealth().getResourcePool()/10;
        assertEquals(generated, expected, "damage was not as expected");
    }

    @Test
    void generateDamageMage() {
        int generated = p2.getAbilityDamage().generateDamage();
        int expected = 15;
        assertEquals(generated, expected, "damage was not as expected");
    }

    @Test
    void generateDamageRogue() {
        int generated = p3.getAbilityDamage().generateDamage();
        int expected = p3.getAttack();
        assertEquals(generated, expected, "damage was not as expected");
    }

    @Test
    void generateDamageHunter() {
        int generated = p4.getAbilityDamage().generateDamage();
        int expected = p4.getAttack();
        assertEquals(generated, expected, "damage was not as expected");
    }

    @Test
    void generateDamageSpy() {
        int generated = p5.getAbilityDamage().generateDamage();
        int expected = p5.getDefense();
        assertEquals(generated, expected, "damage was not as expected");
    }
}