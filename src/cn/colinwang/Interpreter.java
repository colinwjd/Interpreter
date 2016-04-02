package cn.colinwang;

import cn.colinwang.exception.SyntaxException;
import cn.colinwang.syntax.AbstractSyntaxTree;

/**
 * 解释器
 * Created by colin on 3/25/16.
 */
public class Interpreter {

    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    public int interpret() {
        AbstractSyntaxTree syntaxTree = this.parser.parse();
        return syntaxTree.visit();
    }

}
