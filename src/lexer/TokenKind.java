package lexer;

public enum TokenKind {
    TOKEN_EOF, // end-of-file
    TOKEN_PIPE, // |
    TOKEN_SEP_LPAREN , // (
    TOKEN_SEP_RPAREN , // )
    TOKEN_SEP_LCURLY , // {
    TOKEN_SEP_RCURLY , // }
    TOKEN_SEP_LBRACK , // [
    TOKEN_SEP_RBRACK , // ]
    TOKEN_SEP_COMMA  , // ,
    TOKEN_SEP_END	 , // ;
    TOKEN_SEP_DO	 , // =>

    TOKEN_OP_ASSIGN  , // =
    TOKEN_OP_MINUS   , // -
    TOKEN_OP_WAVE    , // ~
    TOKEN_OP_ADD     , // +
    TOKEN_OP_MUL     , // *
    TOKEN_OP_DIV     , // /
    TOKEN_OP_IDIV    , // //
    TOKEN_OP_POW     , // ^
    TOKEN_OP_MOD     , // %
    
    TOKEN_OP_GT		 , // >
    TOKEN_OP_GE		 , // >=
    TOKEN_OP_LT		 , // <
    TOKEN_OP_LE		 , // <=
    TOKEN_OP_EQ		 , // ==

    TOKEN_KW_IF      , // if
    TOKEN_KW_ELIF    , // elif
    TOKEN_KW_ELSE    , // else
    TOKEN_KW_LAMBDA	 , // lambda
    TOKEN_KW_FUNC	 , // fnuc
    TOKEN_KW_LET	 , // let
    TOKEN_KW_RETURN  , // return

    TOKEN_NUMBER,
    TOKEN_STRING,
    TOKEN_IDENTIFIER,
}
