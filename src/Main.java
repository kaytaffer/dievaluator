import controller.Controller;

public class Main {
    public static void main(String[] args) {
        //initialize
        Controller controller = new Controller();
        controller.runView();
        //do all the stuff
        controller.shutdown();
    }
}