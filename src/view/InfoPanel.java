package view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DiceStatisticsDTO;
import model.TrialObserver;

/**
 * Responsible for rendering informative text to the user of the application.
 */
public class InfoPanel extends JPanel implements TrialObserver{
	
	private static final long serialVersionUID = 1L;
	private final String APP_DESCRIPTION = "This application asks you to roll an amount (which you decide) of " +
			"six-sided dice to try and determine if they as a group are fairly balanced.";
	private final Dimension PANEL_SIZE = new Dimension(780, 230);
	private final JLabel infoLabel;
	
	/**
	 * Creates an instance of the {@code InfoPanel} with initial conditions.
	 */
	InfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(PANEL_SIZE);
		this.infoLabel = new JLabel(APP_DESCRIPTION);
		this.add(infoLabel);
	}

	public void setNumberOfDice(int numberOfDice) {
		this.removeAll();
		this.infoLabel.setText("Number of dice in current Trial: " + numberOfDice);
		this.add(infoLabel);
		this.add(new JLabel("Record dice rolls to display their statistics"));
		revalidate();
		repaint();
	}

	@Override
	public void notifyObserversStatisticsAreUpdated(DiceStatisticsDTO statistics) {
		this.removeAll();
		this.add(infoLabel);
        if(statistics == null) {
            return;
        }
        // Add statistics labels to the panel
        int totalDiceRolled = statistics.totalDiceRolled();
        add(new JLabel("----------- Dice Report -----------"));
        add(new JLabel("You've rolled " + totalDiceRolled + " dice in total."));

        double expectedValue = statistics.uniformExpectedValue();
        double arithmeticMean = statistics.arithmeticMean();
        add(new JLabel(String.format("The expected value on the roll of dice of this kind is %.2f", expectedValue)));
        add(new JLabel(String.format("The statistical average of your rolled dice is %.2f.", arithmeticMean)));
        add(new JLabel(String.format("This is a difference of %.2f compared to completely fair dice.", (expectedValue - arithmeticMean))));

        add(new JLabel("The most common roll was " + statistics.mostCommon()));
        add(new JLabel("The least common roll was " + statistics.leastCommon()));

        double uniformDeviation = statistics.uniformDeviation();
        double sampleDeviation = statistics.sampleDeviation();
        boolean swingy = sampleDeviation > uniformDeviation;

        String[] swingyness = new String[2];
        if (swingy) {
            swingyness[0] = "more";
            swingyness[1] = "the extreme highs and lows";
        } else {
            swingyness[0] = "less";
            swingyness[1] = "their statistical average";
        }

        double deviationDifference = Math.abs(uniformDeviation - sampleDeviation);
        add(new JLabel(String.format("The expected standard deviation for a die of this kind is %.2f.", uniformDeviation)));
        add(new JLabel(String.format("The estimated standard deviation based on the samples provided is %.2f.", sampleDeviation)));
        add(new JLabel(String.format("This means the tested dice are %s swingy than a completely fair die.", swingyness[0])));
        add(new JLabel(String.format("They tend towards %s more often with a difference in standard deviation of %.2f.", swingyness[1], deviationDifference)));

        double fairnessConfidence = statistics.confidence();
        add(new JLabel(String.format("The probability that the dice in this trial are fair is approximately %.2f%%.", (fairnessConfidence * 100))));
        
        if(totalDiceRolled <= 100)
        	add(new JLabel("Be aware that since you've rolled only a few dice that this estimate is suspect. Try to roll at least 100 dice in total."));
        
		this.revalidate();
		this.repaint();
	}

}
