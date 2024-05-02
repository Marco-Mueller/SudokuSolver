package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuUtilities {

    public static String arrayToString2D(int[][] array) {
        String sudokuString = "";
        for (int row = 0; row < array.length; row++) {
            String rowString = "";
            for (int col = 0; col < array[1].length; col++) {
                rowString += array[row][col] + " ";
            }
            rowString += "\n";
            sudokuString += rowString;
        }

        return sudokuString;
        //System.out.println(sudokuString);
    }

    public static String arrayToString1D(int[] array) {
        String sudokuString = "";

        for (int i = 0; i < array.length; i++) {
            sudokuString += array[i] + " ";
        }
        sudokuString += "\n";


        return sudokuString;
        //System.out.println(sudokuString);
    }

    /**
     * this method unifies the elements present in all three input lists
     *
     * @param list1
     * @param list2
     * @param list3
     * @param <T>
     * @return
     */
    public static <T> List<T> unifyLists3(List<T> list1, List<T> list2, List<T> list3) {
        HashSet<T> set1 = new HashSet<>(list1);
        HashSet<T> set2 = new HashSet<>(list2);
        HashSet<T> set3 = new HashSet<>(list3);

        set1.retainAll(set2);
        set1.retainAll(set3);

        return new ArrayList<>(set1);
    }

    public static <T> List<T> unifyLists2(List<T> list1, List<T> list2) {
        HashSet<T> set1 = new HashSet<>(list1);
        HashSet<T> set2 = new HashSet<>(list2);


        set1.retainAll(set2);

        return new ArrayList<>(set1);
    }

    /**
     * returns unique numbers at the nth spot, numbers which are possible in n and no other spot
     *
     * @param n
     * @param arrays
     * @return
     */
    public static List<Integer> findUniqueNumbers(int n, List<int[]> arrays) {
        List<Integer> uniqueNumbers = new ArrayList<>();
        int[] targetArray = arrays.get(n);

        // Convert other arrays to a set of integers
        Set<Integer> otherNumbers = new HashSet<>();
        for (int i = 0; i < arrays.size(); i++) {
            if (i != n) {
                for (int num : arrays.get(i)) {
                    otherNumbers.add(num);
                }
            }
        }

        // Find unique numbers in the target array
        for (int num : targetArray) {
            if (!otherNumbers.contains(num)) {
                uniqueNumbers.add(num);
            }
        }

        return uniqueNumbers;
    }


}
