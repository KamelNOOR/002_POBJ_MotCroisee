package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP{
	private GrillePotentiel gp;
	private List <DicoVariable> listedv;
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		listedv=new ArrayList<DicoVariable>();
		
	}
	@Override
	public List<IVariable> getVars() {
		List<IVariable> liv=new ArrayList<IVariable>();
		List<Emplacement> lemplacement=gp.getGrillePlaces().getPlaces();
		listedv.clear();
		Boolean ilyaunecasevide;
		int j;
		for (int i = 0; i < lemplacement.size(); i++) {
			Emplacement curempl=lemplacement.get(i);
			ilyaunecasevide=false;
			j=0;
			while(!ilyaunecasevide && j<lemplacement.size()) {
				ilyaunecasevide=curempl.getCaseIndice(j).isVide();
				j++;
			}
			if(ilyaunecasevide) {
				listedv.add(new DicoVariable(i, gp));
			}
		}
		
		for(DicoVariable dicov : listedv) {
			liv.add(dicov);
		}
		System.out.println("liv"+liv);
		return liv;
	}

	@Override
	public boolean isConsistent() {
		return !gp.isDead();
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		if(vi instanceof DicoVariable) {
			DicoVariable dvc= (DicoVariable)vi;
			gp=gp.fixer(dvc.getIndex(),val);
			
		}
		return this;
	}
	
	@Override
	public String toString() {
		return gp.toString();
	}
}
