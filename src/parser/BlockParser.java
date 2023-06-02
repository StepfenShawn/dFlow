package parser;

import java.util.ArrayList;
import java.util.List;

import ast.Block;
import ast.Stat;
import lexer.Lexer;
import lexer.TokenKind;

public class BlockParser {
	
	/*
	 * <block> = <stat> {; <stat>}
	 */
	
	static Block parseBlock(Lexer lexer) {
		Block block = new Block();
		block.setStats(parseStats(lexer));
		return block;
	}
	
	private static List<Stat> parseStats(Lexer lexer) {
        List<Stat> stats = new ArrayList<>();
        stats.add(StatParser.parseStat(lexer));
        while (lexer.lookAhead() == TokenKind.TOKEN_SEP_END) {
        	lexer.getToken();
            Stat stat = StatParser.parseStat(lexer);
            stats.add(stat);
        }
        return stats;
    }
}
