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

                for (int coloumn = 0; coloumn < sudoku[rows].length; coloumn++) {
                    if (sudoku[rows][coloumn] == 0) {
                        List<int[]> list = new ArrayList<>();
                        for (int i = 0; i < 9; i++) {
                            list.add(possibilities[rows][i]);
                        }
                        int solvedNumber = 0;
                        List<Integer> conjunctionColoumn = SudokuUtilities.findUniqueNumbers(coloumn, list);
                        if (conjunctionColoumn.size() == 1) {
                            solvedNumber = conjunctionColoumn.get(0);
                            intermediateSolve[rows][coloumn] = solvedNumber;
                        }
                    }
                }
            }

//            for (int rows = 0; rows < sudoku.length; rows++) {
//                for (int coloumns = 0; coloumns < sudoku[rows].length; coloumns++) {
//                }
//            }


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

}
