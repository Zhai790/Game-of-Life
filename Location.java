package Assignment4;

/*
  Author:  James Tam
  Version: February 11, 2021
*/

public class Location
{
    private int row;
    private int column;

    public Location(int newRow, int newColumn)
    {
        row = newRow;
        column = newColumn;
    }

    public int getColumn()
    {
        return(column);
    }

    public int getRow()
    {
        return(row);
    }

    public void setColumn(int newColumn)
    {
        column = newColumn;
    }

    public void setRow(int newRow)
    {
        row = newRow;
    }
}