package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecClass {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getSubsequence("abc").toArray()));
    }

    private static List<String> getSubsequence(String str) {
        if (str.length() == 0) {
            return List.of("");
        }

        char frontChar = str.charAt(0);
        List<String> tempSub = getSubsequence(str.substring(1));
        List<String> toReturn = new ArrayList<>();

        for (String st : tempSub) {
            toReturn.add(st);
            toReturn.add(frontChar + st);
        }
        return toReturn;
    }
}
