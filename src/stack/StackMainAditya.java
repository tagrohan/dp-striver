package stack;

import java.util.Arrays;
import java.util.Stack;

public class StackMainAditya {
   public static void main(String[] args) {
      System.out.println(Arrays.toString(greatestToTheLeft(new int[]{14, 13, 21, 3})));
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
