import controller.Controller;

/**
 * Dievaluator helps you decide if your favorite dice roll fairly.
 */
public class Main {

    /**
     * Bootstraps application
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        //initialize
        Controller controller = new Controller();
        controller.runView();
        //do all the stuff
        controller.shutdown();
    }
}