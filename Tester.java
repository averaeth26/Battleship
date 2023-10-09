public class Tester {
    public static void main(String[] args) {
        Intro.instructions();
        Setup grid = new Setup();
        grid.printBoard(grid.getPlayerBoard());
        grid.userRegularSetup();
        grid.randomSetup();
    }
}