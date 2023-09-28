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
            System.out.print("Where would you like to place your " + ships[ship] + " tile long ship: ");
            String tileNum = scan.nextLine();
            System.out.println((int)(tileNum.charAt(1))-'1');
            game[(int)(tileNum.toLowerCase().charAt(0)) - 'a'][(int)(tileNum.charAt(1))-'1'] = 1;
        }
    }

    public void printBoard() {
        for (int r = 0; r < numRows; r++) {
            System.out.print((char)(r+'a') + " ");
            for (int c = 0; c < numCols; c++) {
                System.out.print(game[r][c] + " ");
            }
            System.out.println("");
        }
    }
}
