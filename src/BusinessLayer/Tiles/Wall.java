package BusinessLayer.Tiles;

import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visitor;

public class Wall extends Tile{

    public static final char WALL_TILE = '#';

    public Wall(Position position){
        super(WALL_TILE);
        super.initialize(position);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void visit(Empty e) { }

}
