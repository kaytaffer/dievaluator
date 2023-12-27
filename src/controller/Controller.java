package controller;
import model.DiceStatisticsDTO;
import model.Trial;
import util.DiceLogger;
import util.Statistics;
import view.View;

/**
 * This class passes data between the Model and View layers.
 */
public class Controller {

    View view;
    private Trial trial;

    /**
     * Creates an instance of a Controller
     */
    public Controller(){
        view = new View(this);
        trial = new Trial(view.howManyDice());
    }

    /**
     * Continually asks <code>View</code> to prompt the user with their options.
     */
    public void runView() {
        boolean viewRunning = true;
        while(viewRunning)
            viewRunning = view.showUserOptions();
    }

    /**
     * Starts a fresh <code>Trial</code> and sets it as the current one.
     */
    public void newTrial() {
        trial = new Trial(view.howManyDice());
    }

    /**
     * Tasks the <code>Trial</code> to record the results of a new dice throw.
     * @param newThrow an array of ints representing amounts of dice results. Each index <code>i</code> should
     *                 correspond to the <code>i + 1</code>:th face of the die.
     */
    public void recordThrow(int[] newThrow) {
        trial.saveThrow(newThrow);
    }

    /**
     * Fetches the amount dice in the <code>Trial</code>.
     * @return the number of dice in the <code>Trial</code>.
     */
    public int howManyDice() {
        return trial.getAmountOfDice();
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
     * @return a <code>StatisticsDTO</code> containing the resulting mathematical output of a <code>Trial</code>.
     */
    public DiceStatisticsDTO requestResults() {
        int[] savedInput = trial.getSavedInput();
        DiceLogger logger = new DiceLogger();
        logger.logDiceRolls(savedInput);
        logger.closeWriter();
        return Statistics.assembleStatistics(savedInput);
    }

    /**
     * Orders the view to shut down.
     */
    public void shutdown() {
        view.shutdown();
    }
}