package ast.stats;

import java.util.List;

import ast.Block;
import ast.Exp;
import ast.Stat;

public class IfStat extends Stat {
	private List<Exp> exps;
	private List<Block> blocks;
	
	public IfStat(List<Exp> exps, List<Block> blocks) {
		this.exps = exps;
		this.blocks = blocks;
	}
}
