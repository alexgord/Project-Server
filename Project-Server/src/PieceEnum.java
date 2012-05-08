
public enum PieceEnum {
	KNIGHT ("K"), PAWN ("P");
	
	private String pieceStr;

	PieceEnum(String pieceStr) {
		this.pieceStr = pieceStr;
	}
	
	public String toString() {
		return pieceStr;
	}
}
