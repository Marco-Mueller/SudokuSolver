package src;

public class Test {
    public static void main(String[] args) {
        System.out.println(getCurrentSquare(7, 5));
    }

    public static int getCurrentSquare(int row, int coloumn) {
        return ((row - 1) / 3) * 3 + 1 + ((coloumn - 1) / 3);
    }


}
