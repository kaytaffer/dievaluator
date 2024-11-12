package model;

import java.util.ArrayList;

import util.Statistics;

/**
 * Represents an ongoing trial to determine the fairness of a group of dice.
 */
public class Trial {
    private final int amountOfDice;
    private final int sidesOnDie = 6; //TODO option: allow for multiple types of dice beyond d6's through overloaded constructor

    private final int[] savedInput; //state of amount of each side of die rolled.
    private final ArrayList<TrialObserver> observers;

    /**
     * Creates an instance of a dice trial.
     * @param amountOfDice The number of dice to be tested in this trial.
     */
    public Trial(int amountOfDice) {
        this.amountOfDice = amountOfDice;
        this.savedInput = new int[sidesOnDie];
        this.observers = new ArrayList<>();
    }
    
    /**
     * Adds an observer to this instance, to be notified when dice statistics change.
     * @param observer The observer to be notified.
     */
    public void addObserver(TrialObserver observer) {
    	this.observers.add(observer);
    }

    /**
     * Records the thrown results into the state of model.
     * @param throwToSave array with length equalling the number of sides on a die, and the amount of outcomes
     *                    for each side.
     */
    public void saveThrow(int[] throwToSave) {
        for (int i = 0; i < sidesOnDie; i++)
            savedInput[i] = savedInput[i] + throwToSave[i];
        for(TrialObserver observer : observers)
        	observer.notifyObserversStatisticsAreUpdated(Statistics.assembleStatistics(savedInput));
    }

    /**
     * Gets the state of results of all rolled dice
     * @return an array with each side of the die represented by the index (+1).
     */
    public int[] getSavedInput() {
        return savedInput;
    }

    /**
     * Gets the amount dice in this <code>Trial</code>.
     * @return the number of dice in this <code>Trial</code>.
     */
    public int getAmountOfDice() {
        return amountOfDice;
    }

    /**
     * Gets the amount of sides on the dice in this <code>Trial</code>.
     * @return The number of sides of the dice in this <code>Trial</code>.
     */
    public int getSidesOnDie() {
        return sidesOnDie;
    }
}
