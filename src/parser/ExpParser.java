package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Exp;
import ast.exps.BinopExp;
import ast.exps.NumberExp;
import ast.exps.NameExp;
import ast.exps.StringExp;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenKind;

/*
 * <explist> = <exp> {',', exp}
 * 
 * <exp> = <primary> <binop_rhs>
 * 
 * <primary> = identifier
 * 			 | Number
 * 			 | '(' <exp> ')'
 * 
 * */

class Priority {
	public int left_prec;
	public int right_prec;
	public int associate;
	
	public Priority(int left_prec, int right_prec, int associate) {
		this.left_prec = left_prec;
		this.right_prec = right_prec;
		this.associate = associate;
	}
	
}


public class ExpParser {
	
	static final Map<String, Priority> precedence = new HashMap<String, Priority>(){{
		put("+", new Priority(10, 20, 50));
		put("-", new Priority(10, 20, 50));
		put("*", new Priority(30, 40, 0));
		put("/", new Priority(30, 40, 0));
	}};
	
	public static List<Exp> parseExpList(Lexer lexer) {
		List<Exp> exps = new ArrayList<>();
		exps.add(parseExp(lexer, 0));
		while (lexer.lookAhead() == TokenKind.TOKEN_SEP_COMMA) {
			lexer.getToken();
			exps.add(parseExp(lexer, 0));
		}
		return exps;
	}
	
	public static Exp parseExp(Lexer lexer, int prec) {
		Exp lhs = parsePrimary(lexer);
		while (true) {
			Token tok_op = lexer.peek();
			if (! tok_op.getValue().equals("+") &&
				! tok_op.getValue().equals("*") &&
				! tok_op.getValue().equals("/") &&
				! tok_op.getValue().equals("-")) {
				break;
			}
			
			Priority p = precedence.get(tok_op.getValue());
			if (p.left_prec < prec)
				break;
			lexer.getToken();
			Exp rhs = parseExp(lexer, p.right_prec);
			lhs = new BinopExp(tok_op, lhs, rhs);
		}
		return lhs;
	}
	
	
	public static Exp parseParenExpr(Lexer lexer) {
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		Exp exp = parseExp(lexer, 0);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_RPAREN);
		return exp;
		
	}
	
	private static Exp parsePrimary(Lexer lexer) {
		switch (lexer.lookAhead()) {
		case TOKEN_STRING: // String
			return new StringExp(lexer.getToken());
		case TOKEN_NUMBER:
			return new NumberExp(lexer.getToken());
		case TOKEN_IDENTIFIER:
			return new NameExp(lexer.getToken());
		case TOKEN_SEP_LPAREN:
			return parseParenExpr(lexer);
		default:
			return null;
		}
	}
	
	private static List<Exp> parseIdList(Lexer lexer) {
		List<Exp> idList = new ArrayList<>();
		if (lexer.lookAhead() != TokenKind.TOKEN_IDENTIFIER) {
			throw new RuntimeException("");
		}
//		idList.add(new NameExp());
//		while (lexer.lookAhead() == TokenKind.TOKEN_SEP_COMMA) {
//			lexer.getToken();
//			if (lexer.lookAhead() != TokenKind.TOKEN_IDENTIFIER) {
//				throw new RuntimeException("");
//			}
//			idList.add(new Name);
//		}
//		return idList;
		return null;
	}
}
