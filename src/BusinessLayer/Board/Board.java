package BusinessLayer.Board;

import BusinessLayer.Tiles.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private Comparator<Tile> comparator = (o1, o2) -> o1.compareTo(o2);

    private List<Tile> Tiles;

    public Board(){ Tiles = new ArrayList<>(); }

    public Tile getTile(Position position){
        return Tiles.stream().filter(t -> t.getPosition().compareTo(position) == 0).findFirst().get();
    }

    public void addTile(Tile t){
        Tiles.add(t);
    }


    public void removeTile(Tile t){
        Tiles.remove(t);
    }

    public String toString(){
        Tiles.sort(comparator);
        int limX = Tiles.stream().max(comparator).stream().findFirst().get().getPosition().getX();
        return Tiles.stream().map(t -> t.getPosition().getX() == limX ? t.toString()+"\n" : t.toString()).collect(Collectors.joining());
    }
    public Tile[][] toTileArray(){
        Tiles.sort(comparator);
        int limX = Tiles.stream().max(comparator).stream().findFirst().get().getPosition().getX();
        int limY = Tiles.stream().max(comparator).stream().findFirst().get().getPosition().getY();
        Tile[][] tileArr = new Tile[limX+1][limY+1];
        for (Tile t: Tiles){ tileArr[t.getPosition().getX()][t.getPosition().getY()] = t; }
        return tileArr;
    }





}
