package ast.stats;

import ast.Stat;
import ast.exps.FuncCallExp;

public class FuncCallStat extends Stat {
	private FuncCallExp exp;
	
	public FuncCallStat(FuncCallExp exp) {
		this.exp = exp;
	}
}
