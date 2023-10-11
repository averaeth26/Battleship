// This class is responsible for the execution and ordering of the program and ties all of the other classes together.
public class Tester {
    // Main method actually runs the program.
    public static void main(String[] args) {
        Intro introduction = new Intro();
        Setup grid = new Setup();
        Battle guesses = new Battle();
        introduction.instructions();
        // grid.printBoard(grid.getPlayerBoard());
        // grid.userRegularSetup();
        grid.randomSetup();
        guesses.guess(grid.getOpponentBoard());
    }
}