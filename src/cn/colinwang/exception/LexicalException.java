package cn.colinwang.exception;

/**
 * Invalid lexical found by lexer.
 * Created by colin on 3/29/16.
 */
public class LexicalException extends InterpretException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public LexicalException() {
        super("Invalid Lexical!");
    }
}
