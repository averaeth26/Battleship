// This class is responsible for the execution and ordering of the program and ties all of the other classes together.
public class Tester {
    // Main method actually runs the program.
    public static void main(String[] args) {
        Intro introduction = new Intro();
        Setup grid = new Setup();
        Battle guesses = new Battle();
        introduction.instructions();
        introduction.pickGameMode();
        if (introduction.getGameMode().equals("r") || introduction.getGameMode().equals("regular")) {
            grid.regularSetup();
            guesses.guessRegular(grid.getOpponentBoard());
        }
        else {
            grid.speedSetup();
            guesses.guessSpeed(grid.getPlayerBoard(), grid.getOpponentBoard());
        }
        // grid.printGameBoard(grid.getOpponentBoard());
        // grid.userSetup();
        // grid.randomSetup(grid.getPlayerBoard());
        // grid.randomSetup(grid.getOpponentBoard());
        // grid.printGameBoard(grid.getPlayerBoard());
        // guesses.printMissileBoard(grid.getOpponentBoard());
        // guesses.guessSpeed(grid.getPlayerBoard(), grid.getOpponentBoard());
    }
}   