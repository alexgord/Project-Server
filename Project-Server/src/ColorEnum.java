
public enum ColorEnum {
	WHITE ("W"), BLACK ("B");
	
	private String colorStr;

	ColorEnum(String colorStr) {
		this.colorStr = colorStr;
	}
	
	public String toString() {
		return colorStr;
	}
}
