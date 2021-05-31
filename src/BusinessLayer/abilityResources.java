package BusinessLayer;

public class abilityResources {

    private int resourceBar;
    private int resourcePool;
    private int resourceCost;
    private int hitsCount;
    private int abilityRange;
    private abilityPower abilityPower;

    public abilityResources(int resourcePool, int resourceBar, int resourceCost, int hitsCount, int abilityRange, abilityPower abilityPower){
        this.resourcePool = resourcePool;
        this.resourceBar = resourceBar;
        this.resourceCost = resourceCost;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.abilityPower = abilityPower;
    }
}
