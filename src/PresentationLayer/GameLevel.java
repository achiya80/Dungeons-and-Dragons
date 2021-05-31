package PresentationLayer;

import BusinessLayer.Board.Board;
import BusinessLayer.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        playerLost = true;
    }

    public void onEnemyDeath(Enemy e){
        enemies = enemies.stream().filter(enemy -> (enemy.getPosition().compareTo(e.getPosition()) != 0)).collect(Collectors.toList());
    }

    public boolean levelEnded(){
        return enemies.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", gameBoard, player.describe());
    }

    public static void main(String[] args){
        args = new String[1];
        args[0] = "C:\\Users\\achiy\\levels_dir";

        System.out.println("choose from players");
        TileFactory tileFactory = new TileFactory();
        tileFactory.listPlayers().stream().forEach(p -> System.out.println(p.describe()));
        int select = reader.nextInt();
        Player player = tileFactory.producePlayer(select);
        GameLevel gameLevel = null;
        while(level < 5 && !playerLost) {
            char[][] board = readAllLines(args[0] + LEVEL + level + PATH);
            gameLevel = GameInitializer.Initialize(board, player);
            gameLevel.startLevel();
            level++;
        }
        if(!playerLost){
            System.out.println("you won!!!");
        }
    }



    public void startLevel(){
        while(!levelEnded() && !playerLost){
            String s = reader.nextLine();
            while(s.length() != 1){
                s = reader.nextLine();
            }
            char c = s.charAt(0);
            player.performAction(c, enemies);
        }

    }

    public static char[][] readAllLines(String path) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(path));
            String next;
            while ((next = reader.readLine()) != null) {
                lines.add(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return ConvertListToArray(lines);
    }


    public static char[][] ConvertListToArray(List<String> l){
        if(l.size() == 0) return null;
        char[][] c = new char[l.size()][l.get(0).length()];
        int i = 0;
        for(String s : l){
            for(int j = 0; j< c[0].length;j++){
                c[i][j] = s.charAt(j);
            }
            i++;
        }
        return c;

    }

}
