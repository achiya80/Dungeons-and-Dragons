package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Trap extends Enemy {

    private final int visibilityTime;
    private final int invisibilityTime;
    private int ticksCount;
    private boolean Visible;


    public Trap(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attack, defense, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.Visible = true;
    }


    @Override
    public String toString(){
        return (Visible) ? super.toString() : ".";
    }
}
