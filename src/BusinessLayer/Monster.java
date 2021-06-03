package BusinessLayer;

import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

public class Monster extends Enemy{

    protected int visionRange;


    public Monster(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, healthPool, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }
    public void preformAction(Player player) {
        int move;
        if (player.getPosition().Range(this.getPosition())<visionRange)
        {
            int dX= this.getPosition().getX()-player.getPosition().getX();
            int dY=this.getPosition().getY()-player.getPosition().getY();
            if (dX>dY) {
                if (dX > 0)
                    positionCallback.Move(new Position(getPosition().getX() - 1, getPosition().getY()));
                else
                    positionCallback.Move(new Position(getPosition().getX() + 1, getPosition().getY()));
            }
            else
            {
                if(dY>0)
                    positionCallback.Move(new Position(getPosition().getX() , getPosition().getY()+ 1));
                else
                    positionCallback.Move(new Position(getPosition().getX() , getPosition().getY()- 1));
            }

        }
        else {
            move = (int) (Math.random() * 5);
            if(move==1)
                positionCallback.Move(new Position(getPosition().getX() - 1, getPosition().getY()));
            if(move==2)
                positionCallback.Move(new Position(getPosition().getX() + 1, getPosition().getY()));
            if(move==3)
                positionCallback.Move(new Position(getPosition().getX(), getPosition().getY() - 1));
            if(move==4)
                positionCallback.Move(new Position(getPosition().getX(), getPosition().getY() + 1));
        }

    }

}
