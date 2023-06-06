package ast.exps;

import java.util.List;

import ast.Exp;

public class ListExp extends Exp {
	private List<Exp> list;
	public ListExp(int line, List<Exp> list) {
		this.line = line;
		this.list = list;
	}
	
	public List<Exp> getList() {
		return this.list;
	}
	
	public static ListExp valueOf(Exp exp) {
		return (ListExp)exp;
	}
}
