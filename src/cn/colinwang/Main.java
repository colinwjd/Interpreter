package cn.colinwang;

import cn.colinwang.exception.SyntaxException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = null;
            try {
                System.out.print("calc>>>");
                text = scanner.nextLine();
            } catch (SyntaxException e) {
                e.printStackTrace();
            }
            if (text != null && text.length() != 0) {
                Lexer lexer = new Lexer(text);
//                Parser parser = new Parser(lexer);
//                parser.parse();

                Interpreter interpreter = new Interpreter(lexer);
                int result = interpreter.getResult();
                System.out.println(result);
            }
        }
    }
}
