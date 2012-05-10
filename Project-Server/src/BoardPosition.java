
public class BoardPosition
{

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
}
