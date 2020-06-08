package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable{
	private int index;
	private GrillePotentiel gp;
	
	public DicoVariable(int index, GrillePotentiel gp) {
		this.gp=gp;
		this.index=index;
		
	}
	@Override
	public List<String> getDomain() {
		return gp.getMotsPot().get(index).getMots();
	}
	@Override
	public String toString() {
		return ""+index;
	}
	
	public int getIndex() {
		return index;
	}
}
