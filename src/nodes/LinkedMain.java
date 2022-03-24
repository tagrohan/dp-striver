package nodes;

public class LinkedMain {

   public static void main(String[] args) {
      LinkedNode linked = new LinkedNode();
      linked.addAlL(1, 2, 2);
      LinkedNode linked2 = new LinkedNode();
      linked2.addAlL(1, 2, 2);
      linked.sumOfTwoList(linked2).print();

   }


}
