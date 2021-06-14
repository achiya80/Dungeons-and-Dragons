package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Energy;

import java.util.List;

public class Rogue extends Player {

    private static final int ATTACK_BONUS = 3;
    private Energy energy;

    public Rogue(String name, int healthPool, int attack, int defense, int energyCost) {
        super(name, healthPool, attack, defense,"Fan of Knives");
        this.energy = new Energy(energyCost);
        setAbilityDamage(() -> getAttack());
    }

    public Energy getEnergy(){
        return energy;
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
        super.levelUp();
        getEnergy().uponLevelingUp();
    }

    @Override
    public void onPlayerTurn(){
        getEnergy().onGameTick();
    }

    @Override
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }

    @Override
    public String describe(){
        return String.format("%s  %s", super.describe(), getEnergy());
    }
}
