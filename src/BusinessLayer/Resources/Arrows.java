package BusinessLayer.Resources;

import BusinessLayer.Enemy;
import BusinessLayer.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Arrows extends AbilityResource {

    private static final int COST = 1;

    private static final String RESOURCE_NAME = "Arrows";

    private static final int MAX_TICKS = 10;

    private int ticksCount;

    public Arrows(int resourceAmount, int range) {
        super(Integer.MAX_VALUE, resourceAmount, range, RESOURCE_NAME);
        this.ticksCount = 0;
    }

    @Override
    public List<Enemy> filterRange(Position position, List<Enemy> enemies){
        return enemies.stream().filter(e -> position.Range(e.getPosition()) <= range).collect(Collectors.toList());
    }

    public void uponLevelingUp(int level){
        addAmount(level);
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

    public void onAbilityCast(){
        reduceAmount(COST);
    }


    public boolean isAbleToCast(List<Enemy> enemies){
        return (getResourceAmount() - COST >= 0) && !(enemies.isEmpty());
    }

    public String toString(){
        return getResourceName() + ": " + getResourceAmount() + "\t\t\t\t\tRange: " + range;
    }
}
