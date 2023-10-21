import java.util.Scanner;

public class View {
    private Scanner input;

    public View(Controller controller){
        Scanner input = new Scanner(System.in);
    }

    public int howManyDice() {
        System.out.println("How many dice are you rolling each time? ");
        return input.nextInt();
    }








}
