package stack;

import java.util.Arrays;
import java.util.Stack;

public class StackMainAditya {
   public static void main(String[] args) {

      // todo: some questions left in stack, so take care that as well
   }

   private static int rainWaterTrapping(int[] arr) {
//      System.out.println(rainWaterTrapping(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

      int[] maxToLeft = maxToLeft(arr);
      int[] maxToRight = maxToRight(arr);

      int totalWater = 0;
      for (int i = 0; i < arr.length; i++) {
         totalWater = totalWater + Integer.min(maxToRight[i], maxToLeft[i]) - arr[i];
      }
      return totalWater;
   }

   private static int[] maxToLeft(int[] arr) {
      int[] toLeft = new int[arr.length];
      toLeft[0] = arr[0];
      for (int i = 1; i < arr.length; i++) {
         toLeft[i] = Integer.max(toLeft[i - 1], arr[i]);
      }
      return toLeft;
   }

   private static int[] maxToRight(int[] arr) {
      int indexes = arr.length - 1;
      int[] toRight = new int[indexes + 1];
      toRight[indexes] = arr[indexes];
      for (int i = indexes - 1; i >= 0; i--) {
         toRight[i] = Integer.max(toRight[i + 1], arr[i]);
      }
      return toRight;
   }


   private static int areaOfRectangleInBinaryMetric(int[][] arr) {
//      System.out.println(areaOfRectangleInBinaryMetric(new int[][]{
//           {0, 1, 1, 0},
//           {1, 1, 1, 1},
//           {1, 1, 1, 1},
//           {1, 1, 0, 0}
//      }));
      int max = -1;
      int[] toPass = new int[arr.length];
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr.length; j++) {
            toPass[i] += arr[i][j];
         }
         max = Integer.max(max, maxAreaOfHistogram(toPass));
      }
      return max;
   }

   private static int maxAreaOfHistogram(int[] arr) {
      int[] smallestToRight = smallestToRightForHistogram(arr);
      int[] smallestToLeft = smallestToLeftForHistogram(arr);
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < arr.length; i++) {
         max = Integer.max((smallestToRight[i] + smallestToLeft[i] - 1) * arr[i], max);
      }
      return max;
   }

   private static int[] smallestToRightForHistogram(int[] arr) {
      int len = arr.length;
      int[] STR = new int[len];
      Stack<Integer> stack = new Stack<>();
      for (int i = len - 1; i >= 0; i--) {
         while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();

         STR[i] = stack.isEmpty() ? len - i : stack.peek() - i;
         stack.push(i);
      }
      return STR;
   }

   private static int[] smallestToLeftForHistogram(int[] arr) {
      int len = arr.length;
      int[] STL = new int[len];
      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < len; i++) {
         while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();

         STL[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
         stack.push(i);
      }
      return STL;
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
