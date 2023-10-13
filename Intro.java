import java.util.Scanner;
// This class is responsible for anything before the setup phase
public class Intro {
    String gameMode;
    // Prints out general instructions for the game.
    public void instructions() {
        System.out.println("Welcome to battleship!\n");
        System.out.println("The aim of the game is to sink all of your opponent's ships\nbefore they sink all of yours.\n");
        System.out.println("The game is broken up into two phases:\n 1. The setup phase\n 2. The attacking phase\n");
        System.out.println("During the setup phase, both players place their ships on the board\n");
        System.out.println("Then, during the attacking phase, players launch missiles at each other's boards,\ntrying to find and destroy their opponent's ships\n");
        System.out.println("Once a player has no ships remaining, the game ends and the player who still has ships left wins!\n");
        System.out.println("Additionally, there are two game modes, regular and fast\n");
        System.out.println("In regular mode, one player places ships on a 10 by 10 board\nthen, a second player tries to guess the ships placed by the first player\n");
        System.out.println("In fast mode, you and a computer face off with randomized 8 by 8 boards\n");
        System.out.println("On the board:\n - Ships are represented by a '[:]' tile");
        System.out.println(" - Missed guesses/ships are represented by a '-' tile");
        System.out.println(" - Correct guesses/hit ships are represented by an 'x' tile\n");
    }
    // Allows the user to pick a regular or fast game.
    public void pickGameMode() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Would you like to play a regular or a fast game? (r/f): ");
        gameMode = scan.nextLine().toLowerCase();
        while (!gameMode.equals("f") && !gameMode.equals("r") && !gameMode.equals("fast") && !gameMode.equals("regular")) {
        System.out.print("Not a valid game mode! Pick again: ");
        gameMode = scan.nextLine().toLowerCase();
        }
        System.out.println("");
    }
    // Getter Function: Returns the user's selected Game Mode.
    public String getGameMode() {
        return gameMode;
    }
}
