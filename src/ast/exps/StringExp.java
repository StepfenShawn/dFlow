package ast.exps;

import ast.Exp;
import lexer.Token;

public class StringExp extends Exp {
	private String str;
	
	public StringExp(Token token) {
		this.line = token.getLine();
		this.str = token.getValue();
	}
	
	public StringExp(int line, String str) {
		this.line = line;
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
	public static StringExp valueOf(Exp exp) {
		return (StringExp)exp;
	}
}
