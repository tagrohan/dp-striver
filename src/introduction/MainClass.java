package introduction;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("working..");

        System.out.println(Arrays.toString(findAllIndex(new int[]{1, 4, 1, 2, 3, 4, 4, 5, 5, 6}, 0, 0, 8)));
    }

    private static int[] findAllIndex(int[] arr, int index, int noOfElementFound, int element) {
        if (arr.length == index) return new int[noOfElementFound];
        if (arr[index] == element) noOfElementFound += 1;

        int[] indexes = findAllIndex(arr, index + 1, noOfElementFound, element);
        if (arr[index] == element) {
            indexes[noOfElementFound - 1] = index;
        }
        return indexes;
    }
}
