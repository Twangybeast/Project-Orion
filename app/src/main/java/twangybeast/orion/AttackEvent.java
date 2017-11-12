package twangybeast.orion;

/**
 * Created by Twangybeast on 11/11/2017.
 */

public class AttackEvent
{
    int attackingStart;
    int defendingStart;
    int planetDefense;
    int attackingLoss;
    int defendingLoss;
    boolean successfulCapture;
    public AttackEvent(int attackingStart, int defendingStart, int planetDefense, int attackingLoss, int defendingLoss, boolean successfulCapture)
    {
        this.attackingStart = attackingStart;
        this.defendingStart = defendingStart;
        this.planetDefense = planetDefense;
        this.attackingLoss = attackingLoss;
        this.defendingLoss = defendingLoss;
        this.successfulCapture = successfulCapture;
    }

    public int getAttackingStart()
    {
        return attackingStart;
    }

    public int getDefendingStart()
    {
        return defendingStart;
    }

    public int getPlanetDefense()
    {
        return planetDefense;
    }

    public int getAttackingLoss()
    {
        return attackingLoss;
    }

    public int getDefendingLoss()
    {
        return defendingLoss;
    }

    public boolean isSuccessfulCapture()
    {
        return successfulCapture;
    }
}
