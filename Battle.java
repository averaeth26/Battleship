import java.util.Scanner;

public class Battle {
    Scanner scan = new Scanner(System.in);
    // Counts number of occurrances of a number in a 2d integer array.
    public int countOf(int[][] arr, int testVal) {
        int numRows = arr.length;
        int currentCount = 0;
        int numCols = arr[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (arr[r][c] == testVal) {
                    currentCount += 1;
                }
            }
        }
        return currentCount;
    }
    public void guess(int[][] board) {
        System.out.println("Like ship locations, guesses should be written in row column form (such as a1 or c10).");
        while (countOf(board, 1) > 0) {
             System.out.print("Where would you like to guess: ");
        String guessNum = scan.nextLine();
        int guessRow = -1;
        int guessCol = -1;
        do {
            if (!(guessNum.length() > 1 && guessNum.substring(0, 1).matches("[A-Ja-j]+") && guessNum.substring(1).matches("[0-9]+"))) {
                System.out.print("Invalid guess! Try again: ");
                guessNum = scan.nextLine();
                continue;
            }
            guessRow = (int)(guessNum.toLowerCase().charAt(0)) - 'a';
            guessCol = Integer.parseInt(guessNum.substring(1))-1;
            if (guessCol < 0 || guessCol > 9) {
                System.out.print("Invalid guess! Try again: ");
                guessNum = scan.nextLine();
            }
            } while (guessCol < 0 || guessCol > 9);
        if (board[guessRow][guessCol] == 2) {
            System.out.println("Oops, you already guessed there! Try again");
        }
        if (board[guessRow][guessCol] == 1) {
            System.out.println("Nice, you found an enemy ship!");
            board[guessRow][guessCol] = 2;
        }
        }
    }
}