package parser;

import ast.Block;
import lexer.Lexer;
import lexer.TokenKind;

public class Parser {
	public static Block parse(String source, String sourceName) {
		Lexer lexer = new Lexer(source, sourceName);
		System.out.println(source);
		Block block = BlockParser.parseBlock(lexer);
		System.out.println(block.getStats());
		lexer.skipNextKind(TokenKind.TOKEN_EOF);
		return block;
	}
}