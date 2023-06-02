package lexer;

public enum State {
    BEGIN,
    IDENTIFIER,
    NUMBER,
    STRING,
    COMMENT,
    FINISH,
};