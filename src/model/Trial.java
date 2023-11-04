package model;

import controller.Controller;

/**
 * Represents an ongoing trial to determine the fairness of a group of dice.
 */
public class Trial {
    private final int amountOfDice;
    private final int sidesOnDie = 6; //TODO option: allow for multiple types of dice beyond d6's through overloaded constructor
    private final Controller controller;
    private int[] savedInput; //state of amount of each side of die rolled.

    /**
     * Creates an instance of a dice trial.
     * @param controller a reference to the controller, in case data needs to be passed up from the model.
     * @param amountOfDice The number of dice to be tested in this trial.
     */
    public Trial(Controller controller, int amountOfDice) {
        this.controller = controller;
        this.amountOfDice = amountOfDice;
        this.savedInput = new int[sidesOnDie];
    }

    /**
     * For each side of the die records the amount of that outcome.
     * @return an array of length corresponding to the number of sides on the dice with a record of how many
     * 1's, 2's, etc. were rolled in this throw.
     */
    public int[] recordThrow(){
        int[] newThrow = new int[sidesOnDie];
        int diceLeftToRecord = amountOfDice;
        for (int i = 0; i < sidesOnDie; i++) {
            int outcomeAmount = controller.requestAmountOfOutcome(i+1);
            newThrow[i] = outcomeAmount;
            diceLeftToRecord = diceLeftToRecord - outcomeAmount;
            if (diceLeftToRecord <= 0)
                break;
        }
        return newThrow;
    }

    /**
     * Records the thrown results into the state of model.
     * @param throwToSave array with length equalling the number of sides on a die, and the amount of outcomes
     *                    for each side.
     */
    public void saveToModel(int[] throwToSave) {
        for (int i = 0; i < sidesOnDie; i++)
            savedInput[i] = savedInput[i] + throwToSave[i];
    }

}
