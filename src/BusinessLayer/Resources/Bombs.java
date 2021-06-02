package BusinessLayer.Resources;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Bombs extends AbilityResource{
    private static final String RESOURCE_NAME = "Bombs";

    private static final int MAX_TICKS = 5;

    private int ticksCount;

    public Bombs(int resourceAmount) {
        super(Integer.MAX_VALUE, resourceAmount, 5, RESOURCE_NAME, 1);
        this.ticksCount = 0;
    }

    public void uponLevelingUp(){
        addAmount(5);
    }

    public void onGameTick(){
        if(ticksCount == MAX_TICKS){
            addAmount(2);
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
    }

    public String toString(){
        return getResourceName() + ": " + getResourceAmount() ;
    }
}
