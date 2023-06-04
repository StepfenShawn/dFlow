package lexer;

import java.util.*;

public class Lexer {

    private String source;
    private String sourceName;
    private int line;
    
    private int colOffset = 0;
    
    public Lexer(String source, String sourceName) {
        this.source = source;
        this.sourceName = sourceName;
        this.line = 1;
    }
    
    private char nextc() {
        return source.charAt(this.colOffset++);
    }
    
    private void back(int step) {
    	this.colOffset -= step;
    }
    
    public Token getToken() {
    	State state = State.BEGIN;
    	String token_value = "";
    	TokenKind kind = TokenKind.TOKEN_EOF;
    	char c;
    	
    	if (isEndOfFile()) {
    		return new Token(this.line, kind, "");
    	}
    	
    	do {
    		c = this.nextc();
    	} while (isWhiteSpace(c));
    	
    	while (isNewLine(c) && !isEndOfFile()) {
    		c = this.nextc();
    		if (isEndOfFile()) {
        		return new Token(this.line, kind, "");
        	}
    	}
    	
    	while (state != State.FINISH) {
    		switch (state) {
    		case BEGIN:
    			if (Character.isDigit(c)) {
    				state = State.NUMBER;
    				kind = TokenKind.TOKEN_NUMBER;
    			} 
    			else if (Character.isLetter(c)) {
    				state = State.IDENTIFIER;
    				kind = TokenKind.TOKEN_IDENTIFIER;
    			} 
    			
    			else if (c == '"') {
    				state = State.STRING;
    				kind = TokenKind.TOKEN_STRING;
    			}
    			
    			else if (isNewLine(c)) {
    				this.line++;
    				state = State.FINISH;
    			}
    			
    			else if ("+-*/%(){}[]|,;".lastIndexOf(c) != -1) {
    				state = State.FINISH;
    				kind = Token.separator.get(c);
    				token_value += c;
    			}
    			
    			else if (c == '>') {
    				token_value += c;
    				if (nextc() == '=') {
    					token_value += '=';
    					kind = TokenKind.TOKEN_OP_GE;
    				} else {
    					back(1);
    					kind = TokenKind.TOKEN_OP_GT;
    				}
    				state = State.FINISH;
    			}
    			
    			else if (c == '<') {
    				token_value += c;
    				if (nextc() == '=') {
    					token_value += '=';
    					kind = TokenKind.TOKEN_OP_LE;
    				} else {
    					back(1);
    					kind = TokenKind.TOKEN_OP_LT;
    				}
    				state = State.FINISH;
    			}
    			
    			else if (c == '=') {
    				token_value += c;
    				if (nextc() == '=') {
    					token_value += '=';
    					kind = TokenKind.TOKEN_OP_EQ;
    				} else {
    					back(1);
    					kind = TokenKind.TOKEN_OP_ASSIGN;
    				}
    				state = State.FINISH;
    			}
    			
    			else {
    				throw new RuntimeException("Unknown token: " + c);
    			}
    			break;
    		
    		
    		case IDENTIFIER:
    			token_value += c;
    			while (!isEndOfFile()) {
    				c = this.nextc();
    				if(!(c == '_' || Character.isLetterOrDigit(c))) {
    					colOffset--;
    					break;
    				}
    				token_value += c;
    			}
    			if (Token.keywords.containsKey(token_value)) {
    				kind = Token.keywords.get(token_value);
    			}
    			state = State.FINISH;
    			break;
    			
    		case STRING:
    			token_value += c;
    			while (!isEndOfFile()) {
    				c = this.nextc();
    				if (c == '"') {
    					token_value += c;
    					break;
    				}
    				token_value += c;
    			}
    			state = State.FINISH;
    			break;
    			
    		case NUMBER:
    			token_value += c;
    			while (!isEndOfFile()) {
    				c = this.nextc();
    				if (!Character.isDigit(c)) {
    					colOffset--;
    					break;
    				}
    				token_value += c;
    			}
    			state = State.FINISH;
    			break;
    			
    		}
    	}
    	return new Token(this.line, kind, token_value);
    }

    public TokenKind lookAhead() {
    	Token tok = this.getToken();
    	// 回退
    	back(tok.getValue().length());
    	return tok.getKind();
    }
    
    public Token peek() {
    	Token tok = this.getToken();
    	// 回退
    	back(tok.getValue().length());
    	return tok;
    }
    
    public void skipNextKind(TokenKind kind) {
    	if (lookAhead() == kind) {
    		this.getToken();
    	} else {
    		throw new RuntimeException("Excepted TokenKind (" + kind + ") in " + this.line);
    	}
    }
    
    static boolean isWhiteSpace(char ch) {
        switch (ch) {
            case '\t':
            case '\f':
            case ' ':
                return true;
        }
        return false;
    }
    
    static boolean isNewLine(char ch) {
    	return ch == '\n' || ch == '\r';
    }
    
    private boolean isEndOfFile() {
    	return this.colOffset >= source.length();
    }
    
    public int getLine() {
    	return this.line;
    }
}