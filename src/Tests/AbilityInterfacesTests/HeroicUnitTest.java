package Tests.AbilityInterfacesTests;

import BusinessLayer.AbilityInterfaces.HeroicUnit;
import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Boss;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Enemies.Witch;
import BusinessLayer.Players.Player;
import BusinessLayer.Players.Warrior;
import BusinessLayer.Tiles.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroicUnitTest {

    private HeroicUnit h1;
    private HeroicUnit h2;
    private HeroicUnit h3;
    private List<Enemy> enemies;
    private Player player;

    @BeforeEach
    void setUp() {
        Player p = new Warrior("Jon Snow", 500, 30, 4, 3);
        p.initialize(new Position(2,3), (msg) -> onMessageCallback(msg), () -> onDeathCallback(), (pos) -> onPositionCallback(pos));
        Boss b = new Boss('K', "Night's King", 5000, 300, 15, 5000, 8, 3);
        b.initialize(new Position(2,4), (msg) -> onMessageCallback(msg));
        Witch w = new Witch('W', "Morgen La Fey", 10000, 450, 20, 10000, 7, 2);
        w.initialize(new Position(2,2), (msg) -> onMessageCallback(msg));
        Unit.DeterministicForTesting();
        Movement.DeterministicForTesting();
        enemies = Arrays.asList(b, w);
        player = p;
        h1 = p;
        h2 = b;
        h3 = w;
    }

    @Test
    void castAbilityPlayer() {
        h1.castAbility(player, enemies);
        int HPE1 = enemies.get(0).getHealth().getResourceAmount();
        int HPE2 = enemies.get(1).getHealth().getResourceAmount();
        assertEquals(9969,HPE2,"ability didn't work as planed");
        assertEquals(5000,HPE1,"ability didn't work as planed");
    }

    @Test
    void castAbilityBoss() {
        h2.castAbility(player, enemies);
        assertEquals(203, player.getHealth().getResourceAmount(), "player health was not as expected");
    }

    @Test
    void castAbilityWitch() {
        h3.castAbility(player, enemies);
        assertEquals(450, player.getHealth().getResourceAmount(), "player health was not as expected");
    }

    void onMessageCallback(String msg){

    }


    void onDeathCallback(){

    }

    void onPositionCallback(Position position){

    }




}