
public class Aminoacid {
	private String codon;
	private int point;
	
	public Aminoacid(String codon, int point) {
		this.codon = codon;
		this.point = point;
	}

	public String getCodon() {
		return codon;
	}

	public void setCodon(String codon) {
		this.codon = codon;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
