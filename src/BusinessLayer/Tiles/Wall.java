package BusinessLayer.Tiles;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visitor;

public class Wall extends Tile{


    public Wall(Position position){
        super('#');
        super.initialize(position);
    }



    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) { }

    @Override
    public void visit(Empty e) { }

}
