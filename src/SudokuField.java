package src;

import java.util.Arrays;

public class SudokuField {
    public static final int x = 0;
    private int[][] sudoku;


    public SudokuField(int[][] startConfiguration) {
        sudoku = startConfiguration;
    }

    public SudokuField() {
    }


    public static boolean containsAllNumbers(int[] array) {
        return Arrays.stream(array).filter(num -> num >= 1 && num <= 9).distinct().count() == 9;
    }

    public int[][] extractSquare(int n) {
        int startRow, startCol;
        if (n <= 3) {
            startRow = 0;
            startCol = (n - 1) * 3;
        } else if (n <= 6) {
            startRow = 3;
            startCol = ((n - 3) - 1) * 3;
        } else {
            startRow = 6;
            startCol = ((n - 6) - 1) * 3;
        }

        int[][] square = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square[i][j] = sudoku[startRow + i][startCol + j];
            }
        }
        return square;
    }

    public void printSudoku() {
        String sudokuString = "-----------------------\n";
        for (int row = 0; row < 9; row++) {
            String rowString = "|";
            for (int col = 0; col < 9; col++) {
                if (!(sudoku[row][col] == 0)) {
                    rowString += sudoku[row][col] + " ";
                } else {
                    rowString += "_ ";
                }
                if ((col + 1) % 3 == 0) {
                    rowString += "|";
                }
            }
            rowString += "\n";
            if ((row + 1) % 3 == 0) {
                rowString += "-----------------------\n";
            }

            sudokuString += rowString;
        }
        System.out.println(sudokuString);
    }


    public int[] extractNthColumn(int n) {
        int[] column = new int[sudoku.length];
        for (int i = 0; i < sudoku.length; i++) {
            column[i] = sudoku[i][n - 1];
        }
        return column;
    }

    public int[] extractNthRow(int n) {
        return sudoku[n - 1];
    }

    public boolean checkSolved() {
        for (int i = 1; i < 10; i++) {
            if ((checkSquare(i) && checkColoumn(i) && checkRow(i)) == false) {
                return false;
            }
        }
        return true;
    }


    public boolean checkRow(int n) {
        return containsAllNumbers(sudoku[n - 1]);
    }

    public boolean checkColoumn(int n) {
        int[] coloumn = extractNthColumn(n);
        return containsAllNumbers(coloumn);
    }

    public boolean checkSquare(int n) {
        return true;
    }

    public int[][] getSudoku() {
        return sudoku;
    }


}
