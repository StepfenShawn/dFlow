package execute;

import java.util.List;

import ast.Block;
import ast.exps.NameExp;

public class FunctionValue implements Value {
	
	private String name = null;
	private List<NameExp> args;
	private Block body;
	
	public FunctionValue(String name, List<NameExp> args, Block body) {
		this.name = name;
		this.args = args;
		this.body = body;
	}
	
	public String getFuncName() {
		return this.name;
	}
	
	public List<NameExp> getArgs() {
		return this.args;
	}
	
	public Block getBody() {
		return this.body;
	}
	
	@Override
	public String toString() {
		return " Function Object : [ " + name + " ]";
	}
}