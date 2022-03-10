package hashmap_heap;

import java.util.*;

public class HeapMain {
   public static void main(String[] args) {
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
