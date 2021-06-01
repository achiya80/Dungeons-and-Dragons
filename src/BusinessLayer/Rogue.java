package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Rogue extends Player {


    public Rogue(String name, int healthPool, int attack, int defense, int energyCost) {
        super(name, healthPool, attack, defense,"Fan of Knives");
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
    public void accept(Visitor v) {

    }
}
