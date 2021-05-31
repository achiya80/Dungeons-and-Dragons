package BusinessLayer.VisitorPattern;

import BusinessLayer.*;

public interface Visitor {
    public void visit(Empty e);
    public void visit(Wall w);
    public void visit(Player p);
    public void visit(Enemy e);

}
