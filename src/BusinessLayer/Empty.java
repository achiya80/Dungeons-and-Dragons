package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

public class Empty extends Tile{


    public Empty(Position position){
        super('.');
        super.initialize(position);
    }


    @Override
    public void accept(Visitor v) {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }
}
