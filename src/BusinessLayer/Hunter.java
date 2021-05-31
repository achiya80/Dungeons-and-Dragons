package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Hunter extends Player {

    public Hunter(String name, int healthPool, int attackPoints, int defensePoints, int range) {
        super(name, healthPool, attackPoints, defensePoints, "Shoot");
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
