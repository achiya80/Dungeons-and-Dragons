package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Arrows;

import java.util.List;

public class Hunter extends Player {

    private static final int ATTACK_BONUS = 2;
    private static final int DEFENSE_BONUS = 1;
    private Arrows arrows;

    public Hunter(String name, int healthPool, int attack, int defense, int range) {
        super(name, healthPool, attack, defense, "Shoot");
        setAbilityDamage(() -> getAttack());
        this.arrows = new Arrows(10*getLevel(),range);
    }

    public Arrows getArrows(){
        return arrows;
    }

    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        List<Enemy> inRange = getArrows().filterRange(getPosition(),enemies);
        if(getArrows().isAbleToCast() && !inRange.isEmpty()){
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            Enemy e = closest(inRange);
            abilityDamage(e);
            getArrows().onAbilityCast();
        }
        else {
            if (!getArrows().isAbleToCast()) {
                messageCallback.send(String.format("%s tried to cast %s, but %s is: %d", getName(), getABILITY_NAME(), getArrows().getResourceName(), getArrows().getResourceAmount()));
            }
            else {
                messageCallback.send(String.format("%s tried to cast %s, but there was no enemy in range", getName(), getABILITY_NAME()));
            }
        }
    }

    private Enemy closest(List<Enemy> enemies){
        Enemy e = enemies.get(0);
        for(Enemy enemy : enemies){
            if(e.getPosition().Range(getPosition()) > enemy.getPosition().Range(getPosition())){
                e = enemy;
            }
        }
        return e;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        getArrows().uponLevelingUp(getLevel());
    }

    @Override
    public void onPlayerTurn(){
        arrows.onGameTick(getLevel());
    }

    @Override
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }

    @Override
    protected int gainDefense(){
        return super.gainDefense() + getLevel() * DEFENSE_BONUS;
    }

    @Override
    public String describe(){
        return String.format("%s  %s",super.describe(), getArrows());
    }


}
