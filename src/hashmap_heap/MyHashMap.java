package hashmap_heap;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
   public static void main(String[] args) {
      printCommonElement(new int[]{1, 1, 2, 2, 3, 3, 4}, new int[]{1, 1, 1, 2, 2, 4, 5});
   }

   private static void printCommonElement(int[] arr1, int[] arr2) {
//      printCommonElement(new int[]{1, 1, 2, 2, 3, 3, 4}, new int[]{1, 1, 1, 2, 2, 4, 5});
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

