package ast.exps;

import java.util.List;

import ast.Block;
import ast.Exp;

public class IfExp extends Exp {
	
	private Exp ifCond;
	private Exp ifBlock;
	private Exp elseBlock;
	
	public IfExp(int line, Exp ifCond, Exp ifBlock, 
					Exp elseBlock) {
		this.line = line;
		this.ifCond = ifCond;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}
	
	public Exp getIfCond() {
		return this.ifCond;
	}
	
	public Exp getIfBlock() {
		return this.ifBlock;
	}
	
	public Exp getElseBlock() {
		return this.elseBlock;
	}
	
	public static IfExp valueOf(Exp exp) {
		return (IfExp)exp;
	}
}
