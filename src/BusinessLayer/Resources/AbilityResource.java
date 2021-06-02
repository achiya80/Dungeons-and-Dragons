package BusinessLayer.Resources;

import BusinessLayer.Enemy;
import BusinessLayer.Position;

import java.util.List;
import java.util.stream.Collectors;

public class AbilityResource extends Resource {

    protected int range;

    protected String resourceName;


    protected AbilityResource(int resourcePool, int resourceAmount, int range, String resourceName) {
        super(resourcePool, resourceAmount);
        this.range = range;
        this.resourceName = resourceName;
    }
    protected AbilityResource(int resourcePool, int range, String resourceName) {
        super(resourcePool);
        this.range = range;
        this.resourceName = resourceName;
    }
    public List<Enemy> filterRange(Position position, List<Enemy> enemies){
        return enemies.stream().filter(e -> position.Range(e.getPosition()) < range).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return resourceName + ": " + super.toString();
    }

    public String getResourceName() {
        return resourceName;
    }



}
