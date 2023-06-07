package parser;

import java.util.ArrayList;
import java.util.List;

import ast.Exp;
import ast.Stat;
import ast.exps.NameExp;
import ast.stats.AssignStat;
import ast.stats.PipeStat;
import ast.stats.ReturnStat;
import lexer.Lexer;
import lexer.TokenKind;

public class StatParser {
	
	static Stat parseStat(Lexer lexer) {
		switch (lexer.lookAhead()) {
		case TOKEN_KW_FUNC:
			return parseFuncStat(lexer);
		case TOKEN_KW_LET:
			return parseAssignStat(lexer);
		case TOKEN_KW_RETURN:
			return parseReturnStat(lexer);
		default:
			return parsePipeOrOtherStat(lexer);
		}
	}
	
	private static Stat parsePipeOrOtherStat(Lexer lexer) {
		List<Exp> PipeExps = new ArrayList<>();
		PipeExps.add(ExpParser.parseExp(lexer, 0));
		lexer.skipNextKind(TokenKind.TOKEN_PIPE);
		PipeExps.add(ExpParser.parseExp(lexer, 0));
		while (lexer.lookAhead() == TokenKind.TOKEN_PIPE) {
			lexer.getToken();
			PipeExps.add(ExpParser.parseExp(lexer, 0));
		}
		return new PipeStat(PipeExps);
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
		
		List<NameExp> argList = ExpParser.parseIdList(lexer);
		
		lexer.skipNextKind(TokenKind.TOKEN_SEP_LPAREN);
		lexer.skipNextKind(TokenKind.TOKEN_SEP_DO);
		
		return null;
	}
	
	private static Stat parseReturnStat(Lexer lexer) {
		lexer.getToken();
		List<Exp> rets = ExpParser.parseExpList(lexer);
		return new ReturnStat(rets);
	}
}
