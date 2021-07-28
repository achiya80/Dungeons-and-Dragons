package PresentationLayer.GameUI;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;
import PresentationLayer.FileHandler.FileParser;
import PresentationLayer.FileHandler.TileFactory;
import PresentationLayer.GameManagers.GameInitializer;
import PresentationLayer.GameManagers.GameLevel;
import PresentationLayer.GameManagers.GameManager;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameUIManager {


    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        ///if(args.length == 0){
        //       throw new IllegalArgumentException("input levels directory");
        //  }
        String input = JOptionPane.showInputDialog("please insert path to levels files","C:\\Users\\achiy\\levels_dir" );
        args = new String[1];
        args[0] = "C:\\Users\\achiy\\levels_dir";
        args[0] = input;
       // System.out.println("choose from players");
        TileFactory tileFactory = new TileFactory();
        AtomicInteger i = new AtomicInteger(1);
        List<String> selected = new ArrayList<>();
        String players = tileFactory.listPlayers().stream().map(p -> ""+i.getAndIncrement() + ".  " + Arrays.stream(p.describe().split("\\s+")).map(t -> t+" ").collect(Collectors.joining()) + "\n").collect(Collectors.joining());
        i.set(1);
        tileFactory.listPlayers().stream().forEach(p -> selected.add(String.format("%d", i.getAndIncrement())));
        String select = JOptionPane.showInputDialog("choose from players\n" + players);
        while(!selected.contains(select)){
            new GameUILevel().messageBox("please try again", "Not a member");
            i.set(1);
            select = JOptionPane.showInputDialog("choose from players\n" + players);
           // tileFactory.listPlayers().stream().forEach(p -> System.out.println((i.getAndIncrement()) + ".  " + p.describe()));
            //select = reader.nextLine();
        }
        Player player = tileFactory.producePlayer(Integer.valueOf(select)-1);
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        new GameUIManager().runGame(listOfFiles, player);

    }


    public void runGame(File[] listOfFiles, Player player){
        GameUILevel gameLevel = null;
        for (File currLevel : listOfFiles) {
            char[][] board = FileParser.readAllLines(currLevel);
            gameLevel = GameUIInitializer.Initialize(board, player);
            gameLevel.init();
            while (!gameLevel.levelEnded() && !gameLevel.gameEnded()){ }
            if(gameLevel.gameEnded()) break;
        }
        if(!gameLevel.gameEnded()){
            gameLevel.messageBox("you won!!!", "Game Ended");
        }
        System.exit(0);
    }


}
