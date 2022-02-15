package Assignment4;

/* As needed students can write additional methods and add attributes */
import java.util.Random;

import jdk.internal.vm.compiler.word.LocationIdentity;

public class Taminator extends Critter
{
    //Class attribute by James Tam. Students do not make any changes to it but
    //additional attributes may be added.
    public static final char DEFAULT_APPEARANCE = 'T';
    private Random rand; 

    //Start of modifiable code written by James Tam
    //Taminator(), Taminator(char)
    //Students can make any changes.
    public Taminator()
    {
	    super(DEFAULT_APPEARANCE);
        rand = new Random();
    }

    public Taminator(char newAppearance)
    {
	    super(newAppearance);
        rand = new Random();
    }
    //End of modifiable code written by James Tam

    //Students can add additional methods below as needed. 
    public Location[] checkForCritters(int r, int c, Critter[][] critters)     //checks adjacent positions for # of neighbours
    {
        Location [] taminateCritterLocations = new Location [Biosphere.MAX_TAMINATION];

        if ((r-1) >= 0 & (r-1) <= 9) {  //1 up
            if (critters[r-1][c].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[0] = new Location((r-1), c);
            }
        } 
        if ((r-1) >= 0 & (r-1) <= 9 & (c-1) >= 0 & (c-1) <= 9) {   //1 up 1 left
            if (critters[r-1][c-1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[1] = new Location((r-1), (c-1));
            }
        }
        if ((r-1) >= 0 & (r-1) <= 9 & (c+1) >= 0 & (c+1) <= 9) {   //1 up 1 right
            if (critters[r-1][c+1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[2] = new Location((r-1), (c+1));
            }
        }
        if ((c-1) >= 0 & (c-1) <= 9) { //1 left
            if (critters[r][c-1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[3] = new Location(r, (c-1));
            }
        }
        if ((c+1) >= 0 & (c+1) <= 9) { //1 right
            if (critters[r][c+1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[4] = new Location(r, (c+1));
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9 & (c-1) >= 0 & (c-1) <= 9) {    //1 down 1 left
            if (critters[r+1][c-1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[5] = new Location((r+1), (c-1));
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9) { //1 down
            if (critters[r+1][c].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[6] = new Location((r+1), c);
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9 & (c+1) >= 0 & (c+1) <= 9) {   //1 down 1 right
            if (critters[r+1][c+1].getAppearance() == Critter.DEFAULT_APPEARANCE) {
                taminateCritterLocations[7] = new Location((r+1), (c+1));
            }   
        }
        return(taminateCritterLocations);
    }

    public Location randomTeleportation(Critter[][] taminator, Location tamLocation)
    {
        int currentRow = tamLocation.getRow();
        int currentColumn = tamLocation.getColumn();

        int newRow = rand.nextInt(10);
        int newColumn = rand.nextInt(10);

        while (newRow == currentRow | newColumn == currentColumn)
        {
            newRow = rand.nextInt(10);
            newColumn = rand.nextInt(10);
        }
        tamLocation.setRow(newRow);
        tamLocation.setColumn(newColumn);
        return(tamLocation);
    }

}