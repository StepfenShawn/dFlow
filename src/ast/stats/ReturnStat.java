package ast.stats;

import java.util.List;

import ast.Exp;
import ast.Stat;

public class ReturnStat extends Stat {
	private List<Exp> rets;
	
	public ReturnStat(List<Exp> rets) {
		this.rets = rets;
	}
	
	public List<Exp> getRets() {
		return this.rets;
	}
}
