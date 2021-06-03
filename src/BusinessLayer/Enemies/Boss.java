package BusinessLayer.Enemies;

import BusinessLayer.AbilityInterfaces.HeroicUnit;
import BusinessLayer.Board.Position;
import BusinessLayer.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

import java.util.List;

import static BusinessLayer.ActionHandler.Movement.randomMovement;

public class Boss extends Monster implements HeroicUnit {
    private Integer abilityFrequency;
    private Integer combatTicks;


    public Boss(char tile, String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defensePoints, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void preformAction(Player player) {
        int move;
        if (player.getPosition().Range(this.getPosition())<visionRange)
        {
            if(combatTicks==abilityFrequency){
                combatTicks=0;
                //castAbility();
            }
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
            combatTicks=0;
            move=randomMovement();
            actionsMap.get(move);
        }

    }
    @Override
    public void castAbility(Player player, List<Enemy> enemies) {

    }


    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void accept(Visitor v) {

    }
}
