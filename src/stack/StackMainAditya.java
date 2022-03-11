package stack;

import java.util.Arrays;
import java.util.Stack;

public class StackMainAditya {
   public static void main(String[] args) {

      System.out.println(celebrityProblem(new int[][]{ // pep coding
           //0  1  2  3
           {0, 1, 1, 1},// 0
           {1, 0, 1, 0},// 1
           {0, 0, 0, 0},// 2
           {1, 1, 1, 0},// 3
      }));
   }

   private static boolean celebrityProblem(int[][] arr) {
//      System.out.println(celebrityProblem(new int[][]{ // pep coding
//           //0  1  2  3
//           {0, 1, 1, 1},// 0
//           {1, 0, 1, 0},// 1
//           {0, 0, 0, 0},// 2
//           {1, 1, 1, 0},// 3
//      }));
      Stack<Integer> stack = new Stack<>();
      for (int i = arr.length - 1; i >= 0; i--) stack.add(i);
      while (stack.size() > 1) {
         int firstIndex = stack.pop();
         int secondIndex = stack.pop();
         if (arr[firstIndex][secondIndex] == 1) stack.push(secondIndex);
         else stack.push(firstIndex);
      }
      for (int i = 0; i < arr.length; i++) {
         if (i == stack.peek()) {
            if (arr[i][stack.peek()] == 1) return false;
            else continue;
         }
         if (arr[i][stack.peek()] != 1 || arr[stack.peek()][i] != 0) return false;
      }
      return true;
   }

   private static void mergeOverlappingIntervals(int[][] intervals) {
//      mergeOverlappingIntervals(new int[][]{
//           {1, 8},
//           {5, 12},
//           {14, 19},
//           {22, 28},
//           {25, 27},
//           {27, 30},
//      });
      Stack<Pair> stack = new Stack<>();
      stack.push(new Pair(intervals[0][0], intervals[0][1]));
      for (int i = 1; i < intervals.length; i++) {
         Pair stackPair = stack.peek();
         Pair newInterval = new Pair(intervals[i][0], intervals[i][1]);
         if (stackPair.second > newInterval.first) {
            if (stackPair.second < newInterval.second) stack.peek().second = newInterval.second;
         } else stack.push(newInterval);
      }
      stack.forEach(System.out::println);
   }

   private static class Pair {
      int first, second;

      public Pair(int first, int second) {
         this.first = first;
         this.second = second;
      }

      @Override
      public String toString() {
         return "Pair{" +
              "first=" + first +
              ", second=" + second +
              '}';
      }
   }

   // todo will do in morning
   private static int[] slidingWindowMaximum(int[] arr, int k) {
//      System.out.println(Arrays.toString(slidingWindowMaximum(new int[]{2, 9, 3, 8, 1, 7, 12, 6, 14, 4, 32, 0, 7, 19, 8, 12, 6}, 4)));
      int[] NGR = nextGreatestIndex(arr); // 1, 6, 3, 6, 5, 6, 8, 8, 10, 10, 10, 12, 13, 13, 15, 15, 6
      int j = 0, len = arr.length;
      int[] out = new int[len];
      for (int i = 0; i < len; i++) {
         while (NGR[j] < i + k) {
            j = NGR[j];
         }
         out[i] = arr[j];
      }
      return out;
   }

   private static int[] nextGreatestIndex(int[] arr) {
      Stack<Integer> stack = new Stack<>();
      int[] NGE = new int[arr.length];
      for (int i = arr.length - 1; i >= 0; i--) {
         while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
         NGE[i] = stack.isEmpty() ? i : stack.peek();
         stack.push(i);
      }
      return NGE;
   }

   // ex - (a + b) + ((c + d)) -> () is extra in second half
   private static boolean checkExtraBracket(String str) {
//      System.out.println(checkExtraBracket("(a + b) + ((c + d))"));
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (Character.isSpaceChar(ch)) continue; // not checking for spaces
         if (ch == ')') {
            if (stack.peek() == '(') return true;
            while (stack.peek() != '(') stack.pop(); // no need to take care of empty check here
            stack.pop();
         } else stack.push(ch);
      }
      return false;
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
