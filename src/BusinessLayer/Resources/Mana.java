package BusinessLayer.Resources;

public class Mana extends AbilityResource {


    private static final String RESOURCE_NAME = "Mana";

    private int cost;

    public Mana(int resourcePool, int range, int cost) {
        super(resourcePool, resourcePool/4, range, RESOURCE_NAME);
        this.cost = cost;
    }

    public void uponLevelingUp(int level){
        setResourcePool(25*level);
        addAmount(getResourcePool()/4);
    }

    public void onGameTick(int level){
        addAmount(level);
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
