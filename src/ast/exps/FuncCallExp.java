package ast.exps;

import java.util.List;

import ast.Exp;

public class FuncCallExp extends Exp {
	
	private Exp prefixExp;
	private List<Exp> args;
	
	public FuncCallExp(int line, Exp prefixExp, List<Exp> args) {
		this.line = line;
		this.prefixExp = prefixExp;
		this.args = args;
	}
	
	public Exp getPrefix() {
		return this.prefixExp;
	}
	
	public List<Exp> getArgs() {
		return this.args;
	}
	
	public static FuncCallExp valueOf(Exp exp) {
		return (FuncCallExp)exp;
	}
}
