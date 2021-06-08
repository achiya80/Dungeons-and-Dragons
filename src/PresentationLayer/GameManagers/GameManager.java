package PresentationLayer.GameManagers;

import BusinessLayer.Players.Player;
import PresentationLayer.FileHandler.FileParser;
import PresentationLayer.FileHandler.TileFactory;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {

    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args){
        //if(args.length == 0){
        ///     throw new Exception("input files directory");
        //  }
        args = new String[1];
        args[0] = "C:\\Users\\achiy\\levels_dir";
        System.out.println("choose from players");
        TileFactory tileFactory = new TileFactory();
        AtomicInteger i = new AtomicInteger(1);
        tileFactory.listPlayers().stream().forEach(p -> System.out.println((i.getAndIncrement()) + ".  " + p.describe()));
        int select = reader.nextInt();
        Player player = tileFactory.producePlayer(select-1);
        GameLevel gameLevel = null;
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        for (File currLevel : listOfFiles) {
            char[][] board = FileParser.readAllLines(currLevel);
            gameLevel = GameInitializer.Initialize(board, player);
            gameLevel.startLevel();
            if(gameLevel.gameEnded()) break;
        }
        if(!gameLevel.gameEnded()){
            System.out.println("you won!!!");
        }

    }





}
