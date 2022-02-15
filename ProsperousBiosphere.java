package Assignment4;

/* As needed students can write additional methods and add attributes */

public class ProsperousBiosphere extends Biosphere // Students can't change this inheritance relation.
{
    //Students can make any changes except for the relationship.
    public ProsperousBiosphere(Critter [][] aWorld)
    {
        super(aWorld);  
    }  

    protected void critterBirth(int r, int c, int neighbours, Critter[][] current, Critter[][] birthsAndDeaths) //alternative rules for birth
    {
        if (current[r][c].getAppearance() == Critter.EMPTY & neighbours == 4)
        {
            birthsAndDeaths[r][c] = new Critter();    //birth
        }
    }

    protected boolean critterCondition(int neighbourCounter) //alternative conditional for death
    {
        if (neighbourCounter > 0 & neighbourCounter < 5)
        {
            return(true);
        }
        return(false);
    }
}
