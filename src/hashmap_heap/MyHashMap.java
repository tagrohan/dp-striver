package hashmap_heap;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
   public static void main(String[] args) {
      System.out.println(highestFrequency("abracadabra"));
   }

   private static char highestFrequency(String str) {
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

