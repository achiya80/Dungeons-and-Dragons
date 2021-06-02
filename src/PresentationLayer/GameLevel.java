package PresentationLayer;

import BusinessLayer.Board.Board;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class GameLevel {

    private static final String LEVEL = "\\level";
    private static final String PATH = ".txt";
    private static int level = 1;
    private static Scanner reader = new Scanner(System.in);


    private Board gameBoard;
    private Player player;
    private static boolean playerLost = false;
    private List<Enemy> enemies = new ArrayList<>();

    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }


    public void onPlayerDeath(){
        System.out.println(this);
    }

    public void onEnemyDeath(Enemy e){
        enemies.remove(e);
        gameBoard.removeTile(e);
        gameBoard.addTile(new TileFactory().produceEmpty(e.getPosition()));
    }

    public boolean levelEnded(){
        return enemies.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n", gameBoard, player.describe());
    }

    public static void main(String[] args){
        args = new String[1];
        args[0] = "C:\\Users\\achiy\\levels_dir";

        System.out.println("choose from players");
        TileFactory tileFactory = new TileFactory();
        AtomicInteger i = new AtomicInteger(1);
        tileFactory.listPlayers().stream().forEach(p -> System.out.println((i.getAndIncrement()) + ".  " + p.describe()));
        int select = reader.nextInt();
        Player player = tileFactory.producePlayer(select-1);
        GameLevel gameLevel = null;
        File tempFile = new File(args[0] + LEVEL + level + PATH);
        while(!playerLost && tempFile.exists()) {
            char[][] board = FileParser.readAllLines(tempFile);
            gameLevel = GameInitializer.Initialize(board, player);
            gameLevel.startLevel();
            level++;
            tempFile = new File(args[0] + LEVEL + level + PATH);
        }
        if(!playerLost){
            System.out.println("you won!!!");
        }
    }



    public void startLevel(){
        while(!playerLost && levelEnded()){
            playerLost = player.getHealth().getResourceAmount() <= 0;
            System.out.println(this);
            if(!playerLost) {
                String s = reader.nextLine();
                while (s.length() != 1) {
                    s = reader.nextLine();
                }
                char c = s.charAt(0);
                player.preformAction(c, player, enemies);
                playerLost = player.getHealth().getResourceAmount() <= 0;
            }
        }
        System.out.println(this);

    }


}
