package BusinessLayer.Tiles;

import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;

public abstract class Tile implements Visited, Visitor, Comparable<Tile> {


    private Position position;
    private final char tile;



    protected Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public int compareTo(Tile t){
        return position.compareTo(t.position);
    }

    public void visit(Empty e){
        swapPositions(e);
    }

    protected void swapPositions(Tile t){
        Position temp = t.position;
        t.position = position;
        position = temp;
    }


    public void visit(Wall w){ }

    @Override
    public String toString(){
        return "" + tile;
    }
    public char getTile() {
        return tile;
    }
    public Position getPosition() {
        return position;
    }
}
