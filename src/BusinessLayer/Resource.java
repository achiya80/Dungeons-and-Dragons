package BusinessLayer;

public class Resource {
    public int getResourcePool() {
        return resourcePool;
    }

    private int resourcePool;
    private int resourceAmount;


    public Resource(int resourcePool, int resourceAmount) {
        this.resourcePool = resourcePool;
        this.resourceAmount = resourceAmount;
    }
    public Resource(int resourcePool){
        this.resourcePool = resourcePool;
        this.resourceAmount = resourcePool;
    }


    public int getResourceAmount(){
        return resourceAmount;
    }
    public void setResourcePool(int addedPool){
        resourcePool += addedPool;
    }
    public void addAmount(int add){
        resourceAmount = Math.min(add + resourceAmount, resourcePool);
    }
    public void reduceAmount(int reduce) {
        resourceAmount = Math.max(resourceAmount - reduce, 0);
    }


}
