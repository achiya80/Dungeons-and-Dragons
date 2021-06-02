package BusinessLayer.Resources;

public class Cooldown extends AbilityResource {


    private final int COST = getResourcePool();

    private static final String RESOURCE_NAME = "Cooldown";

    public Cooldown(int resourcePool) {
        super(resourcePool, 0, 3, RESOURCE_NAME);
    }

    public void uponLevelingUp(){
        reduceAmount(COST);
    }

    public void onGameTick(){
        reduceAmount(1);
    }

    public void onAbilityCast(){
        addAmount(COST);
    }



    public boolean isAbleToCast(){
        return getResourceAmount() == 0;
    }




}
