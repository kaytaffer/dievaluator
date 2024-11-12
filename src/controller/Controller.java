package controller;
import model.DiceStatisticsDTO;
import model.Trial;
import model.TrialObserver;
import util.DiceLogger;
import util.Statistics;
import view.ConsoleView;

/**
 * This class passes data between the Model and View layers.
 */
public class Controller {

    private Trial trial;

    /**
     * Creates an instance of a Controller
     */
    public Controller(){
    }

    /**
     * Starts a fresh <code>Trial</code> and sets it as the current one.
     * @param numberOfDice The amount of dice thrown each time.
     */
    public void newTrial(int numberOfDice, TrialObserver observer) {
        this.trial = new Trial(numberOfDice);
        this.trial.addObserver(observer);
    }

    /**
     * Tasks the <code>Trial</code> to record the results of a new dice throw.
     * @param newThrow an array of integers representing amounts of dice results. Each index <code>i</code> should
     *                 correspond to the <code>i + 1</code>:th face of the die.
     */
    public void recordThrow(int[] newThrow) {
        trial.saveThrow(newThrow);
    }

    /**
     * Fetches the amount of sides on the dice in the <code>Trial</code>.
     * @return The number of sides of the dice in the <code>Trial</code>.
     */
    public int howManySides() {
        return trial.getSidesOnDie();
    }

    /**
     * Tasks the model to assemble statistics based on previous input.
     * @return a <code>DiceStatisticsDTO</code> containing the resulting mathematical output of a <code>Trial</code>.
     */
    public DiceStatisticsDTO requestResults() {
        int[] savedInput = trial.getSavedInput();
        DiceLogger logger = new DiceLogger();
        logger.logDiceRolls(savedInput);
        logger.closeWriter();
        return Statistics.assembleStatistics(savedInput);
    }
}