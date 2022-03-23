package nodes;

public class LinkedMain {

   public static void main(String[] args) {
      LinkedNode linked = new LinkedNode();
      LinkedNode linked2 = new LinkedNode();
      linked.addAlL(1, 3, 5, 7, 8, 8, 10);
      linked2.addAlL(2, 4, 6, 8, 9, 11);
      linked.sortTwoSortedList(linked2).print();


   }


}
