package cn.colinwang;

import cn.colinwang.exception.SyntaxException;

/**
 * Created by colin on 3/29/16.
 */
public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        currentToken = this.lexer.getNextToken();
    }

    private void error() {
        throw new SyntaxException();
    }

    private void expr() {
        this.term();
        while (currentToken.getType() == TokenTypes.PLUS || currentToken.getType() == TokenTypes.MINUS) {
            if (currentToken.getType() == TokenTypes.PLUS) {
                this.walk(TokenTypes.PLUS);
                this.term();
            } else if (currentToken.getType() == TokenTypes.MINUS) {
                this.walk(TokenTypes.MINUS);
                this.term();
            }
        }
    }

    private void term() {
        this.factor();
        while (currentToken.getType() == TokenTypes.MUL || currentToken.getType() == TokenTypes.DIV) {
            if (currentToken.getType() == TokenTypes.MUL) {
                this.walk(TokenTypes.MUL);
                this.factor();
            } else if (currentToken.getType() == TokenTypes.DIV) {
                this.walk(TokenTypes.DIV);
                this.factor();
            }
        }
    }

    private void factor() {
        this.walk(TokenTypes.INTEGER);
    }

    private void walk(TokenTypes tokenTypes) {
        if (currentToken.getType() == tokenTypes) {
            currentToken = lexer.getNextToken();
        } else {
            this.error();
        }
    }

    public void parse() {
        this.expr();
    }
}
