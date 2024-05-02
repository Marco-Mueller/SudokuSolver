package src;

import org.apache.commons.lang.SerializationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SudokuSolver {
    private SudokuField sudokuField;

    private int[][][] possibilities = new int[9][9][];

    public SudokuSolver(SudokuField sudokuField) {
        this.sudokuField = sudokuField;
    }


    public void initPossibilities() {
        int[][] sudoku = sudokuField.getSudoku();

        for (int row = 0; row < sudoku.length; row++) {
            for (int coloumn = 0; coloumn < sudoku[row].length; coloumn++) {
                if (sudoku[row][coloumn] != 0) {
                    possibilities[row][coloumn] = new int[]{sudoku[row][coloumn]};
                }
            }
        }
    }

    public SudokuField getSudokuField() {
        return sudokuField;
    }

    public void setSudokuField(SudokuField sudokuField) {
        this.sudokuField = sudokuField;
    }

    public List<Integer> missingNumbers(int[] numbers) {
        List<Integer> presentNumbers = IntStream.of(numbers).boxed().collect(Collectors.toList());

        return IntStream.range(1, 10).filter(num -> !presentNumbers.contains(num)).boxed().collect(Collectors.toList());
    }

    public SudokuField easySolve() {
        initPossibilities();
        int[][] intermediateSolve = (int[][]) SerializationUtils.clone(sudokuField.getSudoku());
        int iterations = 0;

        SudokuField intermediateSolution = sudokuField;
        int[][] sudoku = sudokuField.getSudoku();
        while (!intermediateSolution.checkSolved() && iterations < 10) {
            intermediateSolution = new SudokuField(intermediateSolve);
            for (int rows = 0; rows < sudoku.length; rows++) {
                for (int coloumns = 0; coloumns < sudoku[rows].length; coloumns++) {
                    if (intermediateSolve[rows][coloumns] == 0) {
                        int[] row = intermediateSolution.extractNthRow(rows + 1);
                        int[] coloumn = intermediateSolution.extractNthColumn(coloumns + 1);
                        int[] square = Arrays.stream(intermediateSolution.extractSquare(getCurrentSquare(rows + 1, coloumns + 1)))
                                .flatMapToInt(Arrays::stream).toArray();


                        List<Integer> missingNumbersRows = missingNumbers(row);
                        List<Integer> missingNumbersColoumns = missingNumbers(coloumn);
                        List<Integer> missingNumbersSquare = missingNumbers(square);


                        int solvedNumber = 0;
                        List<Integer> conjunction1 = SudokuUtilities.unifyLists(missingNumbersRows, missingNumbersColoumns, missingNumbersSquare);
                        if (conjunction1.size() == 1) {
                            solvedNumber = conjunction1.get(0);
                        }
                        possibilities[rows][coloumns] = listToIntArray(conjunction1);
                        intermediateSolve[rows][coloumns] = solvedNumber;
                    }
                }


            }

            for (int rows = 0; rows < sudoku.length; rows++) {
                for (int coloumns = 0; coloumns < sudoku[rows].length; coloumns++) {

                    int[][] coloumnPossibilities;
                    int[][] rowPossibilities;
                    int[][] squarePossibilities;

                    if (sudoku[rows][coloumns] == 0) {
                        List<int[]> list = new ArrayList<>();
                        rowPossibilities = extractNthRowPossibilities(rows);
                        for (int i = 0; i < 9; i++) {
                            list.add(rowPossibilities[i]);
                        }

                        List<Integer> conjunctionRow = SudokuUtilities.findUniqueNumbers(coloumns, list);
                        if (conjunctionRow.size() == 1) {
                            int solvedNumber = conjunctionRow.get(0);
                            intermediateSolve[rows][coloumns] = solvedNumber;
                            possibilities[rows][coloumns] = new int[]{solvedNumber};
                        }

                    }


                    if (sudoku[rows][coloumns] == 0) {
                        List<int[]> list = new ArrayList<>();
                        coloumnPossibilities = extractNthColumnPossibilities(coloumns);
                        for (int i = 0; i < 9; i++) {
                            list.add(coloumnPossibilities[i]);
                        }

                        int solvedNumber = 0;
                        List<Integer> conjunctionRow = SudokuUtilities.findUniqueNumbers(rows, list);
                        if (conjunctionRow.size() == 1) {
                            solvedNumber = conjunctionRow.get(0);
                            intermediateSolve[rows][coloumns] = solvedNumber;
                        }
                    }

//                    if (sudoku[rows][coloumns] == 0) {
//                        List<int[]> list = new ArrayList<>();
//                        squarePossibilities = extractSquarePossibilities(getCurrentSquare(rows + 1, coloumns + 1));
//                        for (int i = 0; i < 9; i++) {
//                            list.add(squarePossibilities[i]);
//                        }
//
//                        int solvedNumber = 0;
//                        int nthTile = coloumns % 3 + 3 * (rows % 3);
//                        List<Integer> conjunctionRow = SudokuUtilities.findUniqueNumbers(nthTile, list);
//                        if (conjunctionRow.size() == 1) {
//                            solvedNumber = conjunctionRow.get(0);
//                            intermediateSolve[rows][coloumns] = solvedNumber;
//                        }
//                    }

                    if (rows == 0 && coloumns == 1) {
                        int x = 0;
                    }


                }
            }


            iterations++;
            System.out.println("solving iteration: " + iterations + " ...");
            intermediateSolve = (int[][]) SerializationUtils.clone(intermediateSolve);
            intermediateSolution = new SudokuField(intermediateSolve);
            System.out.println("intermediate solution:");
            intermediateSolution.printSudoku();
        }
        return new SudokuField(intermediateSolve);
    }

    public int[] listToIntArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getCurrentSquare(int row, int coloumn) {
        return ((row - 1) / 3) * 3 + 1 + ((coloumn - 1) / 3);
    }

    public int[][] extractNthColumnPossibilities(int n) {
        int[][] columnPossibilities = new int[9][];
        for (int i = 0; i < 9; i++) {
            columnPossibilities[i] = possibilities[i][n];
        }
        return columnPossibilities;
    }

    public int[][] extractNthRowPossibilities(int n) {
        return possibilities[n];
    }

    public int[][] extractSquarePossibilities(int n) {
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

        int[][][] square = new int[3][3][];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square[i][j] = possibilities[startRow + i][startCol + j];
            }
        }


        return flattenFirstTwoDimensions(square);
    }

    public int[][] flattenFirstTwoDimensions(int[][][] array) {
        int firstDimensionSize = array.length;
        int secondDimensionSize = array[0].length;
        int thirdDimensionSize = array[0][0].length;

        int[][] flattenedArray = new int[firstDimensionSize * secondDimensionSize][thirdDimensionSize];

        int index = 0;
        for (int i = 0; i < firstDimensionSize; i++) {
            for (int j = 0; j < secondDimensionSize; j++) {
                flattenedArray[index++] = array[i][j];
            }
        }

        return flattenedArray;
    }


}
