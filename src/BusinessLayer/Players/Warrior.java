package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Cooldown;

import java.util.List;

public class Warrior extends Player {

    private Cooldown cooldown;

    private static final int ATTACK_BONUS = 2;
    private static final int DEFENSE_BONUS = 1;
    private static final int HEALTH_BONUS = 5;


    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int cooldownPool) {
        super(name, healthPool, attackPoints, defensePoints, "Avenger’s Shield");
        this.cooldown = new Cooldown(cooldownPool);
        super.setAbilityDamage(() -> getHealth().getResourcePool()/10);
    }


    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        if(getCooldown().isAbleToCast()) {
            getCooldown().onAbilityCast();
            int healAmount = getDefense()*10;
            getHealth().addAmount(healAmount);
            List<Enemy> inRange = getCooldown().filterRange(getPosition(), enemies);
            messageCallback.send(String.format("%s cast %s healing for %d", getName(), getABILITY_NAME(), healAmount));
            if(!inRange.isEmpty()) {
                Enemy e = inRange.get(r.nextInt(inRange.size()));
                abilityDamage(e);
            }
        }
        else{
            messageCallback.send(String.format("%s tried to cast %s, but %s is: %d", getName(),getABILITY_NAME(),getCooldown().getResourceName(),getCooldown().getResourceAmount()));
            onPlayerTurn();
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        getCooldown().uponLevelingUp();
    }
    @Override
    public void onPlayerTurn() {
        getCooldown().onGameTick();
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    public String describe(){
        return String.format("%s\t\t %s", super.describe(), getCooldown());
    }

    protected int gainHealth(){ return super.gainHealth() + getLevel()*HEALTH_BONUS; }
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }
    protected int gainDefense(){
        return super.gainDefense() + getLevel() * DEFENSE_BONUS;
    }

}
