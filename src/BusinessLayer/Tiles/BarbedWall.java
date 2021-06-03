package BusinessLayer.Tiles;

import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

import java.awt.*;

public class BarbedWall extends Wall{

    public BarbedWall(Position position) {
        super(position);
    }

    public int stub(){
        return (int) (Math.random()*50);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
