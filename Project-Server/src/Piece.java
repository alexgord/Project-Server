
public class Piece
{
	private ColorEnum color;
	private PieceEnum piece;
	
	public Piece(ColorEnum color, PieceEnum piece)
	{
		this.color = color;
		this.piece = piece;
	}
	
	public String toString()
	{
		String output;
		output = color.toString() + piece.toString();
		return output;
	}

	public ColorEnum getColor()
	{
		return color;
	}

	public PieceEnum getPiece()
	{
		return piece;
	}
	
}
