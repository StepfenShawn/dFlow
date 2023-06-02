package ast.stats;

import ast.Exp;
import ast.Stat;

public class PipeStat extends Stat {
	private Exp left;
	private Exp right;
	
	public PipeStat(Exp left, Exp right) {
		this.left = left;
		this.right = right;
	}
	
	public Exp getRight() {
		return this.right;
	}
	
	public Exp getLeft() {
		return this.left;
	}
}
