package PresentationLayer.GameManagers;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;
import PresentationLayer.FileHandler.FileParser;
import PresentationLayer.FileHandler.TileFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class GameManager {

    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        ///if(args.length == 0){
     //       throw new IllegalArgumentException("input levels directory");
      //  }
        Map<Character, Supplier<Position>> actionsMap = new HashMap<>(){
            {
                put(Movement.down, () -> new Position(0,0).Down());
                put(Movement.up, () -> new Position(0,0).Up());
                put(Movement.right, () -> new Position(0,0).Right());
                put(Movement.left, () -> new Position(0,0).Left());
                put(Movement.stay, () -> new Position(0,0).NoOperation());
            }
        };

        args = new String[1];
        args[0] = "C:\\Users\\achiy\\levels_dir";
        System.out.println("choose from players");
        TileFactory tileFactory = new TileFactory();
        AtomicInteger i = new AtomicInteger(1);
        List<String> selected = new ArrayList<>();
        tileFactory.listPlayers().stream().forEach(p -> System.out.println((i.getAndIncrement()) + ".  " + p.describe()));
        i.set(1);
        tileFactory.listPlayers().stream().forEach(p -> selected.add(String.format("%d", i.getAndIncrement())));
        String select = reader.nextLine();
        while(!selected.contains(select)){
            System.out.println("not a member");
            i.set(1);
            tileFactory.listPlayers().stream().forEach(p -> System.out.println((i.getAndIncrement()) + ".  " + p.describe()));
            select = reader.nextLine();
        }
        Player player = tileFactory.producePlayer(Integer.valueOf(select)-1);
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        new GameManager().runGame(listOfFiles, player);

    }


    public void runGame(File[] listOfFiles, Player player){
        GameLevel gameLevel = null;
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
