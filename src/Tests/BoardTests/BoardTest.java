package Tests.BoardTests;

import BusinessLayer.Board.Board;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board b;
    @BeforeEach
    void setUp() {
        b = new Board();
        List<Tile> tiles = Arrays.asList(new Empty(new Position(1,0)), new Wall(new Position(1,1)),
                        new Empty(new Position(0,0)),new Wall(new Position(0,1)));
        tiles.stream().forEach(t -> b.addTile(t));
    }

    @Test
    void testToStringNotSorted() {
        String expected = "..\n##\n";
        assertEquals(expected, b.toString(), "the board to string isn't compatabole to expected");
    }

    @Test
    void testToStringSorted() {
        b = new Board();
        List<Tile> tiles = Arrays.asList(new Empty(new Position(0,0)),new Empty(new Position(1,0)) ,
                new Wall(new Position(0,1)) ,new Wall(new Position(1,1)));
        tiles.stream().forEach(t -> b.addTile(t));
        String expected = "..\n##\n";
        assertEquals(expected, b.toString(), "the board to string isn't compatabole to expected");
    }

}