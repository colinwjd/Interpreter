package cn.colinwang.exception;

/**
 * Created by colin on 3/25/16.
 */
public class SyntaxException extends RuntimeException {
    public SyntaxException() {
        super("Syntax Error!");
    }
}
