package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Block;
import ast.Exp;
import ast.Stat;
import ast.exps.BinopExp;
import ast.exps.IfExp;
import ast.exps.LambdaExp;
import ast.exps.ListExp;
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
		put("+", new Priority(30, 40, 50));
		put("-", new Priority(30, 40, 50));
		put("*", new Priority(50, 60, 0));
		put("/", new Priority(50, 60, 0));
		put("%", new Priority(20, 30, 0));
		put(">", new Priority(0, 10, 0));
		put("<", new Priority(0, 10, 0));
		put(">=", new Priority(0, 10, 0));
		put("<=", new Priority(0, 10, 0));
		put("==", new Priority(0, 10, 0));
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
			if (tok_op.getKind() == TokenKind.TOKEN_EOF) {
				break;
			}
			if ("+-*/%>=<==".indexOf(tok_op.getValue()) == -1) {
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
	
	public static Exp parseListExpr(Lexer lexer) {
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LBRACK);
		List<Exp> expList = parseExpList(lexer);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_RBRACK);
		Exp exp = new ListExp(lexer.getLine(), expList);
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
		case TOKEN_SEP_LBRACK:
			return parseListExpr(lexer);
		case TOKEN_KW_LAMBDA:
			return parseLambdaExpr(lexer);
		case TOKEN_KW_IF:
			return parseIfExpr(lexer);
		default:
			return null;
		}
	}
	
	public static List<NameExp> parseIdList(Lexer lexer) {
		List<NameExp> idList = new ArrayList<>();
		if (lexer.lookAhead() != TokenKind.TOKEN_IDENTIFIER) {
			throw new RuntimeException("Excepted " + TokenKind.TOKEN_IDENTIFIER);
		}
		idList.add(new NameExp(lexer.getToken()));
		while (lexer.lookAhead() == TokenKind.TOKEN_SEP_COMMA) {
			lexer.getToken();
			if (lexer.lookAhead() != TokenKind.TOKEN_IDENTIFIER) {
				throw new RuntimeException("");
			}
			idList.add(new NameExp(lexer.getToken()));
		}
		return idList;
	}
	
	public static Exp parseLambdaExpr(Lexer lexer) {

		lexer.skipNextKind(TokenKind.TOKEN_KW_LAMBDA);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		
		List<NameExp> argList = ExpParser.parseIdList(lexer);
		
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_DO);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LCURLY);
		
		Exp body = ExpParser.parseExp(lexer, 0);
		
		lexer.skipNextKind(TokenKind.TOKEN_SEP_RCURLY);
		
		return new LambdaExp(lexer.getLine(), argList, body);
	}
	
	public static Exp parseIfExpr(Lexer lexer) {
		lexer.skipNextKind(TokenKind.TOKEN_KW_IF);
		Exp ifCond = ExpParser.parseExp(lexer, 0);
		Exp ifBlock = ExpParser.parseExp(lexer, 0);
		Exp elseBlock = null;
		if (lexer.lookAhead() == TokenKind.TOKEN_KW_ELSE) {
			lexer.getToken();
			elseBlock = ExpParser.parseExp(lexer, 0);
		}
		return new IfExp(lexer.getLine(), ifCond, ifBlock, elseBlock);
	}
}
