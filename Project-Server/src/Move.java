
public class Move
{
	private BoardPosition a;
	private BoardPosition b;
	
	public BoardPosition getA()
	{
		return a;
	}

	public void setA(BoardPosition a)
	{
		this.a = a;
	}

	public BoardPosition getB()
	{
		return b;
	}

	public void setB(BoardPosition b)
	{
		this.b = b;
	}

	Move(BoardPosition a, BoardPosition b)
	{
		this.a = a;
		this.b = b;
	}
}
