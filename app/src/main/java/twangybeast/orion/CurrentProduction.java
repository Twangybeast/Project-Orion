package twangybeast.orion;

/**
 * Created by Twangybeast on 11/11/2017.
 */

public class CurrentProduction
{
    String prodName;
    int progress;
    int cost;
    public CurrentProduction(String prodName, int cost)
    {
        this.prodName = prodName;
        progress = 0;
        this.cost = cost;
    }
}
