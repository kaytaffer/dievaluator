package view;
import controller.Controller;
import java.util.Scanner;

/**
 * A simple view to accept user input and present output in the terminal.
 */
public class View {
    private final Scanner input;
    private final Controller controller;

    /**
     * Creates an instance of the View UI.
     * @param controller a reference to the controller, in case user input needs to be passed to the program.
     *                   //TODO code to allow interrupts
     */
    public View(Controller controller){
        this.controller = controller;
        presentProgram();
        input = new Scanner(System.in);
    }

    /**
     * Asks user how many dice will be rolled each time during the current <code>Trial</code>
     * @return the user defined amount of dice to be rolled each time.
     */
    public int howManyDice() {
        System.out.println("How many dice are you rolling each time? ");
        return Integer.parseInt(input.nextLine());
    }

    /**
     * Presents the user with the basic options available to them and passes on their choice to the <code>Controller</code>.
     */
    public boolean showUserOptions() {
        int optionNumber = 0;
        System.out.println();
        System.out.println("What would you like to do next? \n" +
                ++optionNumber + ". Record a roll. \n" +
                ++optionNumber + ". Present results of the trial. \n" +
                ++optionNumber + ". Start entirely new trial. \n" +
                ++optionNumber + ". Exit.");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                controller.throwDice();
                break;
            case 2:
                //TODO
                break;
            case 3:
                controller.newTrial();
                break;
            case 4:
                return false;
            default:
                System.out.println("That was not a valid option.");
        }
        return true;
    }

    /**
     * Prompts the user for the amount of dice showing a particular result.
     * @param face the side of the dice currently asked for.
     * @return the amount counted and entered by the user.
     */
    public int numberOfFace(int face){
        System.out.println("How many " + face + "'s were rolled? ");
        return input.nextInt();
    }

    /**
     * closes open instances.
     */
    public void shutdown(){
        input.close();
    }

    //presents the usage of the program to the user.
    private void presentProgram() {
        System.out.println("This program asks you to roll an amount (which you decide) of six-sided dice to try " +
                "and determine if they as a group are fairly balanced.\n");
    }
}
