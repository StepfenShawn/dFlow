package ast;

import java.util.List;

public class Block extends Node {
	private List<Stat> stats;
	
	public Block() {}
	
	public Block(List<Stat> stats) {
		this.stats = stats;
	}
	
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}
	
	public List<Stat> getStats() {
		return this.stats;
	}
}
