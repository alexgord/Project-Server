
public class Piece {
	ColorEnum color;
	PieceEnum piece;
	
	public String toString() {
		String output;
		output = color.toString() + piece.toString();
		return output;
	}
	
}
