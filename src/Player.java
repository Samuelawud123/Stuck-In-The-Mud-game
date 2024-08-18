/**
 * The Player class represents a participant in the Stuck in the Mud game.
 * Each player has a name, a score, and keeps track of the stuck status of each die they roll.
 */
public class Player {
    private final String name;
    private int score;
    private boolean die1Stuck, die2Stuck, die3Stuck, die4Stuck;

    /**
     * Constructs a new player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Adds the score from a single turn to the player's total score.
     *
     * @param roundScore The score achieved in the current round.
     */
    public void addScore(int roundScore) {
        this.score += roundScore;
    }

    /**
     * Gets the player's name.
     *
     * @return A string representing the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's current score.
     *
     * @return The player's total score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates the stuck status of each die based on the latest dice roll.
     * A die is considered stuck if it rolls a number that is designated as a 'stuck' number.
     *
     * @param diceRoll An array representing the result of the dice roll.
     */
    public void checkIfStuck(int[] diceRoll) {
        die1Stuck = diceRoll[0] == 2 || diceRoll[0] == 5;
        die2Stuck = diceRoll[1] == 2 || diceRoll[1] == 5;
        die3Stuck = diceRoll[2] == 2 || diceRoll[2] == 5;
        die4Stuck = diceRoll[3] == 2 || diceRoll[3] == 5;
    }

    /**
     * Resets the stuck status of all the player's dice to not stuck.
     * This is typically done at the start of a player's turn.
     */
    public void resetStuckDice() {
        die1Stuck = die2Stuck = die3Stuck = die4Stuck = false;
    }

    /**
     * Checks if a specific die is stuck based on its index.
     *
     * @param dieIndex The index of the die (0-3).
     * @return true if the die is stuck, false otherwise.
     * @throws IllegalArgumentException If the die index is invalid.
     */
    public boolean isDiceStuck(int dieIndex) {
        return switch (dieIndex) {
            case 0 -> die1Stuck;
            case 1 -> die2Stuck;
            case 2 -> die3Stuck;
            case 3 -> die4Stuck;
            default -> throw new IllegalArgumentException("Invalid die index");
        };
    }

    /**
     * Provides a string representation of the player's status, including their name,
     * current score, and the stuck status of each die.
     *
     * @return A string representing the player's status.
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", die1Stuck=" + die1Stuck +
                ", die2Stuck=" + die2Stuck +
                ", die3Stuck=" + die3Stuck +
                ", die4Stuck=" + die4Stuck +
                '}';
    }
}
