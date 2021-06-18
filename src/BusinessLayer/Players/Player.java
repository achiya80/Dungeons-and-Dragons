package BusinessLayer.Players;

import BusinessLayer.AbilityInterfaces.AbilityDamage;
import BusinessLayer.AbilityInterfaces.HeroicUnit;
import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.*;

import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {

    public static final char PLAYER_TILE = '@';
    private static final int REQ_EXP = 50;
    private static final int ATTACK_BONUS = 4;
    private static final int DEFENSE_BONUS = 1;
    private static final int HEALTH_BONUS = 10;
    protected int experience = 0;
    protected int level = 1;
    protected final String ABILITY_NAME;
    protected AbilityDamage abilityDamage;

    public Player(String name, int healthPool, Integer attack, Integer defense, String ABILITY_NAME) {
        super(PLAYER_TILE, name, healthPool, attack, defense);
        this.ABILITY_NAME = ABILITY_NAME;
    }

    public void initialize(Position position, MessageCallback messageCallback, DeathCallback deathCallback, PositionCallback positionCallback){
        super.initialize(position, messageCallback, deathCallback, positionCallback);
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }

    public String getABILITY_NAME() {
        return ABILITY_NAME;
    }

    public AbilityDamage getAbilityDamage(){
        return abilityDamage;
    }

    public void setAbilityDamage(AbilityDamage abilityDamage){
        this.abilityDamage = abilityDamage;
    }

    public abstract void onPlayerTurn();

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void visit(Enemy e) {
        super.battle(e);
        if(!e.alive()){
            swapPositions(e);
            onKill(e);
        }
    }

    // Deals damage to the enemy with ability
    protected void abilityDamage(Enemy e) {
        int damageDone = Math.max(abilityDamage.generateDamage() - e.defend(),0);
        e.getHealth().reduceAmount(damageDone);
        messageCallback.send(String.format("%s hit %s for %d ability damage", getName(), e.getName(),damageDone));
        if(!e.alive()) {
            onKill(e);
        }
    }

    // When the player kills an enemy
    protected void onKill(Enemy e) {
        int expPoints = e.getExperienceValue();
        messageCallback.send(String.format("%s died. %s gained %d experience points", e.getName(), getName(), expPoints));
        addExperience(expPoints);
        e.onDeath();
    }

    protected void addExperience(int experienceGained){
        setExperience(experienceGained);
        while (getExperience() >= levelUpRequirement()) {
            setExperience(-levelUpRequirement());
            levelUp();
        }
    }

    // When the player dies
    @Override
    public void onDeath() {
        messageCallback.send("You lost.");
        // Use deathCallback to alert the level manager
        deathCallback.call();
    }

    // Player level up
    protected void levelUp(){
        level++;
        getHealth().setResourcePool(gainHealth());
        setAttack(gainAttack());
        setDefense(gainDefense());
        getHealth().uponLevelingUp();
        messageCallback.send(String.format("%s reached level %d: +%d Health +%d Attack +%d Defense", getName(), level, gainHealth(), gainAttack(), gainDefense()));
    }

    public void performAction(char c,List<Enemy> enemies){
        if(c == Movement.castAbility){
            castAbility(this, enemies);
        }
        else {
            onPlayerTurn();
            positionCallback.Move(actionsMap.get(c).get());
        }
    }

    // Health / attack / defense gain when the player levels up - should be overriden by player subclasses and call super.gainHealth() for the base amount, then add the extra value
    protected int gainHealth(){
        return level * HEALTH_BONUS;
    }

    protected int gainAttack(){
        return level * ATTACK_BONUS;
    }

    protected int gainDefense(){
        return level * DEFENSE_BONUS;
    }

    private int levelUpRequirement(){
        return REQ_EXP * level;
    }

    public String describe() { return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement()); }

    @Override
    public String toString() {
        return alive() ? super.toString() : "X";
    }

}
