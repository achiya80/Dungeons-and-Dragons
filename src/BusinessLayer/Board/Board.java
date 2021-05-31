package BusinessLayer.Board;

import BusinessLayer.*;

import java.util.*;

public class Board {
    Comparator<Tile> comparator = new Comparator<Tile>() {public int compare(Tile o1, Tile o2) { return o1.compareTo(o2); }};
    private List<Tile> Tiles = new ArrayList<>();
    Player player;

    public Board(){

    }

    public Tile getTile(Position position){
        Tile tile = Tiles.stream().filter(t -> t.getPosition().compareTo(position) == 0).findFirst().get();
        return tile;
    }

    public void printBoard(){
        Tiles.sort(comparator);

    }

    public void addTile(Tile t){
        Tiles.add(t);
    }





}
