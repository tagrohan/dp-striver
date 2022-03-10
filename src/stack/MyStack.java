package stack;

import java.util.Objects;
import java.util.Stack;

public class MyStack {

   // todo in O(i) still pending
   private final Stack<Integer> stack = new Stack<>();
   private final Stack<Integer> minValStack = new Stack<>();

   public static void main(String[] args) {
      // 18, 19, 29, 15, 16
      MyStack stack = new MyStack();
      stack.addAll(18, 19, 29, 15, 16);
      System.out.println(stack.min());
      stack.poll();
      stack.poll();
      System.out.println(stack.min());
   }

   private void addAll(int... ele) {
      for (int var : ele) {
         add(var);
      }
   }

   private void add(int ele) {
      stack.push(ele);
      if (minValStack.isEmpty() || minValStack.peek() > ele) {
         minValStack.push(ele);
      }
   }

   private int min() {
      return minValStack.isEmpty() ? -1 : minValStack.peek();
   }

   private int poll() {
      if (stack.isEmpty()) return -1;
      if (Objects.equals(stack.peek(), minValStack.peek())) minValStack.pop();
      return stack.pop();
   }
   // from here we are going to implement version 2 of it, O(1) space;
}
