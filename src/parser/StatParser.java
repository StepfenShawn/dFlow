package parser;

import java.util.List;

import ast.Exp;
import ast.Stat;
import ast.exps.NameExp;
import ast.stats.AssignStat;
import ast.stats.PipeStat;
import lexer.Lexer;
import lexer.TokenKind;

public class StatParser {
	
	/**
	 * <stat> = ''
	 * 		  | 'let' <varlist> '=' <explist>
	 * 		  | 
	 */
	
	static Stat parseStat(Lexer lexer) {
		switch (lexer.lookAhead()) {
		case TOKEN_KW_FUNC:
			return parseFuncStat(lexer);
		case TOKEN_KW_IF:
			return parseIfStat(lexer);
		case TOKEN_KW_LET:
			return parseAssignStat(lexer);
		default:
			return parsePipeOrOtherStat(lexer);
		}
	}
	
	private static Stat parsePipeOrOtherStat(Lexer lexer) {
		Exp left = ExpParser.parseExp(lexer, 0);
		lexer.skipNextKind(TokenKind.TOKEN_PIPE);
		Exp right = ExpParser.parseExp(lexer, 0);
		return new PipeStat(left, right);
	}
	
	private static Stat parseIfStat(Lexer lexer) {
		lexer.getToken();
		return null;
	}
	
	private static Stat parseAssignStat(Lexer lexer) {
		lexer.skipNextKind(TokenKind.TOKEN_KW_LET);
		List<NameExp> varList = ExpParser.parseIdList(lexer);
		lexer.skipNextKind(TokenKind.TOKEN_OP_ASSIGN);
		List<Exp> valueList = ExpParser.parseExpList(lexer);
		return new AssignStat(lexer.getLine(), varList, valueList);
	}
	
	private static Stat parseFuncStat(Lexer lexer) {
		lexer.getToken();
		Exp func_name = ExpParser.parseExp(lexer, 0);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		return null;
	}
}
