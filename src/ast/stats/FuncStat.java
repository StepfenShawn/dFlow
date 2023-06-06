package ast.stats;

import java.util.List;

import ast.Block;
import ast.Exp;
import ast.Stat;
import ast.exps.NameExp;

public class FuncStat extends Stat {
	
	private Exp func_name;
	private List<NameExp> args;
	private List<Stat> blocks;
	
	public FuncStat(Exp func_name, List<NameExp> args, List<Stat> blocks) {
		this.func_name = func_name;
		this.args = args;
		this.blocks = blocks;
	}
}
