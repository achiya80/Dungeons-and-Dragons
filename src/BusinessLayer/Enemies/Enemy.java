package BusinessLayer.Enemies;

import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BarbedWall;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.MessageCallback;

import java.util.List;

public abstract class Enemy extends Unit {

    protected int experienceValue;

    public Enemy(char tile, String name, int healthPool, int attack, int defense, int experienceValue) {
        super(tile, name, healthPool, attack, defense);
        this.experienceValue = experienceValue;
    }
    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position, messageCallback,null, null);
    }

    public int getExperienceValue() {
        return experienceValue;
    }
    
    public abstract void preformAction(Player player);

    @Override
    public void onDeath() {
        deathCallback.call();
    }

    @Override
    public void visit(Player p) {
        super.battle(p);
    }

    @Override
    public void visit(Enemy e) { }

    @Override
    public void visit(BarbedWall b){ }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }




}
