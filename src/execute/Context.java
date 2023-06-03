package execute;

import java.util.HashMap;

import ast.Block;
import ast.Exp;

public class Context {
	Block block;
	
	private HashMap<String, Value> env = new HashMap<>();

	public HashMap<String, Value> getEnv() {
		return this.env;
	}
	
	public void put(String name, Value v) {
		this.env.put(name, v);
	}
	
	public void remove(String name) {
		if (this.env.containsKey(name))
			this.env.remove(name);
	}
}
