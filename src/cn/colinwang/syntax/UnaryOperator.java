package cn.colinwang.syntax;

import cn.colinwang.Token;
import cn.colinwang.TokenTypes;
import cn.colinwang.exception.InterpretException;

/**
 * Created by colin on 4/1/16.
 */
public class UnaryOperator extends AbstractSyntaxTree {
    private Token operator;
    private AbstractSyntaxTree right;

    public UnaryOperator(Token operator, AbstractSyntaxTree right) {
        this.operator = operator;
        this.right = right;
    }

    public AbstractSyntaxTree getRight() {
        return this.right;
    }

    public TokenTypes getOperator() {
        return this.operator.getType();
    }

    @Override
    public int visit() {
        TokenTypes operate = operator.getType();
        if (operate == TokenTypes.PLUS) {
            return right.visit();
        } else if (operate == TokenTypes.MINUS) {
            return -right.visit();
        }
        throw new InterpretException();

    }
}
