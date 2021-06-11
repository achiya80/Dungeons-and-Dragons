package BusinessLayer.Resources;

public class Bullets extends AbilityResource{

    private static final String RESOURCE_NAME = "Bullets";
    private static final int MAX_TICKS = 5;
    private int ticksCount;

    public Bullets(int resourceAmount, int cost) {
        super(Integer.MAX_VALUE, resourceAmount, 5, RESOURCE_NAME, cost);
        this.ticksCount = 0;
    }

    @Override
    public void uponLevelingUp(){
        addAmount(10);
    }

    public void onGameTick(){
        if(ticksCount == MAX_TICKS){
            addAmount(5);
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
