package BusinessLayer.Enemies;

import BusinessLayer.Players.Player;
import BusinessLayer.Tiles.Empty;

import java.util.List;

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

    @Override
    public void performAction(Player player, List<Enemy> enemies) {
        visible=ticksCount<visibilityTime;
        ticksCount = (ticksCount == (visibilityTime + invisibilityTime)) ? 0 : ticksCount + 1;
        if(this.getPosition().Range(player.getPosition())<2) {
            battle(player);
        }
    }

    @Override
    public void visit(Empty e){ }

    @Override
    public String toString(){
        return (visible) ? super.toString() : ".";
    }
}
