package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.DeathCallback;
import PresentationLayer.Callback.MessageCallback;
import PresentationLayer.Callback.PositionCallback;

public abstract class Enemy extends Unit{

    private int experienceValue;

    public Enemy(char tile, String name, int healthPool, int attack, int defense, int experienceValue) {
        super(tile, name, healthPool, attack, defense);
        this.experienceValue = experienceValue;
    }
    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position, messageCallback,null, null);
    }




    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {
        super.battle(p);
    }

    @Override
    public void visit(Enemy e) { }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }




}
