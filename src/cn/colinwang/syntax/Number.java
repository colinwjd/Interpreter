package cn.colinwang.syntax;

import cn.colinwang.Token;

/**
 * Number node of an Abstract Syntax Tree.
 * Created by colin on 4/1/16.
 */
public class Number extends AbstractSyntaxTree {
    private int value;

    public Number(Token token) {
        this.value = (int) token.getValue();
    }

    @Override
    public int visit() {
        return value;
    }
}
