package BusinessLayer.Enemies;

import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.MessageCallback;

import java.util.List;

public abstract class Enemy extends Unit {

    protected int experienceValue;

    protected Enemy(char tile, String name, int healthPool, int attack, int defense, int experienceValue) {
        super(tile, name, healthPool, attack, defense);
        this.experienceValue = experienceValue;
    }

    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position, messageCallback,deathCallback, positionCallback);
    }

    public int getExperienceValue() {
        return experienceValue;
    }
    
    public abstract void performAction(Player player, List<Enemy> enemies);

    @Override
    public void onDeath() {
        deathCallback.call();
    }

    @Override
    public void visit(Player p) {
        super.battle(p);
        if(!p.alive()){
            p.onDeath();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }




}
