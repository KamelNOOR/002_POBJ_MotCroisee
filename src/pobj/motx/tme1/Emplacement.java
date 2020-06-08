package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Emplacement {
	private List<Case> lettres;
	/*private int motnum=0;			//Informations en extra,
	private Boolean horizontal;*/	//non demandé
	public Emplacement() {
		lettres=new ArrayList<Case>();
	}
	
	@Override
	public String toString() {
		String s="";
		/*if(horizontal) {
			s+="[H"+motnum+"]";
		}else {
			s+="[V"+motnum+"]";
		}*/
		for(Case c: lettres) {
			s+=c.getChar();
		}
		return s;
	}
	
	public void setCase(Case c) {
		lettres.add(c);
	}
	
	public Case getCaseIndice(int indice) {
		return lettres.get(indice);
	}
	
	public List<Case> getLettres() {
		return lettres;
	}
	
	public void clear() {
		lettres.clear();
	}
	/*
	public void setMotnum(int motnum) {
		this.motnum = motnum;
	}
	public void setHorizontal(Boolean horizontal) {
		this.horizontal = horizontal;
	}*/
	
	public int size() {
		return lettres.size();
	}
}
