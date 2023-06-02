package parser;

import ast.Exp;
import ast.exps.NameExp;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenKind;

/*
 * <prefixexp> = id
 * 			   | '(' exp ')'
 */
public class PrefixExpParser {
	static Exp parsePrefixExp(Lexer lexer) {
		Exp exp;
		if (lexer.lookAhead() == TokenKind.TOKEN_IDENTIFIER) {
			// id
			Token id = lexer.getToken();
			exp = new NameExp(id.getLine(), id.getValue());
		} else {
			// '(' exp ')'
			exp = parseParensExp(lexer);
		}
		
		return exp;
	}
	
	private static Exp parseParensExp(Lexer lexer) {
		lexer.getToken();      // (
		Exp exp = ExpParser.parseExp(lexer, 0);        // exp
		lexer.getToken();      // )
		return exp;
	}
}
