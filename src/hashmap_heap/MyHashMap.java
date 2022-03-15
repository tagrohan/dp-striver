package hashmap_heap;

import java.util.*;

public class MyHashMap {
   public static void main(String[] args) {
      System.out.println(longestConsecutiveV2(new Integer[]{10, 5, 9, 1, 11, 8, 6, 15, 3, 12, 2}));
   }

   // it will work for -ve and +ve both
   // if you want to see how to save them in list check below
   private static int longestConsecutiveV2(Integer[] arr) {
//      System.out.println(longestConsecutiveV2(new Integer[]{10, 5, 9, 1, 11, 8, 6, 15, 3, 12, 2}));
      Set<Integer> set = new HashSet<>(Arrays.asList(arr));
      int counter = 0;
      int max = 0;
      for (Integer i : arr) {
         if (set.contains(i)) {
            counter = 1;
            while (set.contains(i + counter)) {
               counter++;
            }
            max = Integer.max(counter, max);
         }
      }
      return max;
   }

   // work only for +ve
   private static int longestConsecutiveV1(int[] arr) {
//      System.out.println(longestConsecutiveV1(new int[]{10, 5, 9, 1, 11, 8, 6, 15, 3, 12, 2})); -> 5 for this -> 8 9 10 11 12
      int start = Integer.MAX_VALUE, range = Integer.MIN_VALUE;
      for (int val : arr) {
         start = Integer.min(start, val);
         range = Integer.max(range, val);
      }
      int[] res = new int[range + 1];
      for (int val : arr) res[val] = 1;
      int maxLength = 0, currentLen = 0;
      // in case if you want that list
      List<Integer> resList = new ArrayList<>();
      List<Integer> counterList = new ArrayList<>();
      for (int i = start; i < res.length; i++) {
         if (res[i] == 1) {
            currentLen += 1;
            counterList.add(i);
         } else {
//            maxLength = Integer.max(maxLength, currentLen);
            if (maxLength < currentLen) {
               maxLength = currentLen;
               resList.clear();
               resList.addAll(counterList);
            }
            currentLen = 0;
            counterList.clear();
         }
      }
      resList.forEach(ele -> System.out.print(ele + ","));
      System.out.println();
      return maxLength;
   }
//  print only common in both
   private static void printCommonElementV1(int[] arr1, int[] arr2) {
//      printCommonElementV1(new int[]{1, 1, 2, 2, 3, 3, 4}, new int[]{1, 1, 1, 2, 2, 4, 5});
      Set<Integer> set = new HashSet<>();
      for (int val : arr1) set.add(val);
      for (int val : arr2) {
         if (set.contains(val)) {
            System.out.print(val);
            set.remove(val);
         }
      }
   }
//  print common irrespective no. of times in bot
   private static void printCommonElementV2(int[] arr1, int[] arr2) {
//      printCommonElementV2(new int[]{1, 1, 2, 2, 3, 3, 4}, new int[]{1, 1, 1, 2, 2, 4, 5});
      Map<Integer, Integer> map = new HashMap<>();
      for (int val : arr1) map.put(val, map.containsKey(val) ? map.get(val) + 1 : 1);
      for (int val : arr2)
         if (map.containsKey(val) && map.get(val) > 0) {
            System.out.print(val);
            map.put(val, map.get(val) - 1);
         }
   }

   private static char highestFrequency(String str) {
//      System.out.println(highestFrequency("abracadabra"));
      Map<Character, Integer> map = new HashMap<>();

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
      }
      char maxChar = '-';
      int maxEle = Integer.MIN_VALUE;
      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
         if (entry.getValue() > maxEle) {
            maxEle = entry.getValue();
            maxChar = entry.getKey();
         }
      }
      return maxChar;
   }
}

