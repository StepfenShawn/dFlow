package lexer;

import java.util.HashMap;
import java.util.Map;

public class Token {
    
    static final Map<String, TokenKind> keywords = new HashMap<>(); // 关键字
    static final Map<Character, TokenKind> separator = new HashMap<>(); // 分隔符
    
    static {
        keywords.put("if", TokenKind.TOKEN_KW_IF);
        keywords.put("else", TokenKind.TOKEN_KW_ELSE);
        keywords.put("func", TokenKind.TOKEN_KW_FUNC);
        keywords.put("lambda", TokenKind.TOKEN_KW_LAMBDA);
        keywords.put("let", TokenKind.TOKEN_KW_LET);
        keywords.put("return", TokenKind.TOKEN_KW_RETURN);
        
        separator.put('+', TokenKind.TOKEN_OP_ADD);
        separator.put('-', TokenKind.TOKEN_OP_MINUS);
        separator.put('*', TokenKind.TOKEN_OP_MUL);
        separator.put('/', TokenKind.TOKEN_OP_DIV);
        separator.put('%', TokenKind.TOKEN_OP_MOD);
        
        separator.put('(', TokenKind.TOKEN_SEP_LPAREN);
        separator.put(')', TokenKind.TOKEN_SEP_RPAREN);
        separator.put('{', TokenKind.TOKEN_SEP_LCURLY);
        separator.put('}', TokenKind.TOKEN_SEP_RCURLY);
        separator.put('[', TokenKind.TOKEN_SEP_LBRACK);
        separator.put(']', TokenKind.TOKEN_SEP_RBRACK);
        separator.put(';', TokenKind.TOKEN_SEP_END);
        separator.put(',', TokenKind.TOKEN_SEP_COMMA);
        
        separator.put('|', TokenKind.TOKEN_PIPE);
    }

    private final int line;
    private final TokenKind kind;
    private final String value;

    public Token(int line, TokenKind kind, String value) {
        this.line = line;
        this.kind = kind;
        this.value = value;
    }
    
    public TokenKind getKind() {
    	return this.kind;
    }
    
    public int getLine() {
    	return this.line;
    }
    
    public String getValue() {
    	return this.value;
    }
    
    @Override
    public String toString() {
    	return this.kind + ":" + this.value;
    }

}