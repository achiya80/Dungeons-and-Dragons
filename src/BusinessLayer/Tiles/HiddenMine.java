package BusinessLayer.Tiles;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public class HiddenMine extends Tile{


    private boolean explode;

    public HiddenMine(char tile) {
        super(tile);
        this.explode = false;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) {

    }
}
