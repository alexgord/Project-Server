/**********************************BoardPosition CLASS INFO*****************************************

This class is responsible for getting and setting the rows and columns of a specific piece.  It 
Also can check if the coordinates are out of bounds and return a string of all the column headings.
Note that getting and setting rows and columns are not used in the server.  

**************************************************************************************************/

import java.io.Serializable;
import java.util.ArrayList;


public class BoardPosition  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1004705995234741264L;
	private int row;
	private int collumn;
	
	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCollumn()
	{
		return collumn;
	}

	public void setCollumn(int collumn)
	{
		this.collumn = collumn;
	}

	public BoardPosition(int row, int collumn)
	{
		this.row = row;
		this.collumn = collumn;
	}
	
	
	public boolean isOutOfBounds()
	{
		return row < 0 || row > 6 || collumn < 0 || collumn > 6;
	}
	
	public String toString()
	{
		ArrayList<String> colNames = new ArrayList<String>();

		colNames.add("a");
		colNames.add("b");
		colNames.add("c");
		colNames.add("d");
		colNames.add("e");
		colNames.add("f");
		String r = "";
		r += 6 - row + colNames.get(collumn);
		return r;
	}
}
