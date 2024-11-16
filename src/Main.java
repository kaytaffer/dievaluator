import controller.Controller;
import view.SwingView;

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
        new SwingView(controller);
    }
}