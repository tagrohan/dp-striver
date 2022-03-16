package nodes;

public class MyLinkedList {

   public static void main(String[] args) {

   }

   private static LinkedNode addLast(LinkedNode root, int data) {
      LinkedNode temp = root;
      while (temp.next != null) {
         temp = temp.next;
      }
      temp.next = new LinkedNode(data, null);

      return temp;
   }
}
