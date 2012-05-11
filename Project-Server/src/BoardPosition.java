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
