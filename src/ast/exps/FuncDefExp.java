package ast.exps;

import java.util.List;

import ast.Block;
import ast.Exp;

public class FuncDefExp extends Exp {
	private List<String> parList;
	private Block block;
}
