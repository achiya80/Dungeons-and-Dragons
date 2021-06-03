package BusinessLayer.Enemies;

import BusinessLayer.Players.Player;

public class Trap extends Enemy {

    private final int visibilityTime;
    private final int invisibilityTime;
    private int ticksCount;
    private boolean visible;


    public Trap(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attack, defense, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
    public void preformAction(Player player) {
        visible=ticksCount<visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount=0;
        else
            ticksCount=ticksCount+1;
        if(this.getPosition().Range(player.getPosition())<2)
            this.attack();

    }

    @Override
    public String toString(){
        return (visible) ? super.toString() : ".";
    }
}
