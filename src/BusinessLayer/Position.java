package BusinessLayer;

public class Position implements Comparable<Position> {

    private int X;
    private int Y;


    public Position(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public double Range(Position p){
        return Math.sqrt(RangeX(p)*RangeX(p)+RangeY(p)*RangeY(p));
    }
    public int RangeX(Position p){
        return X-p.X;
    }
    public int RangeY(Position p){
        return Y-p.Y;
    }


    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    @Override
    public int compareTo(Position o) {
        return (X < o.X) ? -1 : ((X > o.X) ? 1 : ((Y < o.Y) ? -1 : ((Y > o.Y) ? 1 : 0)));
    }
}
