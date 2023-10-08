import java.util.Scanner;

public class PlayerBoard {
    Scanner scan = new Scanner(System.in);
    int numRows = 10;
    int numCols = 10;
    int[] ships = {2, 3, 3, 4, 5};
    int numShips = ships.length;
    int[][] playerBoard = new int[numRows][numCols];
    int[][] opponentBoard = new int[numRows][numCols];


    // Sets up the player's board in Normal mode
    public void userRegularSetup() {
        // Loops through each of the ships from shortest to longest (in the order of the ships array).
        for(int ship = 0; ship < numShips; ship++) {

            boolean reset = false;
            System.out.print("Where would you like to place one end of your " + ships[ship] + " tile long ship?: ");
            String tileNum = scan.nextLine();
            if (tileNum.toLowerCase().equals("reset")) {
                ship -= 1;
                continue;
                }

            int startPosRow = -1;
            int startPosCol = -1;
            // If statement to prevent scenarios that would result in crashes due to invalid input validation.
            if (tileNum.length() > 1 && tileNum.substring(0, 1).matches("[A-Ja-j]+") && tileNum.substring(1).matches("[0-9]+")) {
                startPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
                startPosCol = Integer.parseInt(tileNum.substring(1))-1;
            }

            // While loop checks to see if the start pos is a valid location on the grid
            while (startPosCol > 9 || startPosCol < 0 || playerBoard[startPosRow][startPosCol] != 0) {
                System.out.print("Invalid placement! Try again: ");
                tileNum = scan.nextLine();
                if (tileNum.toLowerCase().equals("reset")) {
                        reset = true;
                        break;
                    }
            // If statement to prevent scenarios that would result in crashes due to invalid input validation.
            if (tileNum.length() < 2 || !tileNum.substring(0, 1).matches("[A-Ja-j]+") || !tileNum.substring(1).matches("[0-9]+")) {
                continue;
            }
                startPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
                startPosCol = Integer.parseInt(tileNum.substring(1))-1;
            }
            if (reset == true) {
                ship -= 1;
                continue;
            }
            System.out.print("Where would you like the other end of your ship to be placed?: ");
            tileNum = scan.nextLine();

            if (tileNum.toLowerCase().equals("reset")) {
                ship -= 1;
                continue;
                }
            int endPosRow = 0;
            int endPosCol = 0;
            // If statement to prevent scenarios that would result in crashes due to invalid input validation.
            if (tileNum.length() > 1 && tileNum.substring(0, 1).matches("[A-Ja-j]+") && tileNum.substring(1).matches("[0-9]+")) {
                endPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
                endPosCol = Integer.parseInt(tileNum.substring(1))-1;
            }


            // For loops checks to see if two ships are overlapping
            boolean overlapping = false;
            for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                    if (playerBoard[r][c] == 1)
                        overlapping = true;
                }
            }
            // Second while loop checks to see if the end pos is a valid location on the grid. 
            // It also checks if the end pos is the correct distance away from the start pos
            while ((Math.abs(startPosRow - endPosRow) + Math.abs(startPosCol - endPosCol) != ships[ship]-1)
            || (startPosRow != endPosRow && startPosCol != endPosCol)
            || (endPosCol > 9 || endPosCol < 0)
            || playerBoard[endPosRow][endPosCol] != 0 || overlapping == true) {
                System.out.print("Invalid placement! Try again: ");
                tileNum = scan.nextLine();
                // If the user types "reset", the program sends them back to the beginning of the ship placement
                if (tileNum.toLowerCase().equals("reset")) {
                    reset = true;
                    break;
                }
            // If statement to prevent scenarios that would result in crashes due to invalid input validation.
            if (tileNum.length() < 2 || !tileNum.substring(0, 1).matches("[A-Ja-j]+") || !tileNum.substring(1).matches("[0-9]+")) {
                    continue;
                }
                endPosRow = (int)(tileNum.toLowerCase().charAt(0)) - 'a';
                endPosCol = Integer.parseInt(tileNum.substring(1))-1;
                overlapping = false;
                for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                    for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                        if (playerBoard[r][c] == 1)
                            overlapping = true;
                    }
                }
            }

            if (reset == true) {
                ship -= 1;
                continue;
            }   

            // Actually adds the ship tiles to the grid once it's validated.
            for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                    playerBoard[r][c] = 1;
                }
            }
            printBoard(playerBoard);
        }
    }

    // Places ships randomly on the grid
    public void randomSetup() {
        for(int ship = 0; ship < numShips; ship++) {
            int startPosRow;
            int startPosCol;
            int endPosRow;
            int endPosCol;
            boolean valid;
            boolean overlapping;
            // Challenge: Had trouble figuring out how to calculate valid directions without a ton of repeated code
            // I ended up using the directions list to make the code much less repetitive (instead of just doing the code manually for all 4 directions)
            int[] directions = new int[4];
            boolean[] possibleDirections;
            int randomDir;
            do {
            valid = false;
            startPosRow = (int)(Math.random() * 10);
            startPosCol = (int)(Math.random() * 10);
                // Cycles through the four directions (up, down, left, and right)
                directions[0] = startPosRow-(ships[ship]-1);
                directions[1] = startPosRow+(ships[ship]-1);
                directions[2] = startPosCol-(ships[ship]-1);
                directions[3] = startPosCol+(ships[ship]-1);
                
                possibleDirections = new boolean[directions.length];
                // Checks the possible ship location in each direction for overlapping tiles to determine the possible directions the ship could be placed in
                for (int dir = 0; dir < directions.length; dir++) {
                    overlapping = false;
                    if (dir < 2) {
                        if (directions[dir] < 0 || directions[dir] > numRows-1) {
                            continue;
                        }
                        for (int r = Math.min(startPosRow, directions[dir]); r <= Math.max(startPosRow, directions[dir]); r++) {
                            if (opponentBoard[r][startPosCol] == 1)
                                overlapping = true;
                        }
                    }
                    else {
                        if (directions[dir] < 0 || directions[dir] > numCols-1) {
                            continue;
                        }
                        for (int c = Math.min(startPosCol, directions[dir]); c <= Math.max(startPosCol, directions[dir]); c++) {
                            if (opponentBoard[startPosRow][c] == 1) {
                                overlapping = true;
                                break;
                            }
                        }
                    }
                    // Checking if the loop went all the way through without finding a blocking tile
                    if (!overlapping) {
                        possibleDirections[dir] = true;
                        valid = true;
                    }
                }
            } while (!valid); // Makes sure there is at least one valid location for the ship to be placed in from its starting location
            do {
                endPosRow = startPosRow; 
                endPosCol = startPosCol;
                randomDir = (int)(Math.random()*4);
            if (randomDir < 2) {
                endPosRow = directions[randomDir];
            }
            else {
                endPosCol = directions[randomDir];
            }
            } while (possibleDirections[randomDir] != true); // Generates the other endpoint of the ship in a random valid direction from the starting point
            System.out.println("\n" + (char)(startPosRow+'a') + " " + (startPosCol+1) + " - " + (char)(endPosRow+'a') + " " + (endPosCol+1));
            // Adds each ship to the enemy board
            for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                    opponentBoard[r][c] = 1;
                }
            }
            printBoard(opponentBoard); 
        }
    }

    public void speedSetup () {
        
    }


    public void printBoard(int[][] board) {
        System.out.print("   ");
        for (int i = 0; i < numCols; i++) {
            System.out.print(i+1 + "  ");
        }
        System.out.println("");
        for (int r = 0; r < numRows; r++) {
            System.out.print((char)(r+'a') + "  ");
            for (int c = 0; c < numCols; c++) {
                System.out.print(board[r][c] + "  ");
            }
            System.out.println("");
        }
    }
}
