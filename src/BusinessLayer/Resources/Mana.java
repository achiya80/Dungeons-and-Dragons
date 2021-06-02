package BusinessLayer.Resources;

public class Mana extends AbilityResource {


    private static final String RESOURCE_NAME = "Mana";

    private int cost;
    private int spellPower;

    public Mana(int resourcePool, int range, int cost, int spellPower) {
        super(resourcePool, resourcePool/4, range, RESOURCE_NAME);
        this.cost = cost;
        this.spellPower = spellPower;
    }

    public void uponLevelingUp(){
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

    public void setSpellPower(int add){
        spellPower += add;
    }
    public int getSpellPower(){
        return spellPower;
    }
    public String toString(){
        return String.format("%s \t\t spell power: %d",super.toString(), getSpellPower());
    }

    public int getCost(){
        return cost;
    }
    public boolean isAbleToCast(){
        return (getResourceAmount() - cost >= 0);
    }
}
