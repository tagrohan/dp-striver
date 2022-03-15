package hashmap_heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyHeap {
   Queue<Integer> firstHalf, secondHalf;

   public static void main(String[] args) {
      MyHeap heap = new MyHeap();
      heap.add(1);
      heap.add(3);
      heap.add(4);
      System.out.println(heap.median());
      heap.add(2);
      System.out.println(heap.median());


   }
// todo have to implement this
//   private void pop() {
//
//   }

   private int median() {
      if (firstHalf.isEmpty() && secondHalf.isEmpty()) return -1;
      if (secondHalf.isEmpty()) return firstHalf.peek();
      if (firstHalf.isEmpty()) return secondHalf.peek();

      return firstHalf.size() >= secondHalf.size() ? firstHalf.peek() : secondHalf.peek();

   }

   private void add(int data) {
      if (!secondHalf.isEmpty() && data >= secondHalf.peek()) {
         secondHalf.add(data);
      } else {
         firstHalf.add(data);
      }
      if (firstHalf.size() - secondHalf.size() > 1) {
         secondHalf.add(firstHalf.poll());
      } else if (secondHalf.size() - firstHalf.size() > 1) {
         firstHalf.add(secondHalf.poll());
      }
   }


   public MyHeap() {
      firstHalf = new PriorityQueue<>(Collections.reverseOrder());
      secondHalf = new PriorityQueue<>();
   }
}
