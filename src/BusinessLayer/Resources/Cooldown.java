package BusinessLayer.Resources;

public class Cooldown extends AbilityResource {

    private static final String RESOURCE_NAME = "Cooldown";

    public Cooldown(int resourcePool) {
        super(resourcePool, 0, 3, RESOURCE_NAME, -resourcePool);
    }

    @Override
    public void uponLevelingUp(){
        reduceAmount(-getCost());
    }

    public void onGameTick(){
        reduceAmount(1);
    }

    @Override
    public boolean isAbleToCast(){
        return getResourceAmount() == 0;
    }




}
