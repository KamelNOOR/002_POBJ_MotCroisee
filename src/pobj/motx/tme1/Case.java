package pobj.motx.tme1;

public class Case {
	private int lig,col;
	private char c;
	public Case(int lig, int col, char c) {
		this.lig=lig;
		this.col=col;
		this.c=c;
	}
	
	public int getLig() {
		return lig;
	}
	
	public int getCol() {
		return col;
	}
	public char getChar() {
		return c;
	}
	public void setChar(char valeur) {
		c = valeur;
	}
	public boolean isVide() {
		return c==' ';
	}
	public boolean isPleine() {
		return c=='*';
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(obj instanceof Case) {
			Case c=(Case)obj;
			return this.lig==c.getLig() && this.col==c.getCol() && this.c==c.getChar();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return ""+"["+lig+"]["+col+"]'"+c+"'";
	}
}
