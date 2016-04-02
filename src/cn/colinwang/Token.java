package cn.colinwang;

/**
 * Token used by parser to construct an Abstract Syntax Tree.
 * Created by colin on 3/25/16.
 */
public class Token<T> {
    private final TokenTypes type;
    private final T value;

    public Token(TokenTypes type, T value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

    public TokenTypes getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

}
