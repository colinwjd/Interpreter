package cn.colinwang;

import cn.colinwang.exception.SyntaxException;

/**
 * Created by colin on 3/25/16.
 */
public class Interpreter {
    private char[] text;
    private Token currentToken;
    private int index;
    private Character currentChar;

    public Interpreter(String text) {
        this.text = text.toCharArray();
        this.currentToken = null;
        this.index = 0;
        this.currentChar = this.text[index];
    }

    public Token getNextToken() throws SyntaxException {
        while (currentChar != null) {
            if (Character.isSpaceChar(currentChar)) {
                this.skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) {
                int number = this.walkNumber();
                return new Token<>(TokenTypes.INTEGER, number);
            }
            if (currentChar.equals('+')) {
                Token token = new Token<>(TokenTypes.PLUS, currentChar);
                this.advance();
                return token;
            }
            if (currentChar.equals('-')) {
                Token token = new Token<>(TokenTypes.MINUS, currentChar);
                this.advance();
                return token;
            }
            this.syntaxError();
        }
        return new Token<>(TokenTypes.EOF, null);
    }

    private void syntaxError() throws SyntaxException {
        throw new SyntaxException();
    }

    private void advance() {
        index++;
        if (index == text.length) {
            currentChar = null;
        } else {
            currentChar = text[index];
        }
    }

    private void skipWhitespace() {
        while (currentChar != null && Character.isSpaceChar(currentChar)) {
            this.advance();
        }
    }

    private int walkNumber() {
        StringBuffer result = new StringBuffer();
        while (currentChar != null && Character.isDigit(currentChar)) {
            result.append(currentChar);
            this.advance();
        }
        return Integer.parseInt(result.toString());
    }

    public int calcExpr() throws SyntaxException {
        // init the first token
        currentToken = this.getNextToken();
        int result = this.term();
        while (currentToken.getType() == TokenTypes.PLUS ||
                currentToken.getType() == TokenTypes.MINUS) {
            if (currentToken.getType() == TokenTypes.PLUS) {
                this.walk(TokenTypes.PLUS);
                result += this.term();
            }
            if (currentToken.getType() == TokenTypes.MINUS) {
                this.walk(TokenTypes.MINUS);
                result -= this.term();
            }
        }
        return result;
    }

    private void walk(TokenTypes type) throws SyntaxException {
        Token token = this.currentToken;
        if (token.getType() == type) {
            currentToken = this.getNextToken();
        } else {
            this.syntaxError();
        }
    }

    private int term() {
        Token token = currentToken;
        this.walk(TokenTypes.INTEGER);
        return (int) token.getValue();
    }

}
