package BusinessLayer.AbilityInterfaces;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;

import java.util.List;

public interface HeroicUnit {

    public void castAbility(Player player, List<Enemy> enemies);
}
