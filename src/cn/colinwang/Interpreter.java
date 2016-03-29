package cn.colinwang;

import cn.colinwang.exception.SyntaxException;

/**
 * Created by colin on 3/25/16.
 */
public class Interpreter {
    private Lexer lexer;
    private Token currentToken;

    public Interpreter(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    public int getResult() {
        return this.expr();
    }

    private int expr() {
        int result = this.term();
        while (currentToken.getType() == TokenTypes.PLUS || currentToken.getType() == TokenTypes.MINUS) {
            if (currentToken.getType() == TokenTypes.PLUS) {
                this.walk(TokenTypes.PLUS);
                result += this.term();
            } else if (currentToken.getType() == TokenTypes.MINUS) {
                this.walk(TokenTypes.MINUS);
                result -= this.term();
            }
        }
        return result;
    }

    private int term() {
        int result = this.factor();
        while (currentToken.getType() == TokenTypes.MUL ||
                currentToken.getType() == TokenTypes.DIV) {
            if (currentToken.getType() == TokenTypes.MUL) {
                this.walk(TokenTypes.MUL);
                result *= this.factor();
            }
            if (currentToken.getType() == TokenTypes.DIV) {
                this.walk(TokenTypes.DIV);
                result /= this.factor();
            }
        }
        return result;
    }

    private int factor() {
        Token token = currentToken;
        this.walk(TokenTypes.INTEGER);
        return (int) token.getValue();
    }

    private void walk(TokenTypes type) {
        Token token = this.currentToken;
        if (token.getType() == type) {
            currentToken = lexer.getNextToken();
        } else {
            throw new SyntaxException();
        }
    }
}
