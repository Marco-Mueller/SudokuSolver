package src;

public class SudokuMain {
    public static final int x = 0;
    private SudokuField sudokuField;
    private SudokuField sudokuSolution;


    public SudokuMain(SudokuField sudokuField) {
        int x = 1;
        System.out.println(x);
    }

    public SudokuMain() {
        SudokuLibrary sudokus = new SudokuLibrary();
        sudokuField = sudokus.getSudoku5Difficult();
        sudokuSolution = sudokus.getSudoku5DifficultSolution();
    }

    public SudokuField getSudokuField() {
        return sudokuField;
    }

    public SudokuField getSudokuSolution() {
        return sudokuSolution;
    }
}
