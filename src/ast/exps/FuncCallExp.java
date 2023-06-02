package ast.exps;

import java.util.List;

import ast.Exp;
import ast.PrefixExp;

public class FuncCallExp extends PrefixExp {
	
	private Exp prefixExp;
	private StringExp nameExp;
	private List<Exp> args;
	
}
