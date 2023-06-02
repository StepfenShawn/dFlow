package lexer;

public enum TokenKind {
    TOKEN_EOF, // end-of-file
    TOKEN_PIPE, // |
    TOKEN_SEP_LPAREN , // (
    TOKEN_SEP_RPAREN , // )
    TOKEN_SEP_LCURLY , // {
    TOKEN_SEP_RCURLY , // }
    TOKEN_SEP_COMMA  , // ,
    TOKEN_SEP_END	 , // ;

    TOKEN_OP_ASSIGN  , // =
    TOKEN_OP_MINUS   , // -
    TOKEN_OP_WAVE    , // ~
    TOKEN_OP_ADD     , // +
    TOKEN_OP_MUL     , // *
    TOKEN_OP_DIV     , // /
    TOKEN_OP_IDIV    , // //
    TOKEN_OP_POW     , // ^
    TOKEN_OP_MOD     , // %
    TOKEN_OP_BAND    , // &
    TOKEN_OP_BOR     , // |
    TOKEN_OP_SHR     , // >>
    TOKEN_OP_SHL     , // <<

    TOKEN_KW_IF      , // if
    TOKEN_KW_ELIF    , // elif
    TOKEN_KW_ELSE    , // else
    TOKEN_KW_FUNC	 , // func
    TOKEN_KW_LET	 , // let

    TOKEN_NUMBER,
    TOKEN_STRING,
    TOKEN_IDENTIFIER,
}
