package src;

public class MarcoMain {


    public static void main(String[] args) {
        SudokuMain sudokuMain = new SudokuMain();
        SudokuField sudokuField = sudokuMain.getSudokuField();
        SudokuField solvedSudoku = sudokuMain.getSudokuSolution();
        System.out.println("sudoku:");
        sudokuField.printSudoku();
        System.out.println("solution:");
        sudokuMain.getSudokuSolution().printSudoku();
        System.out.println("********************************************************");
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuField);

        System.out.println(solvedSudoku.checkSolved());


        SudokuField solved = sudokuSolver.easySolve();
        System.out.println("solved: ");
        solved.printSudoku();


    }
}