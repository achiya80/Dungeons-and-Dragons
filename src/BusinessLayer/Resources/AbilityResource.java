package BusinessLayer.Resources;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Board.Position;

import java.util.List;
import java.util.stream.Collectors;

public class AbilityResource extends Resource {

    protected int range;
    protected String resourceName;
    protected int cost;

    protected AbilityResource(int resourcePool, int resourceAmount, int range, String resourceName, int cost) {
        super(resourcePool, resourceAmount);
        this.range = range;
        this.resourceName = resourceName;
        this.cost = cost;
    }

    protected AbilityResource(int resourcePool, int range, String resourceName, int cost) {
        super(resourcePool);
        this.range = range;
        this.resourceName = resourceName;
        this.cost = cost;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getCost(){
        return cost;
    }

    public List<Enemy> filterRange(Position position, List<Enemy> enemies){
        return enemies.stream().filter(e -> position.Range(e.getPosition()) < range).collect(Collectors.toList());
    }

    public boolean isAbleToCast(){
        return (getResourceAmount() - cost >= 0);
    }

    public void onAbilityCast(){
        if(isAbleToCast()) {
            reduceAmount(cost);
        }
    }

    @Override
    public String toString(){
        return resourceName + ": " + super.toString();
    }

}
