package execute;

import java.util.HashMap;

import ast.Block;
import ast.Exp;

public class Context {
	Block block;
	
	static final HashMap<String, Exp> env = new HashMap<String, Exp>();
}
