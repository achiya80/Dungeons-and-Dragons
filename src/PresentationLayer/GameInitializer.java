package PresentationLayer;

import BusinessLayer.Board.Board;
import BusinessLayer.*;
import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callback.MessageCallback;

import java.util.ArrayList;

public class GameInitializer {

    public static GameLevel Initialize(char[][] c, Player player){
        GameLevel level = new GameLevel();
        Board board = new Board();
        level.setGameBoard(board);
        level.setPlayer(player);
        TileFactory tileFactory = new TileFactory();
        for(int i = 0;i < c.length;i++){
            for(int j = 0;j < c[0].length;j++){
                Position position = new Position(i, j);
                char tile = c[i][j];
                if(tile == '.'){
                    Empty e = tileFactory.produceEmpty(position);
                    board.addTile(e);
                }
                else if(tile == '#'){
                    Wall w = tileFactory.produceWall(position);
                    board.addTile(w);
                }
                else if(tile == '@'){
                    player.initialize(position, (msg) -> System.out.println(msg),() -> level.onPlayerDeath(), (pos) -> player.interact(board.getTile(pos)));
                    board.addTile(player);
                }
                else {
                    Enemy e = tileFactory.produceEnemy(tile, position,
                            (msg) -> System.out.println(msg));
                    e.setDeathCallback(() -> level.onEnemyDeath(e));
                    e.setPositionCallback((p) -> e.interact(board.getTile(p)));
                    board.addTile(e);
                    level.addEnemy(e);
                }
            }
        }
        return level;
    }




}
