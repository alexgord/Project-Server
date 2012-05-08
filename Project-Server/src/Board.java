
public class Board {
	PieceEnum board[][];
	
	public Board() {
		board = new PieceEnum[6][6];
		
	}
	
	public void initialize() {
		int count = 0;
		while (count < 6) {
			board[0][count] = PieceEnum.PAWN;
			board[5][count] = PieceEnum.PAWN;
		}
		
	}
	
}
