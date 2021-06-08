package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Bullets;

import java.util.List;

public class Spy extends Player{

    private Bullets bullets;

    private static final int ATTACK_BONUS = 1;
    private static final int DEFENSE_BONUS = 5;
    private static final int HEALTH_BONUS = 2;


    public Spy(String name, int healthPool, int attack, int defense, int bulletsCost) {
        super(name, healthPool, attack, defense, "Rain Of Bullets");
        setAbilityDamage(() -> getDefense());
        this.bullets = new Bullets(10*getLevel(), bulletsCost);
    }


    @Override
    public void levelUp() {
        super.levelUp();
        getBullets().uponLevelingUp();
    }

    @Override
    public void onPlayerTurn() {
        getBullets().onGameTick();
    }

    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        if(getBullets().isAbleToCast()){
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            List<Enemy> inRange = getBullets().filterRange(getPosition(),enemies);
            if(!inRange.isEmpty()) {
                Enemy e = closest(inRange);
                int bulletsSpend = 0;
                while (bulletsSpend++ < getBullets().getCost() && e.alive()) {
                    abilityDamage(e);
                }
            }
            getBullets().onAbilityCast();
        }
        else {
            messageCallback.send(String.format("%s tried to cast %s, but %s is: %d", getName(), getABILITY_NAME(), getBullets().getResourceName(), getBullets().getResourceAmount()));
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

    public Bullets getBullets() {
        return bullets;
    }

    protected int gainHealth(){ return super.gainHealth() + getLevel()*HEALTH_BONUS; }
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }
    protected int gainDefense(){
        return super.gainDefense() + getLevel() * DEFENSE_BONUS;
    }


    public String describe(){
        return String.format("%s  %s",super.describe(), getBullets());
    }

}
