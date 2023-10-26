package view;
import controller.Controller;

import java.util.Scanner;

/**
 * A simple view to accept user input and present output in the terminal.
 */
public class View {
    private final Scanner input;

    /**
     * Creates an instance of the View UI.
     * @param controller a reference to the controller, in case user input needs to be passed to the program.
     *                   //TODO code to allow interrupts
     */
    public View(Controller controller){
        present();
        input = new Scanner(System.in);
    }

    /**
     * Asks user how many dice will be rolled each time during the current <code>Trial</code>
     * @return the user defined amount of dice to be rolled each time.
     */
    public int howManyDice() {
        System.out.println("How many dice are you rolling each time? ");
        return input.nextInt();
    }

    /**
     * closes open instances.
     */
    public void shutdown(){
        input.close();
    }

    //presents the usage of the program to the user.
    private void present() {
        System.out.println("This program asks you to roll an amount (which you decide on) of six-sided dice to try" +
                "and determine if they as a group are fairly balanced.\n");
    }

}
