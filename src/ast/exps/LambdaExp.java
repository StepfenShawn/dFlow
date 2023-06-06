package ast.exps;

import java.util.List;

import ast.Exp;

public class LambdaExp extends Exp {
	private List<NameExp> args;
	private Exp rets;
	
	public LambdaExp(int line, List<NameExp> args, Exp rets) {
		this.line = line;
		this.args = args;
		this.rets = rets;
	}
	
	public List<NameExp> getArgs() {
		return this.args;
	}
	
	public Exp getRets() {
		return this.rets;
	}
	
	public static LambdaExp valueOf(Exp exp) {
		return (LambdaExp)exp;
	}
}
