package ast.exps;

import ast.Exp;
import lexer.Token;
import lexer.TokenKind;

public class BinopExp extends Exp {
	private TokenKind op;
	private Exp right;
	private Exp left;
	
	public BinopExp(Token op, Exp left, Exp right) {
		this.line = op.getLine();
		this.left = left;
		this.right = right;
		this.op = op.getKind();
	}
	
	public Exp getLeft() {
		return this.left;
	}
	
	public Exp getRight() {
		return this.right;
	}
	
	public TokenKind getOp() {
		return this.op;
	}
}
