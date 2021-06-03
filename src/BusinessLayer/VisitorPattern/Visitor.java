package BusinessLayer.VisitorPattern;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Tiles.BarbedWall;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.HiddenMine;
import BusinessLayer.Tiles.Wall;

public interface Visitor {
    public void visit(Empty e);
    public void visit(Wall w);
    public void visit(Player p);
    public void visit(Enemy e);
    public void visit(BarbedWall b);
    public void visit(HiddenMine h);
}
