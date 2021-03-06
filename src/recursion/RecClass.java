package recursion;

import java.util.*;

public class RecClass {
   public static void main(String[] args) {

      System.out.println(safeSpot(40, 7));
   }


   private static int safeSpot(int num, int k) {
//      System.out.println(safeSpot(40, 7));
      List<Integer> list = new LinkedList<>();
      for (int i = 1; i <= num; i++) list.add(i);

      k--;
      int index = 0;
      int size = list.size();
      while (list.size() > 1) {
         int indexOfRemovingElement = (index + k) % size--;
         list.remove(indexOfRemovingElement);
         index = indexOfRemovingElement;
      }

      return list.get(0);
   }

   // time complexity problem still good if asked in interview
   // url https://practice.geeksforgeeks.org/problems/game-of-death-in-a-circle1840/1
   private static int josephProblem(int num, int k) {
//      System.out.println(josephProblem(4, 2));
      List<Integer> list = new LinkedList<>();
      for (int i = 1; i <= num; i++) {
         list.add(i);
      }
      return josephProblemHelper(0, k - 1, list);
   }

   private static int josephProblemHelper(int index, int k, List<Integer> list) {
      if (list.size() == 1) return list.get(0);
      int indexOfRemovingElement = (index + k) % list.size();
      list.remove(indexOfRemovingElement);

      return josephProblemHelper(indexOfRemovingElement, k, list);
   }

   private static int[] separateZeroOneTwo(int[] arr) {
//      System.out.println(Arrays.toString(separateZeroOneTwo(new int[]{2, 1, 0, 0, 0, 1, 2, 1, 2, 1})));
      int low = 0, mid = 0, high = arr.length - 1;

      while (mid < high) {
         if (arr[mid] == 0) {
            swapping(arr, mid, low++);
         }
         if (arr[mid] == 1) {
            mid++;
         }
         if (arr[mid] == 2) {
            swapping(arr, mid, high--);
         }
      }

      return arr;
   }

   private static List<String> generateBinary(int num) {
//      List<String> list = generateBinary(3);
//      list.forEach(System.out::println);
      List<String> list = new ArrayList<>();
      generateBinaryHelper(0, 0, list, "", num);
      return list;
   }

   // condition is 1's should be >= 0'2, ex for 3 - 111, 110, 101
   private static void generateBinaryHelper(int ones, int zeros, List<String> list, String psf, int num) {
      if (num < 0) return;
      if (num == 0) {
         list.add(psf);
         return;
      }

      generateBinaryHelper(ones + 1, zeros, list, psf + 1, num - 1);
      if (ones > zeros) generateBinaryHelper(ones, zeros + 1, list, psf + 0, num - 1);
   }

   private static List<String> generateParenthesis(int num) {
//      List<String> list = generateParenthesis(3);
//     list.forEach(System.out::println);
//      generateParenthesis(3).forEach(System.out::println);
      List<String> list = new ArrayList<>();
      generateParenthesisHelper(num, num, "", list);
      return list;
   }

   private static void generateParenthesisHelper(int left, int right, String psf, List<String> list) {
      if (left < 0 || right < 0) return;
      if (left == 0 && right == 0) {
         list.add(psf);
         return;
      }
      if (left < right) {
         generateParenthesisHelper(left - 1, right, psf + "(", list);
         generateParenthesisHelper(left, right - 1, psf + ")", list);
      } else {
         generateParenthesisHelper(left - 1, right, psf + "(", list);
      }
   }

   private static void permutationWithCaseChangeIncludingNumbers(String str, String ssf) {
//      String str = "a1B2";
//      permutationWithCaseChangeIncludingNumbers(str, "");
      if (str.length() == 0) {
         System.out.println(ssf);
         return;
      }

      if (Character.isAlphabetic(str.charAt(0))) {
         permutationWithCaseChangeIncludingNumbers(str.substring(1), ssf + String.valueOf(str.charAt(0)).toUpperCase(Locale.ROOT));
         permutationWithCaseChangeIncludingNumbers(str.substring(1), ssf + String.valueOf(str.charAt(0)).toLowerCase(Locale.ROOT));
      } else {
         permutationWithCaseChangeIncludingNumbers(str.substring(1), ssf + str.charAt(0));
      }
   }

   // ab -> aB,Ab, ab,AB
   private static void permutationWithCaseChange(String str, String ssf) {
//      permutationWithCaseChange(abc, "");
      if (str.length() == 0) {
         System.out.println(ssf);
         return;
      }

      permutationWithCaseChange(str.substring(1), ssf + String.valueOf(str.charAt(0)).toUpperCase(Locale.ROOT));
      permutationWithCaseChange(str.substring(1), ssf + str.charAt(0));
   }

   private static void addingSpacesInBetweenV3(String str, String ssf) {
//      String str = "abc";
//      addingSpacesInBetweenV3(str.substring(1), String.valueOf(str.charAt(0)));
      if (str.length() == 0) {
         System.out.println(ssf);
         return;
      }

      addingSpacesInBetweenV3(str.substring(1), ssf + "_" + str.charAt(0));
      addingSpacesInBetweenV3(str.substring(1), ssf + str.charAt(0));
   }

   private static void addingSpacesInBetweenV2(String str) {
//      addingSpacesInBetweenV2("abc");
      if (str == null || str.isBlank()) return;
      List<String> combinations = new ArrayList<>();
      addingSpacesInBetweenV2Helper(str.substring(1), String.valueOf(str.charAt(0)), combinations);
      combinations.forEach(System.out::println);
   }

   private static void addingSpacesInBetweenV2Helper(String str, String ssf, List<String> combinations) {
      if (str.length() <= 0) {
         combinations.add(ssf);
         return;
      }

      addingSpacesInBetweenV2Helper(str.substring(1), ssf + "_" + str.charAt(0), combinations);
      addingSpacesInBetweenV2Helper(str.substring(1), ssf + str.charAt(0), combinations);
   }

   //abc = a_b_c,ab_c,a_bc,abc
   private static void addingSpacesInBetween(String str) {
      if (str.isBlank()) return;
      // required a helper as we have a problem with -A in beginning
      addingSpacesInBetweenHelper(str.substring(1), "", str.charAt(0));
   }

   private static void addingSpacesInBetweenHelper(String str, String ssf, char first) {

      if (str.length() <= 0) {
         System.out.println(first + ssf);
         return;
      }

      addingSpacesInBetweenHelper(str.substring(1), ssf + "_" + str.charAt(0), first);
      addingSpacesInBetweenHelper(str.substring(1), ssf + str.charAt(0), first);
   }


   private static void uniqueSubset(String str, String ssf, Set<String> set) {
//      Set<String> set = new LinkedHashSet<>();
//      uniqueSubset("aaab", "", set);
//      set.forEach(str ->{
//         System.out.print(str + ", ");
//      });
      if (str.length() <= 0) {
         set.add(ssf);
         return;
      }

      uniqueSubset(str.substring(1), ssf + str.charAt(0), set);
      uniqueSubset(str.substring(1), ssf, set);
   }

   private static void subset(String str, String ssf) {
//      subset("cba", "");
      if (str.length() <= 0) {
         System.out.print(ssf + ", ");
         return;
      }
      subset(str.substring(1), ssf + str.charAt(0));
      subset(str.substring(1), ssf);
   }

   private static void towerOfHanoi(int s, int d, int h, int n) {
//      towerOfHanoi(1, 3, 2, 3);
      if (n == 1) {
         System.out.println(s + " " + d);
         return;
      }
      towerOfHanoi(s, h, d, n - 1);
      System.out.println(s + " " + d);
      towerOfHanoi(h, d, s, n - 1);
   }


   // todo not working properly
   private static int findKthGrammar(int n, int k) {
//      System.out.println(findKthGrammar(4, 2));
      if (n == 1) {
         return 0;
      }
      int mid = (int) Math.pow(2, n - 1) / 2;
      if (k < mid) {
         return findKthGrammar(n - 1, k);
      } else {
         return findKthGrammar(n - 1, k - mid);
      }
   }


   private static void fluidFill(int[][] arr, int startRow, int startCol, int endRow, int endCol, int[][] dp, String psf) {
//      fluidFill(new int[][]{
//           {0, 1, 0, 0},
//           {0, 0, 0, 0},
//           {1, 0, 1, 0},
//           {1, 0, 0, 0},
//      }, 0, 0, 3, 3, new int[4][4], "");
      if (startRow < 0 || startCol < 0 || startRow > endRow || startCol > endCol || arr[startRow][startCol] == 1 || dp[startRow][startCol] == 1)
         return;
      if (startRow == endRow && startCol == endCol) {
         System.out.println(psf);
         return;
      }

      dp[startRow][startCol] = 1;
      fluidFill(arr, startRow - 1, startCol, endRow, endCol, dp, psf + "U ");
      fluidFill(arr, startRow, startCol + 1, endRow, endCol, dp, psf + "R ");
      fluidFill(arr, startRow + 1, startCol, endRow, endCol, dp, psf + "D ");
      fluidFill(arr, startRow, startCol - 1, endRow, endCol, dp, psf + "L ");
      dp[startRow][startCol] = 0;
   }

   private static void reverseAStack(Stack<Integer> stack) {
//        int[] arr = new int[]{3, 4, 2, 1, 6, 1, 0};
//        Stack<Integer> stack = new Stack<>();
//        for (int i : arr) {
//            stack.push(i);
//        }
//        reverseAStack(stack);
//        stack.forEach(System.out::print);
      if (stack.isEmpty()) return;

      int var = stack.pop();
      reverseAStack(stack);
      insertAtLastPlaceTOReverse(stack, var);
   }

   private static void insertAtLastPlaceTOReverse(Stack<Integer> stack, int var) {
      if (stack.isEmpty()) {
         stack.push(var);
         return;
      }
      int varHolder = stack.pop();
      insertAtLastPlaceTOReverse(stack, var);
      stack.push(varHolder);
   }

   private static void removeMiddle(Stack<Integer> stack, int middle) {
//        removeMiddle(stack, (stack.size() / 2 + 1));
      if (stack.size() <= middle) {

         return;
      }
      int var = stack.pop();
      removeMiddle(stack, middle);
      stack.push(var);
   }


   private static void sortAnStack(Stack<Integer> stack) {
//        int[] arr = new int[]{3, 4, 2, 1, 6, 1, 0};
//        Stack<Integer> stack = new Stack<>();
//        for (int i : arr) {
//            stack.push(i);
//        }
//        sortAnStack(stack);
//        stack.forEach(System.out::print);
      if (stack.size() == 0) return;
      int peek = stack.pop();
      sortAnStack(stack);
      placeOnTheRightPosition(stack, peek);
   }

   private static void placeOnTheRightPosition(Stack<Integer> stack, int peek) {
      if (stack.size() == 0 || stack.peek() <= peek) {
         stack.push(peek);
         return;
      }
      int removingElement = stack.pop();
      placeOnTheRightPosition(stack, peek);
      stack.push(removingElement);
   }


   private static void sortAnArray(int[] arr, int noOfElements) {
//        int[] arr = new int[]{3, 4, 2, 1, 6, 1, 0};
//        sortAnArray(arr, 6);
//        System.out.println(Arrays.toString(arr));
      if (noOfElements == -1) return;
      int maxIndex = getMaxIndex(arr, noOfElements);
      swapping(arr, maxIndex, noOfElements);
      sortAnArray(arr, noOfElements - 1);

   }

   private static void swapping(int[] arr, int maxIndex, int noOfElements) {
      int temp = arr[maxIndex];
      arr[maxIndex] = arr[noOfElements];
      arr[noOfElements] = temp;
   }

   private static int getMaxIndex(int[] arr, int range) {
      int max = arr[0], returnIndex = 0;
      for (int i = 0; i <= range; i++) {
         if (max < arr[i]) {
            max = arr[i];
            returnIndex = i;
         }
      }
      return returnIndex;
   }


   private static void reverseAnArray(int[] arr, int index) {

//        int[] arr2 = new int[]{4, 3, 2, 1};
//        reverseAnArray(arr2, 0);
//        System.out.println(Arrays.toString(arr2));
      if (arr.length == index) {
         return;
      }
      int val = arr[index];
      reverseAnArray(arr, index + 1);
      arr[arr.length - index - 1] = val; // -1 to play around with index
   }

   public static void sort0and1(int n, int[] arr) {

//        int[] arr2 = new int[]{0, 1, 0,1};
//        sort0and1(1, arr2);
//        System.out.println(Arrays.toString(arr2));
      // using swapping technique
      int one = 0;
      int zero = arr.length - 1;

      while (one < zero) {
         if (arr[one] == 0) {
            one++;
         }
         if (arr[zero] == 1) {
            zero--;
         }
         if (arr[one] == 1 && arr[zero] == 0) {
            int temp = arr[one];
            arr[one++] = arr[zero];
            arr[zero--] = temp;
         }
      }
   }


   private static int powerV(int num1, int num2) {
//        System.out.println(powerV(2, 4));
      if (num2 == 0) {
         return 1;
      }
      return num1 * powerV(num1, num2 - 1);
   }


   private static void printSubsequence(String str, String psf) {
//        printSubsequence("abc", "");
//        if (str.isBlank()) { // not available in java - 8
//            System.out.print(psf + ", ");
//            return;
//        }
      if (str.length() == 0) {
         System.out.print(psf + ", ");
         return;
      }

      char ch = str.charAt(0);
      printSubsequence(str.substring(1), psf);
      printSubsequence(str.substring(1), ch + psf);
   }

   private static void printSubsequence(String str, int index, List<Character> list) {
//        printSubsequence("abc", 0, new ArrayList<>());
      if (str.length() == index) {
         System.out.println(Arrays.toString(list.toArray()));
         return;
      }
      list.add(str.charAt(index));
      printSubsequence(str, index + 1, list);
      list.remove(list.size() - 1);
      printSubsequence(str, index + 1, list);
   }

   static int min = Integer.MAX_VALUE;

   private static void minEnergyForFrog(int[] arr, int index, int energy) {
//        minEnergyForFrog(new int[]{10, 20, 30, 10}, 0, 0);
//        System.out.println(min);
      if (index >= arr.length) return;
      if (index == arr.length - 1) {
         System.out.println(energy + "-------->");
         min = Integer.min(min, energy);
         return;
      }


      minEnergyForFrog(arr, index + 1, Math.abs(arr[index] - energy));
      minEnergyForFrog(arr, index + 2, Math.abs(arr[index] - energy));
   }


   private static void subsetSum(int[] arr, int idx, int target, int current, String psf) {
//        subsetSum(new int[]{1, 2, 3, 4, 5, 6}, 0, 7, 0, "");
      if (idx >= arr.length) return;
      if (target == current) {
         System.out.println(psf);
         return;
      }

      subsetSum(arr, idx + 1, target, current + arr[idx], arr[idx] + " " + psf);
      subsetSum(arr, idx + 1, target, current, psf);
   }


   private static List<String> getMazePath(int xStart, int yStart, int xEnd, int yEnd) {
//        System.out.println(Arrays.toString(getMazePath(1, 1, 3, 3).toArray()));
      if (xStart > xEnd || yStart > yEnd) return List.of();
      if (xStart == xEnd && yStart == yEnd) return List.of("");


      List<String> horizontal = getMazePath(xStart, yStart + 1, xEnd, yEnd);
      List<String> vertical = getMazePath(xStart + 1, yStart, xEnd, yEnd);
      List<String> tempPath = new ArrayList<>();

      for (String h : horizontal) {
         tempPath.add("H " + h);
      }
      for (String v : vertical) {
         tempPath.add("V " + v);
      }
      return tempPath;

   }

   // did it by myself, good medium level question
   private static List<String> getSubsequence(String str) {
//        System.out.println(Arrays.toString(getSubsequence("abc").toArray()));
      if (str.length() == 0) {
         return List.of("");
      }

      char frontChar = str.charAt(0);
      List<String> tempSub = getSubsequence(str.substring(1));
      List<String> toReturn = new ArrayList<>();

      for (String st : tempSub) {
         toReturn.add(st);
         toReturn.add(frontChar + st);
      }
      return toReturn;
   }
}
