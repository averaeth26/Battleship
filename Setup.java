import java.util.Scanner;

// This class is responsible for the setup phase of the program, where the player and the opponent both set up their ships to prepare for attack.
public class Setup {
    Scanner scan = new Scanner(System.in);
    int numRows = 10;
    int numCols = 10;
    int[] shipsSpeed = {3, 4, 5};
    int[] shipsRegular = {2, 3, 3, 4, 5};


    int[][] playerBoard = new int[numRows][numCols];
    int[][] opponentBoard = new int[numRows][numCols];

    // Sets up the player-placed board in Normal mode
    public void userSetup(int[][] board, int[] ships) {
        System.out.println("Use row column format to place your ships on the board (Example: a1 or c10).\n");
        System.out.println("You will first place one end of your ship, then the other.\n");
        System.out.println("Additionally, if you ever want to replace your current ship, just type reset!\n");
        // Loops through each of the ships from shortest to longest (in the order of the ships array).
        int numShips = ships.length;
        for(int ship = 0; ship < numShips; ship++) {

            boolean reset = false;
            System.out.print("Where would you like to place one end of your " + ships[ship] + " tile long ship?: ");
            String tileNum = scan.nextLine();
            // Challenge: Getting the reset system to work was a huge challenge as I had to work it in around the existing code, which made it difficult for me to figure out where to put what.
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
            while (startPosCol > 9 || startPosCol < 0 || board[startPosRow][startPosCol] != 0) {
                System.out.print("Invalid placement! Try again: ");
                tileNum = scan.nextLine();
                if (tileNum.toLowerCase().equals("reset")) {
                        reset = true;
                        break;
                    }
            // If statement to prevent scenarios that would result in crashes due to invalid input validation.
            // Challenge: Finding and writing code to prevent every type of input that caused the program to crash.
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
            if (endPosCol < 10 && endPosCol > 0) {
                for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                    for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                        if (board[r][c] == 1)
                            overlapping = true;
                    }
                }
            }
            // Second while loop checks to see if the end pos is a valid location on the grid. 
            // It also checks if the end pos is the correct distance away from the start pos
            while ((Math.abs(startPosRow - endPosRow) + Math.abs(startPosCol - endPosCol) != ships[ship]-1)
            || (startPosRow != endPosRow && startPosCol != endPosCol)
            || (endPosCol > 9 || endPosCol < 0)
            || board[endPosRow][endPosCol] != 0 || overlapping == true) {
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
                        if (board[r][c] == 1)
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
                    board[r][c] = 1;
                }
            }
            printGameBoard(board);
        }
    }

    // Places ships randomly on the grid
    public void randomSetup(int[][] board, int[] ships) {
        int numShips = ships.length;
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
        for(int ship = 0; ship < numShips; ship++) {
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
                            if (board[r][startPosCol] == 1)
                                overlapping = true;
                        }
                    }
                    else {
                        if (directions[dir] < 0 || directions[dir] > numCols-1) {
                            continue;
                        }
                        for (int c = Math.min(startPosCol, directions[dir]); c <= Math.max(startPosCol, directions[dir]); c++) {
                            if (board[startPosRow][c] == 1) {
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
            // System.out.println("\n" + (char)(startPosRow+'a') + " " + (startPosCol+1) + " - " + (char)(endPosRow+'a') + " " + (endPosCol+1));
            // Adds each ship to the enemy board
            for (int r = Math.min(startPosRow, endPosRow); r <= Math.max(startPosRow, endPosRow); r ++) {
                for (int c = Math.min(startPosCol, endPosCol); c <= Math.max(startPosCol, endPosCol); c ++) {
                    board[r][c] = 1;
                }
            }
            // printGameBoard(opponentBoard);  
        }
    }

    // Getter function: Returns the player's game board.
    public int[][] getPlayerBoard() {
        return playerBoard;
    }
    // Getter function: Returns the opponent's game board.
    public int[][] getOpponentBoard() {
        return opponentBoard;
    }

    public void regularSetup() {
        printGameBoard(playerBoard);
        userSetup(playerBoard, shipsRegular);
    }
    public void speedSetup() {
        numRows = 8;
        numCols = 8;
        printGameBoard(playerBoard);
        randomSetup(playerBoard, shipsSpeed);
        randomSetup(opponentBoard, shipsSpeed);
    }

    // Prints out the board with row and column labels to the console.
    public void printGameBoard(int[][] board) {
        System.out.print("  ");
        for (int i = 0; i < numCols; i++) {
            System.out.print(" " + (i+1) + " ");
        }
        System.out.println("");
        for (int r = 0; r < numRows; r++) {
            System.out.print((char)(r+'a') + " ");
            for (int c = 0; c < numCols; c++) {
                if (board[r][c] == 0) {
                    System.out.print(" . ");
                }
                else if (board[r][c] == 1) {
                    System.out.print("[:]");
                }
                else if (board[r][c] == 2) {
                    System.out.print(" x "); 
                }
                else {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
