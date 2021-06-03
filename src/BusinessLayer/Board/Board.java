package BusinessLayer.Board;

import BusinessLayer.Tiles.Tile;

import java.util.*;

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
        String board = "";
        Tiles.sort(comparator);
        for(int i = 0;i < Tiles.size();i++){
            board+=Tiles.get(i).toString();
            if(i < Tiles.size() - 1){
                if(Tiles.get(i).getPosition().getY() < Tiles.get(i+1).getPosition().getY()){
                    board += "\n";
                }
            }
        }
        return board;
    }

    public void addTile(Tile t){
        Tiles.add(t);
    }


    public void removeTile(Tile t){
        Tiles.remove(t);
    }



}
