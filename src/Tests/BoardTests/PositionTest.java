package Tests.BoardTests;

import BusinessLayer.Board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position p1;
    private Position p2;
    private Position p3;
    private Position p4;
    private Position p5;

    @BeforeEach
    void setUp() {
        p1 = new Position(1,2);
        p2 = new Position(3,2);
        p3 = new Position(4, 6);
        p4 = new Position(0,4);
        p5 = new Position(4, 0);
    }

    @Test
    void range() {
        assertEquals(2 ,p1.Range(p2), "range was not as expected");
    }

    @Test
    void rangeX() {
        assertEquals(0, p3.RangeX(p3),  "range was not as expected");
    }

    @Test
    void rangeY() {
        assertEquals(4, p4.RangeY(p5),"range was not as expected");
    }

    @Test
    void up() {
        assertEquals(new Position(1,1),p1.Up(), "position was not as expected");
    }

    @Test
    void down() {
        assertEquals(new Position(3,3),p2.Down(), "position was not as expected");
    }

    @Test
    void left() {
        assertEquals(new Position(3,6),p3.Left(), "position was not as expected");
    }

    @Test
    void right() {
        assertEquals(new Position(1,4),p4.Right(), "position was not as expected");
    }

    @Test
    void noOperation() {
        assertEquals(new Position(4,0),p5.NoOperation(), "position was not as expected");
    }

    @Test
    void compareTo() {
        assertEquals(-1, p1.compareTo(p2), "position was not as expected");
        assertEquals(1, p4.compareTo(p5), "position was not as expected");
        assertEquals(0, p3.compareTo(p3), "position was not as expected");
    }
}