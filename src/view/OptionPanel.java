package view;

import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Responsible for rendering options for the user.
 */
public class OptionPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final int OPTION_PANEL_HEIGHT = 200;
	private final Dimension PANEL_SIZE = new Dimension(480, OPTION_PANEL_HEIGHT);
	private final int NUMBER_OF_DICE_FACES = 6;
	private final String[] FACE_NAMES = {"ones", "twos", "threes", "fours", "fives", "sixes"};
	private final SwingView parentComponent;
	
	private JButton newTrialButton;
	private JButton recordButton;
	private JButton exitButton;
	private JButton submitButton;
	
	private JLabel recordThrowInstructionLabel;
	private JPanel recordThrowClosingInstructionPanel;
	private JPanel diceFaces;
	private JTextField[] textFields;
	
	/**
	 * Creates an instance of the {@code OptionPanel}.
	 */
	OptionPanel(SwingView parent) {
		this.setPreferredSize(PANEL_SIZE);
		this.parentComponent = parent;
		
		buildButtons();
		buildRecordTrialComponents();
		buildDiceFaces();
		
		setMainMenuButtons();
		this.recordButton.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent eventOfButtonClicked) {
		if (eventOfButtonClicked.getSource() == submitButton) {
			int[] recordedThrow = new int[NUMBER_OF_DICE_FACES];
			int totalNumberOfInputs = 0;
			try {
				for(int i = 0; i < NUMBER_OF_DICE_FACES; i++) {
					int numberOfDiceOnThisFace = Integer.parseInt(textFields[i].getText());
					totalNumberOfInputs += numberOfDiceOnThisFace;
					recordedThrow[i] = numberOfDiceOnThisFace;
				}
				if (totalNumberOfInputs == parentComponent.getNumberOfDice()) {
					this.parentComponent.inputThrow(recordedThrow);
					JOptionPane.showMessageDialog(this, "Dice throw recorded.", "Acknowledgement", JOptionPane.PLAIN_MESSAGE);
					setMainMenuButtons();
				} else {
					JOptionPane.showMessageDialog(this, "You have entered an amount of results different from the expected amount of " +
				parentComponent.getNumberOfDice() + " dice.", "User warning", JOptionPane.WARNING_MESSAGE);
				}
			} catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(this, "Only whole numbers of results allowed", "User warning", JOptionPane.WARNING_MESSAGE);
			}
		} else if (eventOfButtonClicked.getSource() == recordButton) {
			recordThrowMenu();
		} else if (eventOfButtonClicked.getSource() == newTrialButton) {
			try {
				this.newTrialButton.setEnabled(false);
				String numberOfDice = JOptionPane.showInputDialog("How many dice are you rolling each time?");
				parentComponent.setTrialNumberOfDice(Integer.parseInt(numberOfDice));
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(this, "Only whole numbers of dice allowed", "User warning", JOptionPane.WARNING_MESSAGE);
			}
			this.newTrialButton.setEnabled(true);
			this.recordButton.setEnabled(true);
		} else if (eventOfButtonClicked.getSource() == exitButton) {
			this.parentComponent.dispose();
		}
	}
	
	private void setMainMenuButtons() {
		this.setLayout(new GridLayout(3,1));
		
		this.removeAll();
		this.add(recordButton);
		this.add(newTrialButton);
		this.add(exitButton);
		this.repaint();
	}

	private void recordThrowMenu() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		
		this.add(recordThrowInstructionLabel, BorderLayout.NORTH);
		this.add(diceFaces, BorderLayout.CENTER);
		this.add(recordThrowClosingInstructionPanel, BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
		
	}
	
	//Set the look of buttons used in this panel
	private void buildButtons() {
		this.recordButton = new JButton("Record dice roll");
		this.recordButton.setFocusable(false);
		this.recordButton.addActionListener(this);
		this.newTrialButton = new JButton("Start new trial");
		this.newTrialButton.setFocusable(false);
		this.newTrialButton.addActionListener(this);
		this.exitButton = new JButton("Quit Dievaluator");
		this.exitButton.setFocusable(false);
		this.exitButton.addActionListener(this);
		this.submitButton = new JButton("Submit rolls");
		this.submitButton.setFocusable(false);
		this.submitButton.addActionListener(this);
		this.submitButton.setPreferredSize(new Dimension(120, 30));
	}
	
	//Set the look and contents of subcomponents used in this panel
	private void buildRecordTrialComponents() {
		this.recordThrowInstructionLabel = new JLabel("Enter how many");
		this.recordThrowInstructionLabel.setHorizontalAlignment(JLabel.CENTER);
		
		this.recordThrowClosingInstructionPanel = new JPanel();
		this.recordThrowClosingInstructionPanel.setLayout(new FlowLayout());
		this.recordThrowClosingInstructionPanel.setPreferredSize(new Dimension(480, 40));
		JLabel closingInstructionLabel = new JLabel("you rolled.");
		this.recordThrowClosingInstructionPanel.add(closingInstructionLabel);
		this.recordThrowClosingInstructionPanel.add(submitButton);
		
	}
	
	private void buildDiceFaces() {
		this.diceFaces = new JPanel();
		diceFaces.setLayout(new GridLayout(6,1));
		diceFaces.setPreferredSize(new Dimension(200, OPTION_PANEL_HEIGHT));
		
		this.textFields = new JTextField[NUMBER_OF_DICE_FACES];
		
		for(int i = 0; i < NUMBER_OF_DICE_FACES; i++) {
			JPanel panel = new JPanel();
			
			JTextField textField = new JTextField(this.FACE_NAMES[i]);
			textField.setPreferredSize(new Dimension(100, (OPTION_PANEL_HEIGHT - 70) / NUMBER_OF_DICE_FACES));
			
			panel.add(textField);
			this.textFields[i] = textField;
			diceFaces.add(panel);
		}
	}
}
