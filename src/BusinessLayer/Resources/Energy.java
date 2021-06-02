package BusinessLayer.Resources;

public class Energy extends AbilityResource {


    private static final String RESOURCE_NAME = "Energy";

    public Energy(int cost) {
        super(100, 2,RESOURCE_NAME ,cost);
        this.cost = cost;
    }

    public void uponLevelingUp(){
        addAmount(getResourcePool());
    }

    public void onGameTick(){
        addAmount(10);
    }

}
