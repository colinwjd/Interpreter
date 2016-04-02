package cn.colinwang;

import cn.colinwang.syntax.AbstractSyntaxTree;

/**
 * An interpreter that interpret the Abstract Syntax Tree.
 * Created by colin on 3/25/16.
 */
public class Interpreter {

    private final Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    public int interpret() {
        AbstractSyntaxTree syntaxTree = this.parser.parse();
        return syntaxTree.visit();
    }

}
