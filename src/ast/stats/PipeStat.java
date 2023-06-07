package ast.stats;

import java.util.List;

import ast.Exp;
import ast.Stat;

public class PipeStat extends Stat {
	private List<Exp> exps;
	
	public PipeStat(List<Exp> exps) {
		this.exps = exps;
	}
	
	public List<Exp> getExps() {
		return this.exps;
	}
}
