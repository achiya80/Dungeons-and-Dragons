package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Rogue extends Player {


    public Rogue(String name, int healthPool, int attack, int defense, int energyCost) {
        super(name, healthPool, attack, defense,"Fan of Knives");
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
