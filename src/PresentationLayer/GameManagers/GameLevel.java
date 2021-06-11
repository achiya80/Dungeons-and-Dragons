package PresentationLayer.GameManagers;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Board;
import BusinessLayer.Enemies.Enemy;
import BusinessLayer.Players.Player;
import PresentationLayer.FileHandler.FileParser;
import PresentationLayer.FileHandler.TileFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameLevel {

    private static Scanner reader = new Scanner(System.in);
    private Board gameBoard;
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void startLevel(){
        while(!gameEnded() && !levelEnded()){
            System.out.println(this);
            if(!gameEnded()) {
                String s = reader.nextLine();
                while (!Movement.getMoves().contains(s)) {
                    s = reader.nextLine();
                }
                char c = s.charAt(0);
                player.performAction(c, enemies);
                for(Enemy e : enemies) e.performAction(player, enemies);
            }
        }

    }

    public void addEnemy(Enemy e){
        enemies.add(e);
    }

    public void onPlayerDeath(){
        System.out.println(this);
        System.out.println("Game Over");
    }

    public void onEnemyDeath(Enemy e){
        enemies.remove(e);
        gameBoard.removeTile(e);
        gameBoard.addTile(new TileFactory().produceEmpty(e.getPosition()));
    }

    public boolean levelEnded(){
        return enemies.isEmpty();
    }


    public boolean gameEnded(){
        return !player.alive();
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n", gameBoard, player.describe());
    }







}
