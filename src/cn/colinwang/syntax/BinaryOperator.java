package cn.colinwang.syntax;

import cn.colinwang.Token;
import cn.colinwang.TokenTypes;
import cn.colinwang.exception.InterpretException;

/**
 * Binary operator node of an Abstract Syntax Tree.
 * Created by colin on 4/1/16.
 */
public class BinaryOperator extends AbstractSyntaxTree {
    private AbstractSyntaxTree left;
    private Token operator;
    private AbstractSyntaxTree right;

    public BinaryOperator(AbstractSyntaxTree left, Token operator, AbstractSyntaxTree right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public int visit() {
        TokenTypes operate = operator.getType();
        if (operate == TokenTypes.PLUS) {
            return left.visit() + right.visit();
        } else if (operate == TokenTypes.MINUS) {
            return left.visit() - right.visit();
        } else if (operate == TokenTypes.MUL) {
            return  left.visit() * right.visit();
        } else if (operate == TokenTypes.DIV) {
            return left.visit() / right.visit();
        }
        throw new InterpretException();
    }
}
