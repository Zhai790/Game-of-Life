package Assignment4;

import java.util.Scanner;
/*
    Java Hierarchies 
    Version: April 1, 2021
    Shufan Zhai - 30117333 - T03

    GameOfLife class: 
        starting execution point

    Program limitations:
      User cannot switch between prosperousBiosphere and regularBiosphere once simulation has started 
      Textfile must be edited/changed in order to adjust starting location of objects

    Versions:
    March 23, 2021
        (1)Created 3 displays and started on biosphereActivites method
        (2)Created constructors for Critter and Biosphere classes
        (3)Outlined methods needed for Critter movement around biosphere array

    March 24, 2021
        (1)Finished biosphereActivities, checkBoundaries and checkForNeighbours methods

    March 25, 2021
        (1)Created checkAttackBoundaries, checkForEnemies, attackEnemy methods
        (2)Updated worldMovement()
        (3)Fixed bug relating to ensuring one attack or move per turn by creating turnConditional method within Entity class
    
    March 26, 2021
        (1)Implented taminator tasks 
        (2)Degbugged program to compensate for taminator tasks

    March 29, 2021
        (1)Split biosphereActivites method into biosphereActivities, taminatorActivities, and taminatorMovementActivities
        (2)Fixed Taminator bugs

    March 30, 2021
        (1)Implemented prosperousBiosphere

    March 31, 2021
        (1)Fixed movement and births/deaths discrepancies in prosperousBiosphere

    April 1, 2021
        (1)Updated documentation
*/

/* No additional methods and nor attributes to be added. */ 

public class GameOfLife
{
    public static void main (String [] args)
    {
        //Start of code written by James Tam, students can freely modify (but still need to 
        //fulfill assignment requirements and stylistic approaches).
        Scanner in = new Scanner(System.in);
        System.out.print("Select (p)rosperous Biosphere or (r)egular Biosphere: ");
        String input = in.nextLine();
        while (!input.equalsIgnoreCase("p") & !input.equalsIgnoreCase("r")) //remember to implement two types of biospheres
        {
            System.out.print("Select (p)rosperous Biosphere or (r)egular Biosphere: ");
            input = in.nextLine();
        }
        if (input.equalsIgnoreCase("p"))    //runs prosperous biosphere rules
        {
            ProsperousBiosphere prosperous;
            prosperous = new ProsperousBiosphere(FileInitialization.read());
            prosperous.runTurn();
        }
        else if (input.equalsIgnoreCase("r"))
        {
            Biosphere regular;
            regular = new Biosphere(FileInitialization.read()); //runs regular biosphere rules
            regular.runTurn();
        }
    }
}