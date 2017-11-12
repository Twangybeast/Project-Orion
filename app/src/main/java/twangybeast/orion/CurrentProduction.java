package twangybeast.orion;

/**
 * Created by Twangybeast on 11/11/2017.
 */

public class CurrentProduction
{
    String prodName;
    int progress;
    int cost;
    int turnNum;
    public CurrentProduction(String prodName, int cost, int turnNum)
    {
        this.turnNum = turnNum;
        this.prodName = prodName;
        progress = 0;
        this.cost = cost;
    }
}
