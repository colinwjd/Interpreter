package cn.colinwang.syntax;

import cn.colinwang.Token;
import cn.colinwang.TokenTypes;

/**
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

    public AbstractSyntaxTree getLeft() {
        return this.left;
    }

    public AbstractSyntaxTree getRight() {
        return this.right;
    }

    public TokenTypes getOperator() {
        return this.operator.getType();
    }
}
