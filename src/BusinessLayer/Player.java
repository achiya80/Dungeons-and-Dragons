package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.*;

import java.util.List;

public abstract class Player extends Unit implements HeroicUnit{

    public static final char playerTile = '@';
    private static final int REQ_EXP = 50;
    private static final int ATTACK_BONUS = 4;
    private static final int DEFENSE_BONUS = 1;
    private static final int HEALTH_BONUS = 10;


    private int experience = 0;
    private int level = 1;
    private final String ABILITY;


    public Player(String name, int healthPool, Integer attack, Integer defense, String ABILITY) {
        super('@', name, healthPool, attack, defense);
        this.ABILITY = ABILITY;
    }

    public void initialize(Position position, MessageCallback messageCallback, DeathCallback deathCallback, PositionCallback positionCallback){
        super.initialize(position, messageCallback, deathCallback, positionCallback);
    }






    public abstract void castAbility();

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) {
        super.battle(e);
    }


    // Deals damage to the enemy with ability
    protected void abilityDamage(Enemy e, int abilityDamage) {

    }

    // When the player kills an enemy
    protected void onKill(Enemy e){

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

    }

    public void performAction(char c, List<Enemy> enemies){
        if(c == 'e'){
            castAbility();
        }
        else if(c == 's'){
            positionCallback.Move(new Position(getPosition().getX(), getPosition().getY()-1));
        }
        else if(c == 'd'){
            positionCallback.Move(new Position(getPosition().getX()+1, getPosition().getY()));
        }
        else if(c == 'w'){
            positionCallback.Move(new Position(getPosition().getX(), getPosition().getY()+1));
        }
        else if(c == 'a'){
            positionCallback.Move(new Position(getPosition().getX()-1, getPosition().getY()));
        }
    }

    @Override
    public String toString() {
        return alive() ? super.toString() : "X";
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

    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }

    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
    }
}
