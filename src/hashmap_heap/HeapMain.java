package hashmap_heap;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapMain {
   public static void main(String[] args) {
      System.out.println(kthSmallest(List.of(5, 4, 3, 2, 7, 6, 8, 5), 3));
   }

   private static int kthSmallest(List<Integer> list, int k) {
      Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
      list.forEach(ele -> {
         if (queue.size() < k) queue.add(ele);
         else queue.poll();
      });
      return queue.isEmpty() ? -1 : queue.peek();
   }

}
