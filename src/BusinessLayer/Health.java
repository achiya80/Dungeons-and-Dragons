package BusinessLayer;

public class Health {

    private int healthPool;
    private int healthBar;


    public Health(int healthPool) {
        this.healthPool = healthPool;
        this.healthBar = healthPool;
    }

    public int getHealthPool(){
        return healthPool;
    }

    public int getHealthBar(){
        return healthBar;
    }
    public void setHealthPool(int health){
        healthPool += health;
    }
    public void Heal(int recover){
        healthBar = Math.min(recover + healthBar, healthPool);
    }
    public void Bleed(int damage) {
        healthBar = Math.max(healthBar - damage, 0);
    }

    public boolean alive(){
        return healthBar != 0;
    }
}
