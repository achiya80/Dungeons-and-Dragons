package BusinessLayer.Tiles;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visitor;

public class Empty extends Tile{

    public static final char EMPTY_TILE = '.';

    public Empty(Position position){
        super(EMPTY_TILE);
        super.initialize(position);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void visit(Empty e) { }
}
