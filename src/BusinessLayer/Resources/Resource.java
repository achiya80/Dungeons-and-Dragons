package BusinessLayer.Resources;

public class Resource {

    protected int resourcePool;
    protected int resourceAmount;

    public Resource(int resourcePool, int resourceAmount) {
        this.resourcePool = resourcePool;
        this.resourceAmount = resourceAmount;
    }

    public Resource(int resourcePool){
        this.resourcePool = resourcePool;
        this.resourceAmount = resourcePool;
    }

    public int getResourcePool() {
        return resourcePool;
    }

    public void setResourcePool(int addedPool){
        resourcePool += addedPool;
    }

    public int getResourceAmount(){
        return resourceAmount;
    }

    public void addAmount(int add){
        resourceAmount = Math.min(add + resourceAmount, resourcePool);
    }

    public void reduceAmount(int reduce) {
        resourceAmount = Math.max(resourceAmount - reduce, 0);
    }

    public void uponLevelingUp(){
        addAmount(getResourcePool());
    }

    public String toString(){
        return getResourceAmount() + "/" + getResourcePool();
    }


}
