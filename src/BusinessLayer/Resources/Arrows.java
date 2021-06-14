package BusinessLayer.Resources;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Board.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Arrows extends AbilityResource {

    private static final String RESOURCE_NAME = "Arrows";
    private static final int MAX_TICKS = 10;
    private int ticksCount;

    public Arrows(int resourceAmount, int range) {
        super(Integer.MAX_VALUE, resourceAmount, range, RESOURCE_NAME, 1);
        this.ticksCount = 0;
    }

    @Override
    public List<Enemy> filterRange(Position position, List<Enemy> enemies){
        return enemies.stream().filter(e -> position.Range(e.getPosition()) <= range).collect(Collectors.toList());
    }

    public void uponLevelingUp(int level){
        addAmount(10*level);
    }

    public void onGameTick(int level){
        if(ticksCount == MAX_TICKS){
            addAmount(level);
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
    }

    public String toString(){
        return getResourceName() + ": " + getResourceAmount() + "\t\t\tRange: " + range;
    }
}
