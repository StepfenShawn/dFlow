package ast.stats;

import java.util.List;

import ast.Exp;
import ast.Stat;

public class AssignStat extends Stat {
	private List<Exp> varList;
	private List<Exp> expList;
	
	public AssignStat(int line, List<Exp> varList,
				List<Exp> expList) {
		this.varList = varList;
		this.expList= expList;
		this.line = line;
	}
	
	public List<Exp> getVarList() {
		return this.varList;
	}
	
	public List<Exp> getExpList() {
		return this.expList;
	}
}
