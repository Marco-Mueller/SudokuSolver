package src;

public class SudokuLibrary {
    public static final int x = 0;
    //@formatter:off


    private int[][] sudoku1 =
                   {{9, x, x, 5, x, 8, x, x, 7},
                    {x, 8, x, 3, x, 2, 9, x, 5},
                    {x, 5, 4, x, x, x, x, 8, x},
                    {x, 7, x, 6, 8, x, x, 3, 2},
                    {1, x, x, x, x, 4, x, x, 8},
                    {5, x, x, 2, 1, 9, x, 6, x},
                    {x, x, x, 9, x, 6, x, x, 1},
                    {7, 2, 6, x, x, 1, x, 4, x},
                    {x, x, 1, 4, 7, x, x, 5, 6}};

    private int[][] sudoku2 =
                    {{4, 3, 5, 2, 6, 9, x, 8, 1},
                    {6, 8, x, 5, 7, 1, 4, 9, 3},
                    {1, 9, 7, x, 3, 4, 5, 6, 2},
                    {8, 2, 6, 1, 9, 5, 3, 4, 7},
                    {3, x, 4, 6, 8, 2, 9, 1, 5},
                    {9, 5, 1, 7, 4, x, 6, 2, 8},
                    {5, 1, 9, 3, 2, 6, 8, 7, 4},
                    {x, 4, 8, 9, 5, 7, 1, 3, 6},
                    {7, 6, 3, 4, 1, 8, 2, 5, x}};





    //@formatter:on

    private int[][] sudoku3Solution =
            {{6, 4, 1, 2, 9, 8, 5, 3, 7},
                    {3, 5, 2, 1, 7, 6, 9, 8, 4},
                    {7, 9, 8, 3, 4, 5, 1, 6, 2},
                    {9, 2, 3, 6, 1, 4, 8, 7, 5},
                    {1, 8, 6, 5, 3, 7, 4, 2, 9},
                    {5, 7, 4, 9, 8, 2, 6, 1, 3},
                    {8, 3, 5, 7, 6, 9, 2, 4, 1},
                    {4, 1, 9, 8, 2, 3, 7, 5, 6},
                    {2, 6, 7, 4, 5, 1, 3, 9, 8}};

    private int[][] sudoku3 =
            {{6, 4, 1, 2, 9, 8, 5, 3, 7},
                    {3, 5, 2, 1, 7, 6, 9, 8, 4},
                    {7, 9, 8, 3, 4, 5, 1, 6, 2},
                    {9, 2, 3, 6, 1, 4, 8, 7, 5},
                    {1, 8, 6, 5, 3, 7, 4, 2, 9},
                    {5, 7, 4, 9, 8, 2, 6, 1, 3},
                    {8, 3, 5, 7, 6, 9, 2, 4, 1},
                    {4, 1, 9, 8, 2, 3, 7, 5, 6},
                    {2, 6, 7, 4, 5, 1, 3, 9, 8}};

    private int[][] sudoku4 =
            {{x, x, 5, 4, 8, x, x, 6, 7},
                    {8, 3, x, x, 6, 9, 5, x, x},
                    {7, x, 6, 5, x, x, 4, x, 8},
                    {x, 7, x, 9, x, 6, x, 5, 2},
                    {6, x, 3, x, 7, 2, 1, 9, x},
                    {x, 2, 9, 1, x, x, 8, x, x},
                    {3, 8, x, x, 5, 7, x, x, 9},
                    {x, x, 7, 3, x, 4, 2, 8, x},
                    {5, x, 2, 6, x, x, 7, x, 3}};

    private int[][] sudoku4Solution =
            {{2, 1, 5, 4, 8, 3, 9, 6, 7},
                    {8, 3, 4, 7, 6, 9, 5, 2, 1},
                    {7, 9, 6, 5, 2, 1, 4, 3, 8},
                    {1, 7, 8, 9, 4, 6, 3, 5, 2},
                    {6, 5, 3, 8, 7, 2, 1, 9, 4},
                    {4, 2, 9, 1, 3, 5, 8, 7, 6},
                    {3, 8, 1, 2, 5, 7, 6, 4, 9},
                    {9, 6, 7, 3, 1, 4, 2, 8, 5},
                    {5, 4, 2, 6, 9, 8, 7, 1, 3}};


    private int[][] sudoku5Difficult =
            {{5, x, x, x, x, 3, 8, x, 6},
                    {6, x, x, 1, x, 9, x, x, x},
                    {x, x, x, x, 4, x, 1, x, x},
                    {9, x, x, x, x, x, x, 5, x},
                    {x, 4, x, x, x, x, 7, 2, x},
                    {x, 8, x, x, x, 1, x, 3, 4},
                    {x, x, 7, x, 5, x, x, 6, x},
                    {x, x, x, 8, x, x, 2, x, x},
                    {x, x, x, x, x, x, x, x, x}};

    private int[][] sudoku5DifficultSolution =
            {{5, 9, 1, 7, 2, 3, 8, 4, 6},
                    {6, 2, 4, 1, 8, 9, 5, 7, 3},
                    {3, 7, 8, 5, 4, 6, 1, 9, 2},
                    {9, 3, 2, 4, 7, 8, 6, 5, 1},
                    {1, 4, 6, 9, 3, 5, 7, 2, 8},
                    {7, 8, 5, 2, 6, 1, 9, 3, 4},
                    {8, 1, 7, 3, 5, 2, 4, 6, 9},
                    {4, 6, 3, 8, 9, 7, 2, 1, 5},
                    {2, 5, 9, 6, 1, 4, 3, 8, 7}};


    public SudokuLibrary() {

    }

    public SudokuField getSudoku1() {
        return new SudokuField(sudoku1);
    }

    public SudokuField getSudoku2() {
        return new SudokuField(sudoku2);
    }

    public SudokuField getSudoku4() {
        return new SudokuField(sudoku4);
    }

    public SudokuField getSudoku4Solution() {
        return new SudokuField(sudoku4Solution);
    }

    public SudokuField getSudoku5Difficult() {
        return new SudokuField(sudoku5Difficult);
    }

    public SudokuField getSudoku5DifficultSolution() {
        return new SudokuField(sudoku5DifficultSolution);
    }
}
