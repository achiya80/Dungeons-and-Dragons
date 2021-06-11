package PresentationLayer.GameManagers;

import BusinessLayer.Board.Board;
import BusinessLayer.Board.Position;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Wall;
import PresentationLayer.FileHandler.TileFactory;

public class GameInitializer {

    public static GameLevel Initialize(char[][] c, Player player){
        GameLevel level = new GameLevel();
        Board board = new Board();
        level.setGameBoard(board);
        level.setPlayer(player);
        TileFactory tileFactory = new TileFactory();
        for(int i = 0;i < c.length;i++){
            for(int j = 0;j < c[0].length;j++){
                Position position = new Position(j, i);
                char tile = c[i][j];
                if(tile == Empty.EMPTY_TILE){
                    Empty e = tileFactory.produceEmpty(position);
                    board.addTile(e);
                }
                else if(tile == Wall.WALL_TILE){
                    Wall w = tileFactory.produceWall(position);
                    board.addTile(w);
                }
                else if(tile == Player.PLAYER_TILE){
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
