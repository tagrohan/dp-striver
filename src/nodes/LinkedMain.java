package nodes;

public class LinkedMain {

   public static void main(String[] args) {
      LinkedNode linked = new LinkedNode();
      linked.addAlL(10, 9, 8, 7, 6, 5, 4, 3, 3, 4, 4, 4, 2, 1);
      linked.mergeSort();
      linked.print();


   }


}
