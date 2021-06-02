package BusinessLayer;

import BusinessLayer.Resources.Energy;
import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Rogue extends Player {

    private Energy energy;

    private static final int ATTACK_BONUS = 3;

    public Rogue(String name, int healthPool, int attack, int defense, int energyCost) {
        super(name, healthPool, attack, defense,"Fan of Knives");
        this.energy = new Energy(energyCost);
        setAbilityDamage(() -> getAttack());
    }
    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        if(getEnergy().isAbleToCast()){
            List<Enemy> inRange = getEnergy().filterRange(getPosition(), enemies);
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            getEnergy().onAbilityCast();
            for (Enemy e : inRange){
                abilityDamage(e);
            }
        }
        else{
            onPlayerTurn();
            messageCallback.send(String.format("%s tried to cast %s, but there was not enough %s: %d/%d", getName(),getABILITY_NAME(),getEnergy().getResourceName(),getEnergy().getResourceAmount(), getEnergy().getCost()));
        }
    }

    @Override
    public void levelUp() {
        int healthGained = gainHealth();
        int attackGained = gainAttack();
        int defenseGained = gainDefense();
        messageCallback.send(String.format("%s reached level %d: +%d Health +%d Attack +%d Defense", getName(), ++level, healthGained,attackGained, defenseGained));
        getHealth().setResourcePool(healthGained);
        setAttack(attackGained);
        setDefense(defenseGained);
        getEnergy().uponLevelingUp();
    }

    public void onPlayerTurn(){
        getEnergy().onGameTick();
    }
    public Energy getEnergy(){
        return energy;
    }
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }

    public String describe(){
        return String.format("%s   %s", super.describe(), getEnergy());
    }
}
