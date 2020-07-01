package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class that contains the sketch to shoot at the opponent player on mode LAN
 * 
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */
public class FrameManOpponent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private ProcessingManOpponent sketch;

	/**
	 * Creates the Frame
	 */
	public FrameManOpponent() {
		setTitle("Opponent");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 50, 600, 600);
		// this.setSize(600, 600); //Dimensions of the Window
		panel = new JPanel();
		panel.setBounds(50, 20, 509, 521);
		panel.setLayout(null);
		this.sketch = new ProcessingManOpponent();
		sketch.setBounds(50, 20, 500, 500);

		panel.add(sketch);
		getContentPane().add(panel);
	}

	/**
	 * get the sketch of the Frame
	 * 
	 * @return sketch
	 */
	public ProcessingManOpponent getSketch() {
		return sketch;
	}

	/**
	 * display a error message
	 * 
	 * @param errorMsg String to show
	 */
	void displayErrorMessage(String errorMsg) {
		JOptionPane.showMessageDialog(this, errorMsg);
	}
}
