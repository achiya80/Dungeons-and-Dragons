package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Mage extends Player {

    private abilityResources abilityResources;

    public Mage(String name, int healthPool, int attack, int defense
            , int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthPool, attack, defense, "Blizzard");
        this.abilityResources = new abilityResources(manaPool, manaPool/4, manaCost, hitsCount, abilityRange, new Power(spellPower));
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
