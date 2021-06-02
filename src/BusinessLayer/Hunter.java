package BusinessLayer;

import BusinessLayer.Resources.Arrows;
import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Hunter extends Player {

    private static final int ATTACK_BONUS = 2;
    private static final int DEFENSE_BONUS = 1;

    private Arrows arrows;

    public Hunter(String name, int healthPool, int attackPoints, int defensePoints, int range) {
        super(name, healthPool, attackPoints, defensePoints, "Shoot");
        setAbilityDamage(() -> getAttack());
        this.arrows = new Arrows(10*getLevel(),range);
    }


    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        List<Enemy> inRange = getArrows().filterRange(getPosition(),enemies);
        if(getArrows().isAbleToCast(inRange)){
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            Enemy e = closest(inRange);
            abilityDamage(e);
        }
        else{
            messageCallback.send(String.format("%s tried to cast %s, but %s is: %d", getName(),getABILITY_NAME(),getArrows().getResourceName(),getArrows().getResourceAmount()));
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
        int healthGained = gainHealth();
        int attackGained = gainAttack();
        int defenseGained = gainDefense();
        messageCallback.send(String.format("%s reached level %d: +%d Health +%d Attack +%d Defense", getName(), ++level, healthGained,attackGained, defenseGained));
        getHealth().setResourcePool(healthGained);
        setAttack(attackGained);
        setDefense(defenseGained);
        arrows.uponLevelingUp(getLevel());
    }

    public void onPlayerTurn(){
        arrows.onGameTick(getLevel());
    }

    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }
    protected int gainDefense(){
        return super.gainDefense() + getLevel() * DEFENSE_BONUS;
    }
    public Arrows getArrows(){
        return arrows;
    }

    public String describe(){
        return String.format("%s  %s",super.describe(), getArrows());
    }


}
