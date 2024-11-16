package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;

/**
 * Top-level container for the Java Swing-enabled view of the Dievaluator application.
 */
public class SwingView extends JFrame {
	
	private static final long serialVersionUID = 2210795984071771884L;
	private final String APPLICATION_TITLE = "Dievaluator";
	private final ImageIcon ICON = new ImageIcon(SwingView.class.getResource("/resources/diecon.png"));
	private final int MARGIN_WIDTH = 10; //pixel
	
	private InfoPanel infoPanel;
	private Controller controller;
	private int numberOfDice;
	
	/**
	 * Creates an instance of a {@code SwingView} and sets up its components.
	 * @param controller The application controller.
	 */
	public SwingView(Controller controller) {
		this.controller = controller;
		
		this.setTitle(APPLICATION_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(ICON.getImage());
		this.setLayout(new BorderLayout(MARGIN_WIDTH, MARGIN_WIDTH));
		this.getContentPane().setBackground(new Color(0xffffff));
		
		OptionPanel optionPanel = new OptionPanel(this);
		this.add(optionPanel, BorderLayout.CENTER);
		this.infoPanel = new InfoPanel();
		this.add(infoPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.pack();
	}
	
	void setTrialNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
		this.infoPanel.setNumberOfDice(numberOfDice);
		this.controller.newTrial(numberOfDice, this.infoPanel);
	}
	
	void inputThrow(int[] newThrow) {
		controller.recordThrow(newThrow);	
	}
	
	int getNumberOfDice() {
		return this.numberOfDice;
	}

}
