package pobj.motx.tme2;

import java.util.ArrayList;

public class CroixContrainte implements IContrainte{
	private int m1,c1,m2,c2;
	
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1=m1;
		this.c1=c1;
		this.m2=m2;
		this.c2=c2;
	}
	
	@Override
	public int reduce(GrillePotentiel grille) {
		int cpt=0;
		//System.out.println("\nf:");
		Dictionnaire ld1=grille.getMotsPot().get(m1), ld2=grille.getMotsPot().get(m2);
		ArrayList<Character> lc1 = new ArrayList<Character>();
		char curchar;
		for (int i = 0; i < ld1.size(); i++) {
			curchar=ld1.get(i).charAt(c1);
			if( !lc1.contains(curchar) ) {
				lc1.add(curchar);
			}
		}
		//System.out.println("lc1="+lc1+lc1.size());
		ArrayList<Character> lc2 = new ArrayList<Character>();
		for (int i = 0; i < ld2.size(); i++) {
			curchar=ld2.get(i).charAt(c2);
			if( !lc2.contains(curchar) ) {
				lc2.add(curchar);
			}
		}
		//System.out.println("lc2="+lc2+lc2.size());
		ArrayList<Character> s = new ArrayList<Character>(lc1);
		//for (int i = 0; i < lc1.size(); i++) {
			//s.add(lc1.get(i));
		//}
		//System.out.println("s before="+s+s.size());
		s.retainAll(lc2);
		//System.out.println("s after="+s+s.size());
		if(lc1.size()>s.size()) {
			Dictionnaire nld1=new Dictionnaire();
			for (int i = 0; i < ld1.size(); i++) {
				if( s.contains(ld1.get(i).charAt(c1)) ) {
					nld1.add(ld1.get(i));
				}else {
					cpt++;
				}
			}
			//System.out.println("ld1 size:"+ld1.size()+" nld1 size:"+nld1.size());
			grille.setMotsPotIndice(m1, nld1);
		}
		
		if(lc2.size()>s.size()) {
			Dictionnaire nld2=new Dictionnaire();
			for (int i = 0; i < ld2.size(); i++) {
				if( s.contains(ld2.get(i).charAt(c2))) {
					nld2.add(ld2.get(i));
				}else {
					cpt++;
				}
			}
			//System.out.println("ld2 size:"+ld2.size()+" nld2 size:"+nld2.size());
			grille.setMotsPotIndice(m2, nld2);
		}
		//System.out.println(cpt);
		return cpt;
	}
	
	public int getC1() {
		return c1;
	}
	public int getC2() {
		return c2;
	}
	public int getM1() {
		return m1;
	}
	public int getM2() {
		return m2;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(obj instanceof CroixContrainte) {
			CroixContrainte cc=(CroixContrainte)obj;
			return (this.c1==cc.getC1() && this.c2==cc.getC2() && this.m1==cc.getM1() && this.m2==cc.getM2());
		}
		return false;
	}
}
