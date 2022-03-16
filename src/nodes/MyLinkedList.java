package nodes;

public class MyLinkedList {

   public static void main(String[] args) {
      LinkedNode root = new LinkedNode(1, null);
      LinkedNode r2 = new LinkedNode(2, null);
      LinkedNode r3 = new LinkedNode(3, null);
      LinkedNode r4 = new LinkedNode(4, null);
      root.next = r2;
      r2.next = r3;
      r3.next = r4;
      printList(addLast(root, 5));

   }

   private static LinkedNode addLast(LinkedNode root, int data) {
      LinkedNode temp = root;
      while (temp.next != null) {
         temp = temp.next;
      }
      temp.next = new LinkedNode(data, null);

      return root;
   }

   private static void printList(LinkedNode root) {
      while (root != null) {
         System.out.print(root.data + " ");
         root = root.next;
      }
   }
}
