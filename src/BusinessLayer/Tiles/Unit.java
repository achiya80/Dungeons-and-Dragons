package BusinessLayer.Tiles;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.Resources.Resource;
import PresentationLayer.Callback.*;

import java.util.*;
import java.util.function.Supplier;

public abstract class Unit extends Tile{


    protected Map<Character, Supplier<Position>> actionsMap = new HashMap<>(){
        {
            put(Movement.down, () -> getPosition().Down());
            put(Movement.up, () -> getPosition().Up());
            put(Movement.right, () -> getPosition().Right());
            put(Movement.left, () -> getPosition().Left());
            put(Movement.stay, () -> getPosition().NoOperation());
        }
    };
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;
    protected MessageCallback messageCallback;
    protected DeathCallback deathCallback;
    protected PositionCallback positionCallback;
    protected static final Random r = new Random();





    public Unit(char tile, String name, int healthPool, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Resource(healthPool);
        this.attack = attack;
        this.defense = defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback, DeathCallback deathCallback, PositionCallback positionCallback){
        super.initialize(position);
        this.messageCallback = messageCallback;
        this.deathCallback = deathCallback;
        this.positionCallback = positionCallback;
    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }
    public void setPositionCallback(PositionCallback positionCallback) {
        this.positionCallback = positionCallback;
    }

    public void setDeathCallback(DeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }



    protected int attack(){
        int result = r.nextInt(attack);
        messageCallback.send(String.format("%s rolled %d attack points", getName(), result ));
        return result;
    }

    public int defend(){
        int result = r.nextInt(defense);
        messageCallback.send(String.format("%s rolled %d defense points", getName(), result ));
        return result;
    }

    public abstract void onDeath();

    protected void battle(Unit u){
        int damageDone = Math.max(attack() - u.defend(), 0);
        messageCallback.send(String.format("%s dealt %d damage to %s", getName(), damageDone,u.getName()));
        u.getHealth().reduceAmount(damageDone);
        messageCallback.send(u.describe());
    }






    public void interact(Tile t){
        t.accept(this);
    }





    public abstract void visit(Enemy e);
    public abstract void visit(Player p);

    public boolean alive() {
        return getHealth().getResourceAmount() > 0;
    }





    public Resource getHealth() {
        return health;
    }


    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }



    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }



}
