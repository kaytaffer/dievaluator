package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class for logging arrays of ints to a file.
 */
public class DiceLogger {

    private FileWriter writer;

    /**
     * Creates an instance of the <code>DiceLogger</code>.
     */
    public DiceLogger(){
        try {
            writer = new FileWriter("diceLog.txt", true);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    /**
     * Writes an int-array to the log formatted in three ways.
     * First, each index + 1 is written as many times as defined in the array[index].
     * Second, each element is written in order of index.
     * Third, the sum of all elements is written.
     * @param savedInput the array to write to the log
     */
    public void logDiceRolls(int[] savedInput){
        int totalDiceRolled = 0;
        try {
            writer.append("\n\nSaved sample: [");
            for (int i = 0; i < savedInput.length; i++) {
                for(int j = 0; j < savedInput[i]; j++) {
                    if(i == (savedInput.length - 1) && j == (savedInput[i] - 1)) //last entry
                        writer.append(String.valueOf(i + 1)).append("]");
                    else
                        writer.append(String.valueOf(i + 1)).append(", ");
                }
            totalDiceRolled += savedInput[i];
            }
            writer.append("\nSample grouped by side: ").append(Arrays.toString(savedInput))
                    .append("\nTotal dice rolled: ").append(String.valueOf(totalDiceRolled));
            writer.flush();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    /**
     * Closes the active <code>FileWriter</code>.
     */
    public void closeWriter() {
        try {
            writer.append("\n");
            writer.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
