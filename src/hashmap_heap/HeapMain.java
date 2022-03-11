package hashmap_heap;

import java.util.*;

public class HeapMain {

   public static void main(String[] args) {
      kFrequentNumber(new int[]{1, 1, 1, 3, 2, 2, 24}, 2);
   }

   // top k frequent element ex for k = 2 in [1,1,1,3,2,2,4] is 1,2
   private static void kFrequentNumber(int[] arr, int k) {
//      kFrequentNumber(new int[]{1, 1, 1, 3, 2, 2, 24}, 2);
      Map<Integer, Integer> map = gettingFrequency(arr);
      Queue<Pair> queue = new PriorityQueue<>();
      map.forEach((key, frequency) -> {
         queue.add(new Pair(key, frequency));
         if (queue.size() > k) queue.poll();
      });
      queue.forEach(el -> System.out.println(el.index + " "));
   }

   private static Map<Integer, Integer> gettingFrequency(int[] arr) {
      Map<Integer, Integer> map = new HashMap<>();
//      for (int ele : arr) {
//         if (map.containsKey(ele)) {
//            map.put(ele, map.get(ele) + 1);
//         } else map.put(ele, 1);
//      }
      // both working fine
      for (int ele : arr) map.put(ele, map.containsKey(ele) ? map.get(ele) + 1 : 1);
      return map;
   }


   // todo imp
   static class Pair implements Comparable<Pair> {

      int index;
      int data;

      public Pair(int index, int date) {
         this.index = index;
         this.data = date;
      }

      @Override
      public int compareTo(Pair o) {
         return this.data - o.data;
      }
   }

   private static void kClosestNumber(int[] arr, int k, int num) {
//      kClosestNumber(new int[]{5, 6, 10, 20, 30, 7, 8, 9, 10, 11, 12}, 3, 7);
      int[] closest = new int[k];
      Queue<Pair> queue = new PriorityQueue<>(Collections.reverseOrder());
      for (int i = 0; i < arr.length; i++) {
         queue.add(new Pair(i, Math.abs(num - arr[i])));
         if (queue.size() > k) queue.remove();
      }
      queue.forEach(obj -> System.out.print(arr[obj.index] + " "));
   }

   private static int[] sortKSorted(int[] arr, int k) {
//      int[] arr = new int[]{6, 5, 3, 2, 8, 10, 9};
//      System.out.println(Arrays.toString(sortKSorted(arr, 3)));
      Queue<Integer> queue = new PriorityQueue<>();
      int[] sortedArray = new int[arr.length];
      int i = 0;
      for (int var : arr) {
         queue.add(var);
         if (queue.size() > k) {
            sortedArray[i] = queue.remove();
            i += 1;
         }
      }
      if (!queue.isEmpty()) {
         while (!queue.isEmpty()) {
            sortedArray[i] = queue.remove();
            i += 1;
         }
      }
      return sortedArray;
   }

   private static int kthLargest(List<Integer> list, int k) {
//      System.out.println(kthLargest(List.of(5, 4, 3, 2, 7, 6, 8, 5), 3));
      Queue<Integer> queue = new PriorityQueue<>();
      list.forEach(ele -> {
         queue.add(ele);
         if (queue.size() > k) queue.remove();
      });
      return queue.isEmpty() ? -1 : queue.peek();

   }


   private static int kthSmallest(List<Integer> list, int k) {
//      System.out.println(kthSmallest(List.of(5, 4, 3, 2, 7, 6, 8, 5), 3));
      Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
      list.forEach(ele -> {
         queue.add(ele);
         if (queue.size() > k) queue.remove();
      });
      return queue.isEmpty() ? -1 : queue.peek();
   }

}
