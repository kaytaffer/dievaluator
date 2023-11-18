package view;
import controller.Controller;
import model.DiceStatisticsDTO;

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

        return input.nextInt();
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
            case 1 -> controller.throwDice();
            case 2 -> showResults();
            case 3 -> controller.newTrial();
            case 4 -> {
                return false;
            }
            default -> System.out.println("That was not a valid option.");
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
     * Closes open instances.
     */
    public void shutdown(){
        input.close();
    }

    //presents the usage of the program to the user.
    private void presentProgram() {
        System.out.println("This application asks you to roll an amount (which you decide) of six-sided dice to try " +
                "and determine if they as a group are fairly balanced.\n");
    }

    // Requests Statistics to show and displays it for the user to view.
    private void showResults() {
        DiceStatisticsDTO stats = controller.requestResults();
        if(stats == null) {
            System.out.println("You haven't recorded any rolls yet.");
            return;
        }

        int totalDiceRolled = stats.totalDiceRolled();
        System.out.println("----------- Dice Report -----------");
        System.out.println("You've rolled " + totalDiceRolled + " dice.");
        double expectedValue = stats.uniformExpectedValue(); //for totally fair dice
        double arithmeticMean = stats.arithmeticMean(); //for the evaluated dice
        System.out.printf("The expected value on the roll of these dice is %.2f\n", expectedValue);
        System.out.printf("The statistical average of your rolled dice is %.2f.\n", arithmeticMean);
        System.out.printf("This is a difference of %.2f compared to completely fair dice.\n", (expectedValue - arithmeticMean));
        double uniformDeviation = stats.uniformDeviation();
        double sampleDeviation = stats.sampleDeviation();
        boolean swingy = sampleDeviation > uniformDeviation;
        String[] swingyness = new String[2]; //towards center or edges
        if (swingy) {
            swingyness[0] = "more";
            swingyness[1] = "the extreme highs and lows";
        }
        else {
            swingyness[0] = "less";
            swingyness[1] = "their statistical average";
        }
        double deviationDifference = Math.abs(uniformDeviation - sampleDeviation);
        System.out.printf("The expected standard deviation for a die of this kind is %.2f.\n", uniformDeviation);
        System.out.printf("The estimated standard deviation based on the samples provided is %.2f.\n", sampleDeviation);
        System.out.printf("This means the tested dice are %s swingy than a completely fair die. They tend towards " +
                "%s more often with a difference in standard deviation of %.2f\n", swingyness[0], swingyness[1], deviationDifference);
        Double fairnessConfidence = stats.confidence();
        if (fairnessConfidence == null)
            System.out.println("The likelihood that the dice in this trial are fair is less than 90%. However, " +
                    "it's not clear exactly how fair. Try adding more rolls to get a clearer calculation.");
        else
            System.out.printf("Samples will never be ideal, but according to the trials performed so far \n" +
                "there is a %.0f%% chance the dice in this trial are completely fair.", (fairnessConfidence * 100));
        System.out.println("\nType \"y\" to proceed.");
        input.next();
    }

}
