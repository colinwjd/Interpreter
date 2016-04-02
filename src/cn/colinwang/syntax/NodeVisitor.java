package cn.colinwang.syntax;

/**
 * Visitor pattern of the design-patterns.
 * Each type of node in Abstract Syntax Tree has its own visit logic.
 * Created by colin on 4/2/16.
 */
public interface NodeVisitor {
    int visit();
}
