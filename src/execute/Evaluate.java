package execute;

import ast.Block;
import ast.Exp;
import ast.Stat;
import ast.exps.BinopExp;
import ast.exps.FalseExp;
import ast.exps.NumberExp;
import ast.exps.StringExp;
import ast.exps.TrueExp;
import ast.stats.PipeStat;
import lexer.TokenKind;

public class Evaluate {
	
	private Context context;
	
	public static String evalStringExp(StringExp exp) {
		String str = exp.getStr();
		// 移除两边的 " 字符
		str = str.substring(1, str.length() - 1);
		return str;
	}
	
	public static long evalNumberExp(NumberExp exp) {
		return exp.getVal();
	}
	
	public static Value evalExp(Exp exp) {
		if (exp instanceof StringExp) {
			String val = evalStringExp((StringExp)exp);
			return new StringValue(val);
		} 
		else if (exp instanceof TrueExp) {
			return new BooleanValue(true);
		} 
		else if (exp instanceof FalseExp) {
			return new BooleanValue(false);
		} 
		else if (exp instanceof NumberExp) {
			long num_val = evalNumberExp((NumberExp)exp);
			return new NumberValue(num_val);
		} 
		else if (exp instanceof BinopExp) {
			TokenKind op = ((BinopExp)exp).getOp();
			Value left = evalExp(((BinopExp)exp).getLeft());
			Value right = evalExp(((BinopExp)exp).getRight());
			return evalOp(left, right, op);
		}
		return null;
	}
	
	public static Value evalOp(Value left, Value right, TokenKind op) {
		Value result = null; // 运算结果
		switch (op) {
		case TOKEN_OP_ADD:
			
		}
		return result;
	}
	
	public static void run(Block block) {
		for (Stat stat : block.getStats()) {
			if (stat instanceof PipeStat) {
				System.out.println(evalExp(((PipeStat)stat).getLeft()));
			}
		}
	}
}
