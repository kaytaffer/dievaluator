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
     * Starts a fresh <code>Trial</code> and sets it as the current one.
     */
    public void newTrial() {
        trial = new Trial(this, view.howManyDice());
    }

    /**
     * TODO fix a nice definition
     * @param side the side of the dice currently asked for.
     * @return the inputted value.
     */
    public int requestAmountOfOutcome(int side) {
        /* TODO call view and request from user the the amount of 1's, 2's etc shown*/
        return 1;
    }

    /**
     * Orders the view to shut down.
     */
    public void shutdown() {
        view.shutdown();
    }
}