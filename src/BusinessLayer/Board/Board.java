package BusinessLayer.Board;

import BusinessLayer.Tiles.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    Comparator<Tile> comparator = new Comparator<Tile>() {public int compare(Tile o1, Tile o2) { return o1.compareTo(o2); }};
    private List<Tile> Tiles = new ArrayList<>();

    public Board(){

    }

    public Tile getTile(Position position){
        Tile tile = Tiles.stream().filter(t -> t.getPosition().compareTo(position) == 0).findFirst().get();
        return tile;
    }

    public String toString(){
        Tiles.sort(comparator);
        int limX = Tiles.stream().max(comparator).stream().findFirst().get().getPosition().getX();
        return Tiles.stream().map(t -> t.getPosition().getX() == limX ? t.toString()+"\n" : t.toString())
                .reduce("", (acc, str) -> acc+str);
    }

    public void addTile(Tile t){
        Tiles.add(t);
    }


    public void removeTile(Tile t){
        Tiles.remove(t);
    }



}
