package ast.exps;

import ast.Exp;
import lexer.Token;

public class NumberExp extends Exp {
	private long val;
	
	public NumberExp(int line, long val) {
		this.val = val;
		this.line = line;
	}

	public NumberExp(Token token) {
		this.val = Long.parseLong(token.getValue());
		this.line = token.getLine();
	}
	
	public long getVal() {
		return this.val;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public static NumberExp valueOf(Exp exp) {
		return (NumberExp)exp;
	}
}
