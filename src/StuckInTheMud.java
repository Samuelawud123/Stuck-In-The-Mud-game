import java.util.Random;
/**
 * The StuckInTheMud class represents a simple dice game where players roll dice
 * and accumulate points unless they roll specific "stuck" numbers.
 */
public class StuckInTheMud {
    private final CircularLinkedList<Player> players;
    private final Random random;
    private static final int WINNING_SCORE = 100;
    private static final int NUM_DICE = 4;
    private static final int STUCK_NUMBER_1 = 2;
    private static final int STUCK_NUMBER_2 = 5;
    /**
     * Constructs a new StuckInTheMud game with the specified player names.
     *
     * @param playerNames An array of strings representing the names of the players.
     */
    public StuckInTheMud(String[] playerNames) {
        players = new CircularLinkedList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        random = new Random();
    }
    /**
     * Starts the game of Stuck in the Mud. The game continues until a player wins by reaching
     * the winning score.
     */
    public void startGame() {
        System.out.println("\nLet's start the game of Stuck in the Mud!\n");
        while (!isGameWon()) {
            for (int i = 0; i < players.getSize(); i++) {
                Player currentPlayer = players.get(i);
                int turnScore = takeTurn(currentPlayer);
                currentPlayer.addScore(turnScore);

                System.out.println(currentPlayer.getName() + " scores " + turnScore +
                        " points this round and has a total score of  " + currentPlayer.getScore() + "\n");

                if (currentPlayer.getScore() >= WINNING_SCORE) {
                    System.out.println(currentPlayer.getName() + " wins with a score of " +
                            currentPlayer.getScore() + "!");
                    printFinalScores();
                    return;
                }
            }
        }
    }
    /**
     * Handles a single turn for the given player. Rolls the dice, updates scores,
     * and checks for stuck dice.
     *
     * @param player The player who is taking the turn.
     * @return The score obtained in this turn.
     */
    private int takeTurn(Player player) {
        System.out.println(player.getName() + "'s turn:");
        player.resetStuckDice(); // Reset the stuck status at the start of the turn
        int turnScore = 0;
        int[] diceRolls = new int[NUM_DICE];
        try {
            Thread.sleep(300); // Simulate time delay for user experience
            for (int i = 0; i < NUM_DICE; i++) {
                diceRolls[i] = random.nextInt(6) + 1; // Roll the die
                if (player.isDiceStuck(i)) {
                    System.out.println("Die " + (i + 1) + " was already stuck in the mud from the last turn!");
                } else if (diceRolls[i] == STUCK_NUMBER_1 || diceRolls[i] == STUCK_NUMBER_2) {
                    System.out.println("Die " + (i + 1) + " rolls a " + diceRolls[i] +
                            " and it's now stuck in the mud!");
                    player.checkIfStuck(diceRolls);
                } else {
                    System.out.println("Die " + (i + 1) + " rolls a " + diceRolls[i] + " and it's free!");
                    turnScore += diceRolls[i];
                }
                Thread.sleep(500); // Sleep after each die roll
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Game was interrupted.");
        }
        return turnScore;
    }

    /**
     * Prints the final scores of all players at the end of the game.
     */
    private void printFinalScores() {
        System.out.println("\nFinal scores:");
        for (int i = 0; i < players.getSize(); i++) {
            Player player = players.get(i);
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }
    /**
     * Checks if any player has won the game by reaching the winning score.
     *
     * @return true if the game is won, false otherwise.
     */
    private boolean isGameWon() {
        for (int i = 0; i < players.getSize(); i++) {
            if (players.get(i).getScore() >= WINNING_SCORE) {
                return true;
            }
        }
        return false;
    }
    /**
     * The main method to start a game of Stuck in the Mud.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String[] playerNames = {"Surafel", "Aymen"};
        StuckInTheMud game = new StuckInTheMud(playerNames);
        game.startGame();
    }
}
