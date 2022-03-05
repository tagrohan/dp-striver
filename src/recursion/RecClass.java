package recursion;

import java.util.*;

public class RecClass {
   public static void main(String[] args) {
      addingSpacesInBetweenV2("abc");
   }

   private static void addingSpacesInBetweenV2(String str) {
//      addingSpacesInBetweenV2("abc");
      if (str == null || str.isBlank()) return;
      List<String> combinations = new ArrayList<>();
      addingSpacesInBetweenV2Helper(str.substring(1), "", combinations);
      combinations.forEach(ele -> System.out.println(str.charAt(0) + ele));
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
