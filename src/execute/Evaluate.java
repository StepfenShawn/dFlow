package execute;

import java.util.ArrayList;
import java.util.List;

import ast.Block;
import ast.Exp;
import ast.Stat;
import ast.exps.BinopExp;
import ast.exps.FalseExp;
import ast.exps.FuncCallExp;
import ast.exps.IfExp;
import ast.exps.ListExp;
import ast.exps.NameExp;
import ast.exps.NumberExp;
import ast.exps.StringExp;
import ast.exps.TrueExp;
import ast.stats.AssignStat;
import ast.stats.FuncStat;
import ast.stats.PipeStat;
import lexer.TokenKind;

public class Evaluate {
	
	public static Context context = new Context();
	
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
		// 字符串表达式
		if (exp instanceof StringExp) {
			String val = evalStringExp(StringExp.valueOf(exp));
			return new StringValue(val);
		} 
		// True表达式
		else if (exp instanceof TrueExp) {
			return new BooleanValue(true);
		}
		// False表达式
		else if (exp instanceof FalseExp) {
			return new BooleanValue(false);
		} 
		// 数字表达式
		else if (exp instanceof NumberExp) {
			long num_val = evalNumberExp(NumberExp.valueOf(exp));
			return new NumberValue(num_val);
		} 
		// 运算表达式
		else if (exp instanceof BinopExp) {
			TokenKind op = BinopExp.valueOf(exp).getOp();
			Value left = evalExp(BinopExp.valueOf(exp).getLeft());
			Value right = evalExp(BinopExp.valueOf(exp).getRight() );
			return evalOp(left, right, op);
		}
		// 变量名表达式
		else if (exp instanceof NameExp) {
			return context.getEnv().get(NameExp.valueOf(exp).getName() );
		}
		else if (exp instanceof ListExp) {
			List<Value> vals = new ArrayList<>();
			for (Exp e : ListExp.valueOf(exp).getList() ) {
				vals.add(evalExp(e));
			}
			return new ListValue(vals);
		}
		else if (exp instanceof IfExp) {
			Value cond = evalExp(IfExp.valueOf(exp).getIfCond());
			if (((BooleanValue)cond).getVal()) {
				return evalExp(IfExp.valueOf(exp).getIfBlock());
			} else {
				return evalExp(IfExp.valueOf(exp).getElseBlock());
			}
		}
		else if (exp instanceof FuncCallExp) {
			return evalFuncCall(FuncCallExp.valueOf(exp));
		}
		return null;
	}
	
	public static Value evalOp(Value left, Value right, TokenKind op) {
		Value result = null; // 运算结果
		switch (op) {
		case TOKEN_OP_ADD:
			result = new NumberValue(
					NumberValue.getVal(left) + NumberValue.getVal(right));
			break;
		case TOKEN_OP_MINUS:
			result = new NumberValue(
					NumberValue.getVal(left) - NumberValue.getVal(right));
			break;
		case TOKEN_OP_MUL:
			result = new NumberValue(
					NumberValue.getVal(left) * NumberValue.getVal(right));			
			break;
		case TOKEN_OP_DIV:
			result = new NumberValue(
					NumberValue.getVal(left) / NumberValue.getVal(right));			
			break;
		case TOKEN_OP_MOD:
			result = new NumberValue(
					NumberValue.getVal(left) % NumberValue.getVal(right));
			break;
		case TOKEN_OP_GT:
			result = new BooleanValue(
					NumberValue.getVal(left) > NumberValue.getVal(right));
			break;
		case TOKEN_OP_GE:
			result = new BooleanValue(
					NumberValue.getVal(left) >= NumberValue.getVal(right));
			break;
		case TOKEN_OP_LT:
			result = new BooleanValue(
					NumberValue.getVal(left) < NumberValue.getVal(right));
			break;
		case TOKEN_OP_LE:
			result = new BooleanValue(
					NumberValue.getVal(left) <= NumberValue.getVal(right));
			break;
		case TOKEN_OP_EQ:
			result = new BooleanValue(
					NumberValue.getVal(left) == NumberValue.getVal(right));
			break;
		default:
			break;
		}
		return result;
	}
	
	public static Value evalFuncCall(FuncCallExp exp) {
		Evaluate env = new Evaluate();
		return null;
	}
	
	public static void evalPipeStat(PipeStat s) {
		Value v = evalExp(s.getExps().get(0));
		for (int i = 1; i < s.getExps().size(); i++) {
			// 函数调用
			if (s.getExps().get(i) instanceof NameExp) {
				String func_name = NameExp.valueOf(s.getExps().get(i)).getName();
				if (func_name.equals("stdout")) {
					System.out.println(v);
				} else if (func_name.equals("sum")) {
					return;
				}
			}
		}
	}
	
	public static void run(Block block) {
		for (Stat stat : block.getStats()) {
			
			// 管道语句
			if (stat instanceof PipeStat) {
				evalPipeStat((PipeStat)stat);
			}
			
			// 赋值语句
			else if (stat instanceof AssignStat) {
				List<NameExp> vars = ((AssignStat)stat).getVarList();
				List<Exp> values = ((AssignStat)stat).getExpList();
				assert (vars.size() == values.size());
				for (int i = 0; i < vars.size(); i++) {
					context.put(vars.get(i).getName(), evalExp(values.get(i)));
				}
			}
			
			// 函数定义语句
			else if (stat instanceof FuncStat) {
				
				Exp name = ((FuncStat)stat).getFuncName();
				String func_name = NameExp.valueOf(name).getName();
				List<NameExp> args = ((FuncStat)stat).getArgs();
				List<Stat> body = ((FuncStat)stat).getBlocks();
				
				context.put(func_name, 
					new FunctionValue(func_name, args, new Block(body)));
			}
			
		}
	}
}