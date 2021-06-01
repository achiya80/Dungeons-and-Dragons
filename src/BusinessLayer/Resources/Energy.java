package BusinessLayer.Resources;

import BusinessLayer.Enemy;

import java.util.List;

public class Energy extends AbilityResource {


    private static final String RESOURCE_NAME = "Energy";

    private int cost;

    public Energy(int resourcePool, int range, int cost) {
        super(resourcePool, range,RESOURCE_NAME );
        this.cost = cost;
    }

    public void uponLevelingUp(){
        addAmount(getResourcePool());
    }

    public void onGameTick(){
        addAmount(10);
    }

    public void onAbilityCast(){
        if(isAbleToCast()) {
            reduceAmount(cost);
        }
    }

    public boolean isAbleToCast(){
        return (getResourceAmount() - cost >= 0);
    }


}
