package PresentationLayer.FileHandler;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;

public class FileParser {



    public static char[][] readAllLines(File path) {
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


    private static char[][] ConvertListToArray(List<String> l){
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
