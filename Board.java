import java.util.Scanner;

public class Board {
    Scanner scan = new Scanner(System.in);
    int numRows = 10;
    int numCols = 10;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    int[] ships = {2, 3, 3, 4, 5};
    int[][] game = new int[numRows][numCols];

    public void setup() {
        for(int ship = 0; ship < ships.length; ship++) {
            System.out.print("Where would you like to place one end of your " + ships[ship] + " tile long ship?: ");
            String tileNum = scan.nextLine();
            int startPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
            int startPosCol = Integer.parseInt(tileNum.substring(1))-1;

            // While loop checks to see if the start pos is a valid location on the grid
            while (startPosRow > 9 || startPosRow < 0 || startPosCol > 9 || startPosCol < 0 || game[startPosRow][startPosCol] != 0) {
            System.out.println("Invalid placement! Try again: ");
            tileNum = scan.nextLine();
            startPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
            startPosCol = Integer.parseInt(tileNum.substring(1))-1;
            }
            System.out.println("Where would you like the other end of your ship to be placed?: ");
            tileNum = scan.nextLine();
            int endPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
            int endPosCol = Integer.parseInt(tileNum.substring(1))-1;

            // Second while loop checks to see if the end pos is a valid location on the grid. 
            // It also checks if the end pos is the correct distance away from the start pos
            while ((Math.abs(startPosRow - endPosRow) + Math.abs(startPosCol - endPosCol) != ships[ship]-1)
            || (startPosRow != endPosRow && startPosCol != endPosCol)
            || (endPosRow > 9 || endPosRow < 0 || endPosCol > 9 || endPosCol < 0)
            || game[endPosRow][endPosCol] != 0) {
            System.out.println("Invalid placement! Try again: ");
            tileNum = scan.nextLine();
            endPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
            endPosCol = Integer.parseInt(tileNum.substring(1))-1;
            System.out.println(endPosCol);
            }

            // Actually adds the ship tiles to the grid once it's validated.
            for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                    game[r][c] = 1;
                }
            }
            printBoard();
        }
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < numCols; i ++) {
            System.out.print(i+1 + "  ");
        }
        System.out.println("");
        for (int r = 0; r < numRows; r++) {
            System.out.print((char)(r+'a') + "  ");
            for (int c = 0; c < numCols; c++) {
                System.out.print(game[r][c] + "  ");
            }
            System.out.println("");
        }
    }
}
