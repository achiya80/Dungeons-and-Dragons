package BusinessLayer.Board;

import BusinessLayer.*;

import java.util.*;

public class Board {
    Comparator<Tile> comparator = new Comparator<Tile>() {public int compare(Tile o1, Tile o2) { return o1.compareTo(o2); }};
    private List<Tile> Tiles = new ArrayList<>();

    public Board(){

    }

    public Tile getTile(Position position){
        Tile tile = Tiles.stream().filter(t -> t.getPosition().compareTo(position) == 0).findFirst().get();
        System.out.println(tile);
        System.out.println(tile.getPosition().getX());
        System.out.println(tile.getPosition().getY());
        return tile;
    }

    public String toString(){
        String board = "";
        Tiles.sort(comparator);
        for(int i = 0;i < Tiles.size();i++){
            board+=Tiles.get(i).toString();
            if(i < Tiles.size() - 1){
                if(Tiles.get(i).getPosition().getX() < Tiles.get(i+1).getPosition().getX()){
                    board += "\n";
                }
            }
        }
        return board;
    }

    public void addTile(Tile t){
        Tiles.add(t);
    }





}
