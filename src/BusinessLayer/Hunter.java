package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Hunter extends Player {

    public Hunter(String name, int healthPool, int attackPoints, int defensePoints, int range) {
        super(name, healthPool, attackPoints, defensePoints, "Shoot");
    }


    @Override
    public void castAbility(Player player, List<Enemy> enemies) {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void levelUp() {

    }

    @Override
    public void accept(Visitor v) {

    }

}
