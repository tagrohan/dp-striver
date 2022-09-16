package leetcode;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return List.of();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            TreeNode temp = stack.peek();
            if (temp.right == null && temp.left == null) {
                list.add(temp.val);

                stack.pop();
                continue;
            }
            if (temp.right != null) stack.push(temp.right);
            if (temp.left != null) stack.push(temp.left);
        }
        return list;
    }

    public static void main(String[] args) {
        simplyPath("/a/./b/../../c/");
    }

    private static void simplyPath(String str) {
        String[] ctr = str.split("/");
        StringBuilder builder = new StringBuilder();
        for (String s : ctr) {
           String ss = s.trim();
           if(ss.isBlank() ||ss.equals("..") || ss.equals(".")) continue;
           builder.append("/").append(ss);
        }
       System.out.println(builder);
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
