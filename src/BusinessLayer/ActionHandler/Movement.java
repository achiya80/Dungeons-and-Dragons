package BusinessLayer.ActionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Movement {

    public static char castAbility = 'e';
    public static char up = 'w';
    public static char down = 's';
    public static char left = 'a';
    public static char right = 'd';
    public static char stay = 'q';

    private static List<Character> moves = Arrays.asList(up, down, left, down, right, stay, castAbility);


    public static List<String> getMoves(){
        return moves.stream().map(c -> String.valueOf(c)).collect(Collectors.toList());
    }
    public static char randomMovement(){
        return moves.get(((int)Math.random()*moves.size()-1));
    }
}
