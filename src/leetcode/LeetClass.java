package leetcode;

import java.util.Stack;

public class LeetClass {

    private static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    private static void num() {

    }

//    private static boolean isPalindromeInON(Node head){
//        Node head = new Node(1,new Node(2,new Node(2,new Node(1))));
//        System.out.println(isPalindromeInON(head));
//    }

    private static boolean isValidBrackets(String str) {


        System.out.println(isValidBrackets("]"));

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '{' || ch == '[' || ch == '(') stack.push(ch);
            else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty() || stack.peek() != getOpeningBracket(ch)) return false; // what if input is "]"
                else stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static char getOpeningBracket(char ch) {
        if (ch == '}') return '{';
        if (ch == ']') return '[';
        return '(';
    }

}
