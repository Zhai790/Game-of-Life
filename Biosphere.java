package Assignment4;

import java.util.Scanner; 

/* As needed students can write additional methods and add attributes */

public class Biosphere
{
    //Constant declarations and attributes by James Tam, don't change.
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    public static final int CRITTERS = 1;
    public static final int BLANKS = 2;
    public static final int TAMINATOR = 3;
    public static final int MAX_TAMINATION = 8;
    private Critter [][] current; 
    private Critter [][] birthsAndDeaths;
    private Critter [][] taminator;
    private boolean continueSimulation = true;
    private Location taminatorLocation = new Location(0, 0);
    private Location[] removeCritters;
    private boolean instanceOfTam = false;


    //Original code written by James Tam, don't modify
    public Biosphere(Critter [][] aWorld)
    {
        //Original code
        current = aWorld;

        //Student code
        birthsAndDeaths = new Critter[ROWS][COLUMNS];
        taminator = new Critter[ROWS][COLUMNS];

        initializeBiosphere(birthsAndDeaths);
        initializeBiosphere(taminator);
    }

    private void generateColumns(Critter[][] arrayInfo, int r)
    {
        int c;
        System.out.print(r); //Line # before each row
        for (c = 0; c < COLUMNS; c++)
        {
            System.out.print("|" + arrayInfo[r][c]); //Bounding line left of array element
        }
        System.out.print("|"); //Bounding line at the end of the row for the last element
        System.out.print("        ");
    }

    private void generateBoundingRows()
    {
        for (int i = 0; i < ROWS; i++)
            {
                System.out.print(" -");  //Bounding line below each array element
            }
    }

    //The code used by the student was based on the display code written by James Tam
    public void display()
    { 
        //Original code below: student can freely modify to fulfill assignment requirements
        //(add, delete, change existing code).
        
	    System.out.println("  PREVIOUS GENERATION           BIRTHS & DEATHS               TAMINATOR");
	    System.out.println(HORIZONTAL_COUNT + "        " + HORIZONTAL_COUNT + "        " + HORIZONTAL_COUNT);
        System.out.print(" ");
        for (int e = 0; e < 3; e++)
        {
            for (int i = 0; i < ROWS; i++)
            {
                System.out.print(" -"); //Line of dashes before the array
            }
            System.out.print("          ");
        }
        System.out.println();
        for (int r = 0; r < ROWS; r++)
        {
            generateColumns(current, r);
            generateColumns(birthsAndDeaths, r);
            generateColumns(taminator, r);

            System.out.println();
            System.out.print(" ");
            for (int e = 0; e < 3; e++)
            {
                generateBoundingRows();
                System.out.print("          ");
            }
            System.out.println();
        }
    }

    //Original code written by James Tam, don't modify
    public Critter [][] getCurrent() 
    {
        return(current);
    }

    protected void initializeBiosphere(Critter[][] world)
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLUMNS; c++)
            {
                world[r][c] = new Critter(Critter.EMPTY);
            }
        }
    }

    //Original code written by James Tam
    public void runTurn()
    {
        //Original code below: student can freely modify to fulfill assignment requirements
        //(add, delete, change existing code).
        Scanner in = new Scanner(System.in);
        
        while (continueSimulation == true)
        {
            biosphereActivities();
            display();
            copyArray(taminator, current);
            initializeBiosphere(birthsAndDeaths);
            initializeBiosphere(taminator);

            System.out.print("Press Q to quit: ");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("q")) 
            {
                continueSimulation = false;
            } 
        }
    }

    protected void biosphereActivities()    //entity turn simulation
    {
        for (int r = 0; r < ROWS; r++) {    //current to birthsAndDeaths simulation
            for (int c = 0; c < COLUMNS; c++) {
                int neighbours = checkBoundaries(r, c);
                if (current[r][c].getAppearance() == Critter.EMPTY) {
                    critterBirth(r, c, neighbours, current, birthsAndDeaths);   //test
                }
                else {  
                    if (current[r][c].getAppearance() == Critter.DEFAULT_APPEARANCE) {    
                        critterSurvival(r, c, neighbours);
                    }    
                    else if (current[r][c] instanceof Taminator) { 
                        instanceOfTam = true;
                        birthsAndDeaths[r][c] = current[r][c];  //copy Tam over to birthsAndDeaths
                        taminatorLocation.setRow(r);
                        taminatorLocation.setColumn(c); 
                    }
                }
            }
        }
        if (instanceOfTam == true)
        {
            taminatorActivites();
            taminatorMovementActivities();
        }
        else  { //copy birthsAndDeaths to taminator
            copyArray(birthsAndDeaths, taminator);
        }
    }

    protected void taminatorActivites()
    {
        int currentRow = taminatorLocation.getRow();
        int currentColumn = taminatorLocation.getColumn();
        removeCritters = ((Taminator) birthsAndDeaths[currentRow][currentColumn]).checkForCritters(currentRow, currentColumn, birthsAndDeaths); //saves locations of adjacent critters
        int killCounter = critterkillCount(removeCritters);

        for (int r = 0; r < ROWS; r++) {    //birthsAndDeaths to taminator simulation
            for (int c = 0; c < COLUMNS; c++) {
                boolean removedNeighbour = false;
                if (killCounter != 0)
                {
                    for (int i = 0; i < MAX_TAMINATION; i++) {  //iterate through all Taminator neighbours
                        if (removeCritters[i] != null & removedNeighbour == false)
                        {
                            int removeRow = removeCritters[i].getRow();
                            int removeColumn = removeCritters[i].getColumn();

                            if (r != removeRow & c != removeColumn & birthsAndDeaths[r][c].getAppearance() != Taminator.DEFAULT_APPEARANCE)
                            {
                                taminator[r][c] = birthsAndDeaths[r][c];
                            } 
                            if (r == removeRow & c == removeColumn) 
                            {
                                removeCritters[i] = null;
                                removedNeighbour = true;
                                killCounter--;
                            }
                        }
                    }
                }
                else if (killCounter == 0 & birthsAndDeaths[r][c].getAppearance() != Taminator.DEFAULT_APPEARANCE)
                {
                    taminator[r][c] = birthsAndDeaths[r][c];
                }
            }
        }
    }   

    protected void taminatorMovementActivities()   //random taminator teleportation
    {
        Critter pastTaminator = birthsAndDeaths[taminatorLocation.getRow()][taminatorLocation.getColumn()];
        Location newTamLocation = ((Taminator) pastTaminator).randomTeleportation(taminator, taminatorLocation);
        taminatorLocation.setRow(newTamLocation.getRow());
        taminatorLocation.setColumn(newTamLocation.getColumn());
        taminator[taminatorLocation.getRow()][taminatorLocation.getColumn()] = new Taminator(Taminator.DEFAULT_APPEARANCE);
    }

    protected int critterkillCount(Location[] removeCritters)   //how many critters must be removed by taminator
    {
        int killCount = 0;
        for (int i = 0; i < MAX_TAMINATION; i++)
        {
            if (removeCritters[i] != null)
            {
                killCount++;
            }
        }
        return(killCount);
    }

    protected void copyArray(Critter[][] sourceArray, Critter[][] destinationArray)
    {
        for (int r = 0; r < ROWS; r++) {    
            for (int c = 0; c < COLUMNS; c++) {
                destinationArray[r][c] = sourceArray[r][c];
            }
        }
    }

    protected int checkBoundaries(int r, int c)   //checks if adjacent positions are within biosphere boundaries (might need to make more general so taminator can use too)
    {
        int neighbourCounter = 0;

        if ((r-1) >= 0 & (r-1) <= 9) {  //1 up
            if (checkForNeighbours((r-1), c)) {
                neighbourCounter++;
            }
        } 
        if ((r-1) >= 0 & (r-1) <= 9 & (c-1) >= 0 & (c-1) <= 9) {   //1 up 1 left
            if (checkForNeighbours((r-1), (c-1))) {
                neighbourCounter++;
            }
        }
        if ((r-1) >= 0 & (r-1) <= 9 & (c+1) >= 0 & (c+1) <= 9) {   //1 up 1 right
            if (checkForNeighbours((r-1), (c+1))) {
                neighbourCounter++;
            }
        }
        if ((c-1) >= 0 & (c-1) <= 9) { //1 left
            if (checkForNeighbours(r, (c-1))) {
                neighbourCounter++;
            }
        }
        if ((c+1) >= 0 & (c+1) <= 9) { //1 right
            if (checkForNeighbours(r, (c+1))) {
                neighbourCounter++;
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9 & (c-1) >= 0 & (c-1) <= 9) {    //1 down 1 left
            if (checkForNeighbours((r+1), (c-1))) {
                neighbourCounter++;
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9) { //1 down
            if (checkForNeighbours((r+1), c)) {
                neighbourCounter++;
            }
        }
        if ((r+1) >= 0 & (r+1) <= 9 & (c+1) >= 0 & (c+1) <= 9) {   //1 down 1 right
            if (checkForNeighbours((r+1), (c+1))) {
                neighbourCounter++;
            }
        }
        return(neighbourCounter);
    }

    protected boolean checkForNeighbours(int adjacentRow, int adjacentColumn) //checks adjacent positions for # of neighbours
    {
        Critter potentialNeighbour = current[adjacentRow][adjacentColumn];

        if (potentialNeighbour.getAppearance() != Critter.EMPTY)
        {
            if (potentialNeighbour.getAppearance() == Critter.DEFAULT_APPEARANCE) 
            {
                return(true);
            }   
        }    
        return(false);   
    }

    private void critterSurvival(int r, int c, int neighbours)
    {
        if (critterCondition(neighbours) == true)
        {
            birthsAndDeaths[r][c] = current[r][c];  //death
        }
    }   

    protected boolean critterCondition(int neighbourCounter)    //death conditional
    {
        if (neighbourCounter == 2 | neighbourCounter == 3)
        {
            return(true);
        }
        return(false);
    }

    protected void critterBirth(int r, int c, int neighbours, Critter[][] current, Critter[][] birthsAndDeaths)
    {
        if (current[r][c].getAppearance() == Critter.EMPTY & neighbours == 3)
        {
            birthsAndDeaths[r][c] = new Critter();    //birth
        }
    }
}