package ast.exps;

import ast.PrefixExp;
import lexer.Token;

public class NameExp extends PrefixExp {
	private String name;
	
	public NameExp(int line, String name) {
		this.line = line;
		this.name = name;
	}
	
	public NameExp(Token tok) {
		this.line = tok.getLine();
		this.name = tok.getValue();
	}
}
