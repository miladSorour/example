package org.milad.example.algo.string;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.err.println(validParentheses.isValid("()"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char p : s.toCharArray()) {
            if (p == '(')
                stack.push(')');
            else if (p == '[')
                stack.push(']');
            else if (p == '{')
                stack.push('}');
            else if (stack.isEmpty() || stack.pop() != p) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
