
public class Board {
	Piece[][] board;
	
	public Board() {
		board = new Piece[6][6];
		
		int count = 0;
		Piece temp;
		while (count < 6) {
			temp = new Piece();
			temp.color = ColorEnum.BLACK;
			temp.piece = PieceEnum.PAWN;
			board[1][count] = temp;
			temp = new Piece();
			temp.color = ColorEnum.WHITE;
			temp.piece = PieceEnum.PAWN;
			board[4][count] = temp;
			count++;
		}
		temp = new Piece();
		temp.color = ColorEnum.BLACK;
		temp.piece = PieceEnum.KNIGHT;
		board[0][1] = temp;
		board[0][4] = temp;
		temp = new Piece();
		temp.color = ColorEnum.WHITE;
		temp.piece = PieceEnum.KNIGHT;
		board[5][1] = temp;
		board[5][4] = temp;
		
	}
	
	public String toString() {
		int cx = 0;
		int cy = 0;
		String output;
		output = " -------------------\n";
		
		while (cx < 6) {
			output += 6-cx;
			output += "|";
			while (cy < 6) {
				if (board[cx][cy] == null) 
					output += "  |";
				else
					output += board[cx][cy].toString() + "|";
				cy++;
			}
			cy = 0;
			output += "\n";
			output += " -------------------\n";
			cx++;
		}
		output += "  a  b  c  d  e  f\n";
		return output;
	}
}
