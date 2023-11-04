package controller;
import model.Trial;
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
        trial = new Trial(this, view.howManyDice());
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
        trial = new Trial(this, view.howManyDice());
    }

    /**
     * Tasks the <code>Trial</code> to record the results of a new dice throw.
     */
    public void throwDice() {
        int[] newRecordedThrow = trial.recordThrow();
        trial.saveToModel(newRecordedThrow);
    }

    /**
     * Calls the <code>View</code> to prompt the user for the amount of dice showing a particular result.
     * @param face the side of the dice currently asked for.
     * @return the inputted value.
     */
    public int requestAmountOfOutcome(int face) {
        return view.numberOfFace(face);
    }

    /**
     * Orders the view to shut down.
     */
    public void shutdown() {
        view.shutdown();
    }
}