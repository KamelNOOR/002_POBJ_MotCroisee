package pobj.motx.tme2;
import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;

public class GrillePotentiel {
	private GrillePlaces grilleplace;
	private Dictionnaire dictionaire;
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grilleplace=grille;
		this.dictionaire=dicoComplet;
		contraintes=new ArrayList<IContrainte>();
		motsPot=new ArrayList<Dictionnaire>();
		ArrayList<Emplacement> emplacementList = (ArrayList<Emplacement>) grille.getPlaces();
		Dictionnaire dicfor;
		for (int i = 0; i < emplacementList.size(); i++) {
			dicfor = new Dictionnaire();
			Emplacement curemp=emplacementList.get(i);
			dicfor=dicoComplet.copy();
			dicfor.filtreLongueur(curemp.size());
			for (int j = 0; j < curemp.size(); j++) {
				Case curcase=curemp.getCaseIndice(j);
				if( (!(curcase.isVide()))&&(!(curcase.isPleine())) ) {
					//System.out.println(curemp.getCaseIndice(j));
					dicfor.filtreParLettre(curcase.getChar(), j);
				}
			}
			motsPot.add(dicfor);
		}
		ArrayList<Case> memoire=new ArrayList<Case>();
		for (int m = 0; m < emplacementList.size(); m++) {
			Emplacement curemp=emplacementList.get(m);
			for (int c = 0; c < curemp.size(); c++) {
				Case curcase=curemp.getCaseIndice(c);
				if(curcase.isVide()) {
					if(!memoire.contains(curcase)) {
						memoire.add(curcase);
						for(int x=0; x < emplacementList.size();x++) {
							if(!(emplacementList.get(x)==curemp)) {
								for(int y=0; y<emplacementList.get(x).size(); y++) {
									if(curcase.equals(emplacementList.get(x).getCaseIndice(y))) {
										CroixContrainte cc=new CroixContrainte(m, c, x, y); 
										contraintes.add(cc);
										cc.reduce(this);
									}
								}
							}
						}
					}
				}
			}
		}
		propage();
	}

	private boolean propage() {
		int cpt;
		while(true) {
			cpt=0;
			for(IContrainte macontrainte : contraintes) {
				CroixContrainte cc=(CroixContrainte)macontrainte;
				cpt+=cc.reduce(this);
			}
			if(cpt==0) {
				return true;
			}
			if(isDead()) {
				return false;
			}
		}
	}
	
	public GrillePlaces getGrillePlaces() {
		return grilleplace;
	}
	
	public boolean isDead() {
		for (Dictionnaire dic: motsPot) {
			if(dic.size()==0) {
				return true;
			}
		}
		return false;
	}
	
	public GrillePotentiel fixer(int m, String soluce) {
		return new GrillePotentiel(grilleplace.fixer(m, soluce), dictionaire);
	}
	
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	public void setMotsPotIndice(int i, Dictionnaire newdico) {
		motsPot.set(i, newdico);
	}
	
	public List<IContrainte> getContraintes() {
		return contraintes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+grilleplace.getGrille();
	}
}
