package stack;

import java.util.Arrays;
import java.util.Stack;

public class StackMainAditya {
   public static void main(String[] args) {
      System.out.println(Arrays.toString(stockSpain(new int[]{100, 80, 60, 60, 60, 75, 85})));
   }

   private static int[] stockSpain(int[] arr) {
//      System.out.println(Arrays.toString(stockSpain(new int[]{100, 80, 60, 60, 60, 75, 85})));
      int len = arr.length;
      int[] timeDiff = new int[len];
      Stack<Integer> stack = new Stack<>();

      for (int i = 0; i < len; i++) {
         while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
            stack.pop();
         }
         timeDiff[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
         stack.push(i);
      }
      return timeDiff;
   }

   private static int[] smallestToTheLeft(int[] arr) {
//      System.out.println(Arrays.toString(smallestToTheLeft(new int[]{1, 3, 2, 3})));
      int len = arr.length;
      int[] STL = new int[len];
      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < len; i++) {
         while (!stack.isEmpty() && stack.peek() >= arr[i]) {
            stack.pop();
         }
         STL[i] = stack.isEmpty() ? -1 : stack.peek();
         stack.push(arr[i]);
      }
      return STL;
   }

   private static int[] smallestToTheRight(int[] arr) {
//      System.out.println(Arrays.toString(smallestToTheRight(new int[]{14, 13, 21, 3})));
      int len = arr.length;
      Stack<Integer> stack = new Stack<>();
      int[] NSE = new int[len];

      for (int i = len - 1; i >= 0; i--) {
         while (!stack.isEmpty() && stack.peek() >= arr[i]) {
            stack.pop();
         }
         NSE[i] = stack.isEmpty() ? -1 : stack.peek();// same code via ternary operator
         stack.push(arr[i]);
      }
      return NSE;
   }

   // its iteration is from right to left else is same as NGE
   private static int[] greatestToTheLeft(int[] arr) {
//      System.out.println(Arrays.toString(greatestToTheLeft(new int[]{14, 13, 21, 3})));
      int len = arr.length;
      int[] GTL = new int[len];
      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < len; i++) {
         while (!stack.isEmpty() && stack.peek() <= arr[i]) {
            stack.pop();
         }
         if (stack.isEmpty()) GTL[i] = -1;
         else GTL[i] = stack.peek();
         stack.push(arr[i]);
      }
      return GTL;
   }

   private static int[] greatestToTheRight(int[] arr) {
//      System.out.println(Arrays.toString(greatestToTheRight(new int[]{11, 13, 21, 3})));
      int len = arr.length;
      Stack<Integer> stack = new Stack<>();
      int[] NGE = new int[len];

      for (int i = len - 1; i >= 0; i--) {
         while (!stack.isEmpty() && stack.peek() <= arr[i]) {
            stack.pop();
         }
         if (stack.isEmpty()) {
            NGE[i] = -1;
         } else {
            NGE[i] = stack.peek();
         }
         stack.push(arr[i]);
      }
      return NGE;
   }

}
