package Assignment4;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
  Usage:
  * Call the static read method to prompt the user for the file containing the 
    positions.
  * The method will prompt for a filename and try to open that the file in the
    location where the Driver for your Java program is located.
  * The prompt will be repeated if there are problems accessing the file (e.g.
    the file is not at the specified location, it is corrupted etc.).
  * The method returns a reference to a 2D array of references to objects of type
    Entity.
  * The appearance attribute of the entity will correspond to the appearance of 
    characters in the file.
  * Starting files must be exactly 10x10 characters in size and consist only of the 
    '*' character, an 'T' character or a space.


*/
public class FileInitialization
{
    public static Critter[][] read() {
        Critter [][] temp = new Critter[Biosphere.ROWS][Biosphere.COLUMNS];
        String line = null;
        int r, c;
        char letter;
        BufferedReader br = null;
        FileReader fr = null;
        FileContainer aContainer = null;
        try {
            aContainer = checkingFile(br,fr);
            br = aContainer.getBufferedReader();
            fr = aContainer.getFileReader();
            line = br.readLine();
            if (line == null)
                System.out.println("Empty file, nothing to read");
            r = 0;
            while (line != null) {   
                c = 0;
                while(c < Biosphere.COLUMNS) {
                    letter = line.charAt(c);
                    temp[r][c] = createCritter(letter);
                    c = c + 1;
                }
                line = br.readLine();
                if (line != null)
                    r = r + 1;
            }
            fr.close();
        }
        catch (FileNotFoundException e) {
            String location = System.getProperty("user.dir");
            System.out.println("Input file not in " + location);
        }
        catch (IOException e) {
            System.out.println("General file input problem " + 
                               "reading starting positions");
        }
        return(temp);
    }
    
    private static FileContainer checkingFile(BufferedReader br, FileReader fr)
    {
        FileContainer aContainer = null;    
        Scanner in = new Scanner(System.in);
        String filename = null;
        boolean fileFound = false;
        while (fileFound == false)
        {
            try
            {
                System.out.print("Name of file containing starting positions: ");
                filename = in.nextLine();   
	        fr = new FileReader(filename);
                br = new BufferedReader(fr);
                fileFound = true;
            }
            catch (FileNotFoundException ex)
            {
                String location = System.getProperty("user.dir");
                System.out.println("Input file not in " + location);
            }

         }
         aContainer = new FileContainer(br,fr); 
         return(aContainer);
    }

    private static Critter createCritter(char letter)
    {
        final int ERROR = -1;
        Critter c = null;
        Taminator t = null;
        switch(letter)
        {
            case Critter.EMPTY:
            case Critter.DEFAULT_APPEARANCE:
                c = new Critter(letter);
                return(c);

            case Taminator.DEFAULT_APPEARANCE:
                t = new Taminator(letter);
                return(t);

            default:
                System.out.println("Error: Invalid character [" + letter + 
                                   "] in file");
                System.exit(ERROR);  //Normally don't just exit but this is drastic!
        }
        return(c);
    }
}
