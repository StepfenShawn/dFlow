package ast.exps;

import ast.Exp;
import lexer.Token;
import lexer.TokenKind;

public class UnopExp extends Exp {
	private TokenKind op;
	private Exp exp;
	
	public UnopExp(Token op, Exp exp) {
		this.line = op.getLine();
		this.exp = exp;
		this.op = op.getKind();
	}
}
