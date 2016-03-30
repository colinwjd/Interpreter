package cn.colinwang;

import cn.colinwang.exception.LexicalException;

/**
 * 词法分析器
 * Created by colin on 3/29/16.
 */
public class Lexer {
    private char[] text;
    private int index;
    private Character currentChar;

    public Lexer(String text) {
        this.text = text.toCharArray();
        index = 0;
        currentChar = this.text[index];
    }

    private void error() {
        throw new LexicalException();
    }

    public Token getNextToken() {
        while (currentChar != null) {
            if (Character.isSpaceChar(currentChar)) {
                this.skipWhiteSpace();
                continue;
            } else if (Character.isDigit(currentChar)) {
                int number = this.number();
                return new Token<>(TokenTypes.INTEGER, number);
            } else if (currentChar.equals('+')) {
                this.advance();
                return new Token<>(TokenTypes.PLUS, '+');
            } else if (currentChar.equals('-')) {
                this.advance();
                return new Token<>(TokenTypes.MINUS, '-');
            } else if (currentChar.equals('*')) {
                this.advance();
                return new Token<>(TokenTypes.MUL, '*');
            } else if (currentChar.equals('/')) {
                this.advance();
                return new Token<>(TokenTypes.DIV, '/');
            } else if (currentChar.equals('(')) {
                this.advance();
                return new Token<>(TokenTypes.LPAREN, '(');
            } else if (currentChar.equals(')')) {
                this.advance();
                return new Token<>(TokenTypes.RPAREN, ')');
            } else {
                this.error();
            }
        }
        return new Token<>(TokenTypes.EOF, null);
    }

    private void advance() {
        index++;
        if (index == text.length) {
            currentChar = null;
        } else {
            currentChar = text[index];
        }
    }

    private void skipWhiteSpace() {
        while (currentChar != null && Character.isSpaceChar(currentChar)) {
            this.advance();
        }
    }

    private int number() {
        StringBuffer result = new StringBuffer();
        while (currentChar != null && Character.isDigit(currentChar)) {
            result.append(currentChar);
            this.advance();
        }
        return Integer.parseInt(result.toString());
    }
}
