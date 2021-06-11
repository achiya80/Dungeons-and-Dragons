package BusinessLayer.Tiles;

import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;
import com.sun.jdi.Value;

public abstract class Tile implements Visited, Visitor, Comparable<Tile> {

    private Position position;
    private final char tile;

    protected Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    protected void swapPositions(Tile t){
        Position temp = t.position;
        t.position = position;
        position = temp;
    }

    @Override
    public int compareTo(Tile t){
        return position.compareTo(t.position);
    }

    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) { }

    @Override
    public void visit(Empty e){
        swapPositions(e);
    }

    @Override
    public void visit(Wall w){ }

    @Override
    public String toString(){
        return String.valueOf(tile);
    }
}
