package ast.stats;

import java.util.List;

import ast.Exp;
import ast.Stat;
import ast.exps.NameExp;

public class AssignStat extends Stat {
	private List<NameExp> varList;
	private List<Exp> expList;
	
	public AssignStat(int line, List<NameExp> varList,
				List<Exp> expList) {
		this.varList = varList;
		this.expList= expList;
		this.line = line;
	}
	
	public List<NameExp> getVarList() {
		return this.varList;
	}
	
	public List<Exp> getExpList() {
		return this.expList;
	}
}
