// This class is responsible for anything before the setup phase
public class Intro {
    // Prints out general instructions for the game.
    public void instructions() {
        System.out.println("Welcome to battleship!\n");
        System.out.println("The aim of the game is to sink all of your opponent's ships\nbefore they sink all of yours.\n");
        System.out.println("The game is broken up into two phases:\n 1. The setup phase\n 2. The attacking phase\n");
        System.out.println("During the setup phase, both players place their ships on the board\n");
        System.out.println("Then, during the attacking phase, players launch missiles at each other's boards,\ntrying to find and destroy their opponent's ships\n");
        System.out.println("Once a player has no ships remaining, the game ends and the player who still has ships left wins!\n");
    }
    // Allows the user to pick a regular or fast game.
    public void pickGameMode() {
        
    }
}
