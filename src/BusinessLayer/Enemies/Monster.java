package BusinessLayer.Enemies;

public class Monster extends Enemy{

    private int visionRange;


    public Monster(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, healthPool, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }


}
