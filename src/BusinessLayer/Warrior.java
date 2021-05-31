package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Warrior extends Player {

    private abilityResources abilityResources;

    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int coolDownPool) {
        super(name, healthPool, attackPoints, defensePoints, "Avenger’s Shield");
        this.abilityResources = new abilityResources(coolDownPool, 0, 1, 1,3, (() -> (int)0.1*super.getHealth().getHealthPool()));
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
