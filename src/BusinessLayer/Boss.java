package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Boss extends Monster implements HeroicUnit{
    private Integer abilityFrequency;
    private Integer combatTicks;


    public Boss(char tile, String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defensePoints, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void accept(Visitor v) {

    }
}
