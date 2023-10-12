import java.util.Scanner;

// This class will control the flow of the game throughout the phase where players try to find and sink each other's ships (Game loop).
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
    // This function controls the player's guessing
    public void playerGuess(int[][] board) {
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
            if (board[guessRow][guessCol] >= 2) {
                System.out.println("Oops, you already guessed there!");
            }
            else if (board[guessRow][guessCol] == 1) {
                System.out.println("Nice, you found an enemy ship!");
                board[guessRow][guessCol] = 2;
            }
            else {
                System.out.println("Splash! Your missile lands in an empty patch of water.");
                board[guessRow][guessCol] = 3;
            }
            printMissileBoard(board);
    }

    public void opponentGuess(int[][] board) {
        int guessRow = (int)(Math.random()*10);
        int guessCol = (int)(Math.random()*10);
        System.out.println("Your opponent guessed " + (char)(guessRow+'a'));
        if (board[guessRow][guessCol] >= 2) {
            System.out.println("Oops, you already guessed there!");
        }
        else if (board[guessRow][guessCol] == 1) {
            System.out.println("Nice, you found an enemy ship!");
            board[guessRow][guessCol] = 2;
        }
        else {
            System.out.println("Splash! Your missile lands in an empty patch of water.");
            board[guessRow][guessCol] = 3;
        }
        
    }
    // This function controls guessing and hit/win detection for the normal gamemode.
    public void guessRegular(int[][] playerBoard, int[][] opponentBoard) {
        System.out.println("Like ship locations, guesses should be written in row column form (such as a1 or c10).\n");
        int numGuesses = 0;
        while (countOf(playerBoard, 1) > 0 && countOf(opponentBoard, 1) > 0) {
            playerGuess(opponentBoard);
            opponentGuess(playerBoard);
        }
        System.out.println("Congratulations! You successfully sank all of your opponent's ships in " + numGuesses + " guesses!");
    }

    // Prints the game board from the enemy player's perspective.
    public void printMissileBoard(int[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        System.out.print("   ");
        for (int i = 0; i < numCols; i++) {
            System.out.print(i+1 + "  ");
        }
        System.out.println("");
        for (int r = 0; r < numRows; r++) {
            System.out.print((char)(r+'a') + "  ");
            for (int c = 0; c < numCols; c++) {
                if (board[r][c] < 2) {
                    System.out.print("?  ");
                }
                else if (board[r][c] == 2) {
                    System.out.print("x  ");
                }
                else {
                    System.out.print("-  ");
                }
            }
            System.out.println("");
        }
    }
}