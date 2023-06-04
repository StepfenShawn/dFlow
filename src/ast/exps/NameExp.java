package ast.exps;
import ast.Exp;
import lexer.Token;

public class NameExp extends Exp {
	private String name;
	
	public NameExp(int line, String name) {
		this.line = line;
		this.name = name;
	}
	
	public NameExp(Token tok) {
		this.line = tok.getLine();
		this.name = tok.getValue();
	}
	
	public String getName() {
		return this.name;
	}
}
