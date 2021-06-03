package PresentationLayer.GameManagers;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Board;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import PresentationLayer.FileHandler.FileParser;
import PresentationLayer.FileHandler.TileFactory;

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
    private static boolean playerLost = false;


    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            throw new Exception("input files directory");
        }
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





    private Board gameBoard;
    private Player player;
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


    public void startLevel(){
        while(!playerLost && !levelEnded()){
            playerLost = !player.alive();
            System.out.println(this);
            if(!playerLost) {
                String s = reader.nextLine();
                while (!Movement.getMoves().contains(s)) {
                    s = reader.nextLine();
                }
                char c = s.charAt(0);
                player.preformAction(c, enemies);
                playerLost = !player.alive();
            }
        }

    }






}
