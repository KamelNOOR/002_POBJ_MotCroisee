package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
	private List<Emplacement> places;
	//private Boolean chercheHorizontal; //pour assignation des infos en extras dans Emplacements
	private int nbHorizontal;
	private Grille grille;
	
	
	public GrillePlaces(Grille grille) {
		this.grille=grille;
		places=new ArrayList<Emplacement>();
		//nbHorizontal=0;
		//chercheHorizontal=true;
		for (int i = 0; i < grille.nbLig(); i++) {
			cherchePlaces(getLig(i));
		}
		nbHorizontal=places.size();
		//chercheHorizontal=false;
		for (int i = 0; i < grille.nbCol(); i++) {
			cherchePlaces(getCol(i));
		}
		
		//System.out.println(places);
	}
	
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	public int getNbHorizontal() {
		return nbHorizontal;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	private List<Case> getLig (int lig){
		List<Case> ligne=new ArrayList<Case>();
		for (int i = 0; i < grille.nbCol(); i++) {
			ligne.add(i,grille.getCase(lig, i));
		}
		return ligne;
	}
	
	private List<Case> getCol(int col){
		List<Case> colone=new ArrayList<Case>();
		for (int i = 0; i < grille.nbLig(); i++) {
			colone.add(i,grille.getCase(i, col));
		}
		return colone;
	}
	
	private void cherchePlaces(List<Case> cases) {
		Emplacement emptemp=new Emplacement();
		//emptemp.setHorizontal(chercheHorizontal);	
		//int nbmot=0;								
		for (Case curcase : cases) {
			if(curcase.getChar()!='*') {
				emptemp.setCase(curcase);
			}else {
				if(emptemp.size()>=2) {
					//nbmot++;
					//emptemp.setMotnum(nbmot);
					places.add(emptemp);
					emptemp=new Emplacement();
					//emptemp.setHorizontal(chercheHorizontal);
				}else {
					emptemp.clear();
				}
			}
		}
		if(emptemp.size()>=2) {
			//nbmot++;
			//emptemp.setMotnum(nbmot);
			places.add(emptemp);
			emptemp=new Emplacement();
			//emptemp.setHorizontal(chercheHorizontal);
		}
	}
	
	public GrillePlaces fixer(int m, String soluce) {
		Grille nGrille=grille.copy();
		GrillePlaces nGrillePlaces = new GrillePlaces(nGrille);
		Emplacement motE=nGrillePlaces.getPlaces().get(m);
		for (int i = 0; i < soluce.length(); i++) {
			motE.getCaseIndice(i).setChar(soluce.charAt(i));
			nGrille.setChar(motE.getCaseIndice(i).getLig(), motE.getCaseIndice(i).getCol(), soluce.charAt(i));
		}
		return nGrillePlaces;
	}
	
	@Override
	public String toString() {
		String s="";
		for (Emplacement em: places) {
		s+=em+"\n";
		}
		return s;
	}
	
}