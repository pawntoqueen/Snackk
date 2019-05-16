

public class Node_data {

	private int x,y ;
	private char dnapart;
	private String type;
	
	public Node_data(int x, int y, char dnapart, String type) {
		this.x = x;
		this.y = y;
		this.dnapart = dnapart;
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getDnapart() {
		return dnapart;
	}
	public void setDnapart(char dnapart) {
		this.dnapart = dnapart;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
