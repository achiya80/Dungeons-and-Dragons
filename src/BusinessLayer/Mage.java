package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Mage extends Player {

    


    public Mage(String name, int healthPool, int attack, int defense
            , int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthPool, attack, defense, "Blizzard");
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
