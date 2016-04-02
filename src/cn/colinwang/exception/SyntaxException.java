package cn.colinwang.exception;

/**
 * Invalid syntax found by parser.
 * Created by colin on 3/25/16.
 */
public class SyntaxException extends InterpretException {
    public SyntaxException() {
        super("Invalid Syntax!");
    }
}
