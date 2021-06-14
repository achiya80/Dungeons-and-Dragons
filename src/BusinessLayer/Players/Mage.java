package BusinessLayer.Players;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Resources.Mana;
import BusinessLayer.Tiles.Unit;

import java.util.List;

public class Mage extends Player {

    private static final int MANA_POOL_BONUS = 25;
    private static final int SPELL_POWER_BONUS = 10;
    private int hitsCount;
    private Mana mana;

    public Mage(String name, int healthPool, int attack, int defense
            , int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthPool, attack, defense, "Blizzard");
        setAbilityDamage(() -> getMana().getSpellPower());
        this.hitsCount = hitsCount;
        this.mana = new Mana(manaPool, abilityRange,manaCost, spellPower);
    }

    public Mana getMana() {
        return mana;
    }

    @Override
    public void onPlayerTurn() {
        getMana().onGameTick(getLevel());
    }

    @Override
    public void castAbility(Player player, List<Enemy> enemies) {
        if(getMana().isAbleToCast()) {
            getMana().onAbilityCast();
            List<Enemy> inRange = getMana().filterRange(getPosition(), enemies);
            int hits = 0;
            messageCallback.send(String.format("%s cast %s", getName(), getABILITY_NAME()));
            while (hits++ < this.hitsCount && !inRange.isEmpty()) {
                Enemy e = inRange.get(ng.generate(inRange.size()));
                abilityDamage(e);
                if (!e.alive()) inRange.remove(e);
            }
        }
        else{
            onPlayerTurn();
            messageCallback.send(String.format("%s tried to cast %s, but there was not enough %s: %d/%d", getName(),getABILITY_NAME(),getMana().getResourceName(),getMana().getResourceAmount(),getMana().getCost()));
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        getMana().setResourcePool(gainManaPool());
        getMana().uponLevelingUp();
        getMana().setSpellPower(gainSpellPower());
        messageCallback.send(String.format("+%d Mana Pool +%d Spell Power", gainManaPool(), gainSpellPower()));
    }

    protected int gainSpellPower(){
        return getLevel()*SPELL_POWER_BONUS;
    }

    protected int gainManaPool(){
        return getLevel()*MANA_POOL_BONUS;
    }

    @Override
    public String describe(){
        return String.format("%s\t\t %s: %s", super.describe(), getMana().getResourceName(),getMana());
    }


}
