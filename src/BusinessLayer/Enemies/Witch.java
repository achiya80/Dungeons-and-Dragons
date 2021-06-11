package BusinessLayer.Enemies;

import BusinessLayer.AbilityInterfaces.HeroicUnit;
import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Players.Player;

import java.util.List;

public class Witch extends Monster implements HeroicUnit {

    private int combatTicks;
    private int abilityFrequency;

    public Witch(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attack, defense, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void performAction(Player player, List<Enemy> enemies) {
        if (getPosition().Range(player.getPosition())<visionRange)
        {
            if(combatTicks==abilityFrequency) {
                castAbility(player, enemies);
            }
            else {
                positionCallback.Move(actionsMap.get(playerTrackPattern(player)).get());
            }
            combatTicks = (combatTicks == abilityFrequency) ? 0 : combatTicks+1;
        }
        else {
            combatTicks=0;
            positionCallback.Move(actionsMap.get(Movement.randomMovement()).get());
        }
    }

    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        int damageStolen = player.getHealth().getResourcePool()/10;
        messageCallback.send(String.format("%s still %d health from %s healing for %d", getName(), damageStolen,player.getName(), damageStolen));
        getHealth().addAmount(damageStolen);
        player.getHealth().reduceAmount(damageStolen);
        if(!player.alive()){
            player.onDeath();
        }
    }
}
