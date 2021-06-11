package BusinessLayer.Enemies;

import BusinessLayer.AbilityInterfaces.HeroicUnit;
import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

import BusinessLayer.ActionHandler.*;

public class Boss extends Monster implements HeroicUnit {

    private int abilityFrequency;
    private int combatTicks;

    public Boss(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visionRange, int abilityFrequency) {
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
        messageCallback.send(String.format("%s shoots %s for %d damage", getName(), player.getName(), getAttack()));
        int damageDone = Math.max(getAttack() - player.defend(),0);
        messageCallback.send(String.format("%s hit %s for %d damage", getName(), player.getName(), damageDone));
        player.getHealth().reduceAmount(damageDone);
        if(!player.alive()){
            player.onDeath();
        }
    }



}
