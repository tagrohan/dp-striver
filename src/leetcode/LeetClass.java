package leetcode;

import java.util.Stack;

public class LeetClass {

    public static void main(String[] args) {
        System.out.println(isValidBrackets("]"));
    }

    private static boolean isValidBrackets(String str){
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '{' || ch == '[' || ch == '(') stack.push(ch);
            else if(ch == '}' || ch == ']' || ch == ')'){
                if(stack.isEmpty() || stack.peek() != getOpeningBracket(ch)) return false; // what if input is "]"
                else stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static char getOpeningBracket(char ch){
        if(ch == '}') return '{';
        if(ch == ']') return '[';
        return '(';
    }

}
