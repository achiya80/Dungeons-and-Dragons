package BusinessLayer.Enemies;

import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;

import java.util.List;

import static BusinessLayer.ActionHandler.Movement.randomMovement;

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
            move=randomMovement();
            actionsMap.get(move);

        }

    }

}
