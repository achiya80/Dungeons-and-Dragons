package BusinessLayer.Enemies;

import BusinessLayer.ActionHandler.Movement;
import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;

import java.util.List;

import BusinessLayer.ActionHandler.*;

public class Monster extends Enemy{

    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, healthPool, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void performAction(Player player, List<Enemy> enemies) {
        if (player.getPosition().Range(this.getPosition())<visionRange) {
            positionCallback.Move(actionsMap.get(playerTrackPattern(player)).get());
        }
        else {
            positionCallback.Move(actionsMap.get(Movement.randomMovement()).get());
        }
    }

    protected char playerTrackPattern(Player player){
        int dX= getPosition().RangeX(player.getPosition());
        int dY= getPosition().RangeY(player.getPosition());
        return (Math.abs(dX)>Math.abs(dY)) ? ((dX > 0) ? Movement.left : Movement.right) : ((dY > 0) ? Movement.up : Movement.down);
    }

}
