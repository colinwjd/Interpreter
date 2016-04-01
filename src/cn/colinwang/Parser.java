package cn.colinwang;

import cn.colinwang.exception.SyntaxException;
import cn.colinwang.syntax.AbstractSyntaxTree;
import cn.colinwang.syntax.BinaryOperator;
import cn.colinwang.syntax.Number;
import cn.colinwang.syntax.UnaryOperator;

/**
 * Parser for constructing an abstract syntax tree.
 * If something was wrong, it should throw syntax exception.
 * Created by colin on 3/29/16.
 */
public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        currentToken = this.lexer.getNextToken();
    }

    /**
     * expr: term ((PLUS | MINUS) term)*
     * term: factor ((MUL | DIV) factor)*
     * factor: (PLUS | MINUS) factor | INTEGER | LPAREN expr RPAREN
     */
    private AbstractSyntaxTree expr() {
        Token token = currentToken;
        AbstractSyntaxTree node = this.term();
        while (token.getType() == TokenTypes.PLUS || token.getType() == TokenTypes.MINUS) {
            if (token.getType() == TokenTypes.PLUS) {
                this.walk(TokenTypes.PLUS);
                return new BinaryOperator(node, token, this.term());
            } else if (token.getType() == TokenTypes.MINUS) {
                this.walk(TokenTypes.MINUS);
                return new BinaryOperator(node, token, this.term());
            }
        }
        return node;
    }

    /**
     * term: factor ((MUL | DIV) factor)*
     */
    private AbstractSyntaxTree term() {
        Token token = currentToken;
        AbstractSyntaxTree node = this.factor();
        while (token.getType() == TokenTypes.MUL || token.getType() == TokenTypes.DIV) {
            if (token.getType() == TokenTypes.MUL) {
                this.walk(TokenTypes.MUL);
                return new BinaryOperator(node, token, this.factor());
            } else if (currentToken.getType() == TokenTypes.DIV) {
                this.walk(TokenTypes.DIV);
                return new BinaryOperator(node, token, this.factor());
            }
        }
        return node;
    }

    /**
     * factor: (PLUS | MINUS) factor | INTEGER | LPAREN expr RPAREN
     */
    private AbstractSyntaxTree factor() {
        Token token = currentToken;
        if (token.getType() == TokenTypes.PLUS) {
            this.walk(TokenTypes.PLUS);
            return new UnaryOperator(token, this.factor());
        } else if (token.getType() == TokenTypes.MINUS) {
            this.walk(TokenTypes.MINUS);
            return new UnaryOperator(token, this.factor());
        } else if (token.getType() == TokenTypes.INTEGER) {
            this.walk(TokenTypes.INTEGER);
            return new Number(token);
        } else if (token.getType() == TokenTypes.LPAREN) {
            this.walk(TokenTypes.LPAREN);
            AbstractSyntaxTree node =  this.expr();
            this.walk(TokenTypes.RPAREN);
            return node;
        }
        throw new SyntaxException();
    }

    private void walk(TokenTypes tokenTypes) {
        if (currentToken.getType() == tokenTypes) {
            currentToken = lexer.getNextToken();
        } else {
            throw new SyntaxException();
        }
    }

    public AbstractSyntaxTree parse() {
        return this.expr();
    }
}
