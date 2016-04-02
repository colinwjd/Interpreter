package cn.colinwang.syntax;

import cn.colinwang.Token;

/**
 * Created by colin on 4/1/16.
 */
public class Number extends AbstractSyntaxTree {
    private Token token;
    private int value;

    public Number(Token token) {
        this.token = token;
        this.value = (int) this.token.getValue();
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int visit() {
        return value;
    }
}
