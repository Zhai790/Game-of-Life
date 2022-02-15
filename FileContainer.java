package Assignment4;

import java.io.FileReader;
import java.io.BufferedReader;

/*
  Author:  James Tam
  Version: February 16, 2021

*/

public class FileContainer
{
    private BufferedReader br;
    private FileReader fr; 

    public FileContainer(BufferedReader aBufferedReader, 
                         FileReader aFileReader)
    {
        br = aBufferedReader;
        fr = aFileReader;
    }

    public BufferedReader getBufferedReader()
    {
        return(br);
    }

    public FileReader getFileReader()
    {
        return(fr);
    }
}