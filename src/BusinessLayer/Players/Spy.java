package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Bombs;

import java.util.List;

public class Spy extends Player{

    private Bombs bombs;

    private static final int ATTACK_BONUS = 1;
    private static final int DEFENSE_BONUS = 15;
    private static final int HEALTH_BONUS = 2;


    public Spy(String name, int healthPool, Integer attack, Integer defense) {
        super(name, healthPool, attack, defense, "Plant And Throw");
        setAbilityDamage(() -> getDefense() * 10);
        this.bombs = new Bombs(5*getLevel());
    }


    @Override
    public void levelUp() {
        super.levelUp();
        getBombs().uponLevelingUp();
    }

    @Override
    public void onPlayerTurn() {
        getBombs().onGameTick();
    }

    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        if(getBombs().isAbleToCast()){
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            List<Enemy> inRange = getBombs().filterRange(getPosition(),enemies);
            if(!inRange.isEmpty()) {
                Enemy e = closest(inRange);
                abilityDamage(e);
            }
            getBombs().onAbilityCast();
        }
        else {
            messageCallback.send(String.format("%s tried to cast %s, but %s is: %d", getName(), getABILITY_NAME(), getBombs().getResourceName(), getBombs().getResourceAmount()));
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

    public Bombs getBombs() {
        return bombs;
    }

    protected int gainHealth(){ return super.gainHealth() + getLevel()*HEALTH_BONUS; }
    protected int gainAttack(){
        return super.gainAttack() + getLevel() * ATTACK_BONUS;
    }
    protected int gainDefense(){
        return super.gainDefense() + getLevel() * DEFENSE_BONUS;
    }


    public String describe(){
        return String.format("%s  %s",super.describe(), getBombs());
    }

}
