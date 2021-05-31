package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Warrior extends Player {

    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int coolDownPool) {
        super(name, healthPool, attackPoints, defensePoints, "Avenger’s Shield");
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
