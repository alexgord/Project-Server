
public class Board {
	private Piece[][] board;

	public Board()
	{
		board = new Piece[6][6];

		int count = 0;
		Piece temp;
		while (count < 6)
		{
			temp = new Piece(ColorEnum.BLACK, PieceEnum.PAWN);
			board[1][count] = temp;
			temp = new Piece(ColorEnum.WHITE, PieceEnum.PAWN);
			board[4][count] = temp;
			count++;
		}
		temp = new Piece(ColorEnum.BLACK, PieceEnum.KNIGHT);
		board[0][1] = temp;
		board[0][4] = temp;
		temp = new Piece(ColorEnum.WHITE, PieceEnum.KNIGHT);
		board[5][1] = temp;
		board[5][4] = temp;

	}

	public String toString()
	{
		String output;
		output = " -------------------\n";

		for (int cx = 0; cx < 6; cx++)
		{
			output += 6-cx;
			output += "|";
			for (int cy = 0; cy < 6; cy++)
			{
				if (board[cx][cy] == null) 
				{
					output += "  |";
				}
				else
				{
					output += board[cx][cy].toString() + "|";
				}
			}
			output += "\n";
			output += " -------------------\n";
		}
		output += "  a  b  c  d  e  f\n";
		return output;
	}

	public Piece[][] getBoard()
	{
		return board;
	}

	public void setBoard(Piece[][] board)
	{
		this.board = board;
	}

	public void swapPositions(Move move)
	{
		//TODO:
	}
}
