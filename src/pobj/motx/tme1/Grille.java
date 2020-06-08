package pobj.motx.tme1;

public class Grille {
	private Case[][] matrix;
	
	
	public Grille(int hauteur, int largeur) {
		matrix=new Case[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				matrix[i][j]=new Case(i,j,' ');
			}
		}
	}
	
	public Case getCase(int lig, int col) {
		return matrix[lig][col];
	}
	
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	public int nbLig() {
		return matrix.length;
	}
	public int nbCol() {
		return matrix[0].length;
	}
	
	public Grille copy() {
		Grille g=new Grille(nbLig(),nbCol());
		for (int i = 0; i < nbLig(); i++) {
			for (int j = 0; j < nbCol(); j++) {
				g.matrix[i][j].setChar(matrix[i][j].getChar());
			}
		}
		return g;
	}
	
	public void setChar(int lig, int col, char c) {
		matrix[lig][col].setChar(c);
	}
}
