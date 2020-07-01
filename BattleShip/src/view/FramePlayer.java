package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class that contains the JFrame with the sketch to fix the navies on board,
 * Man vs. CPU mode
 * 
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */

public class FramePlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private ProcessingPlayer sketch;

	/**
	 * Creates the Frame
	 */
	public FramePlayer() {
		setTitle("Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 50, 600, 600);
		// this.setSize(600, 600); //Dimensions of the Window
		panel = new JPanel();
		panel.setBounds(0, 0, 501, 521);
		panel.setLayout(null);
		this.sketch = new ProcessingPlayer();
		sketch.setBounds(50, 20, 500, 500);

		panel.add(this.sketch);
		sketch.setLayout(null);

		getContentPane().add(panel);

		add(panel);

	}

	/**
	 * get the sketch of the Frame
	 * 
	 * @return sketch
	 */
	public ProcessingPlayer getSketch() {
		return sketch;
	}

	/**
	 * display a error message
	 * 
	 * @param errorMsg String to show
	 */
	public void displayErrorMessage(String errorMsg) {
		JOptionPane.showMessageDialog(this, errorMsg);
	}
}
