package view;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class that contains the  JFrame with the sketch to fix the navies on board, LAN mode    
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */

public class FramePlayerMan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private ProcessingPlayerMan sketch;
	/**
	 * Creates the Frame
	 */
	public FramePlayerMan() {
		setTitle("Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 50, 600, 600);
		//this.setSize(600, 600); //Las Dimensiones de la Ventana
		panel = new JPanel();
		panel.setBounds(0, 0, 501, 521);
		panel.setLayout(null);
		this.sketch = new ProcessingPlayerMan();
		sketch.setBounds(50, 20, 500, 500);

		panel.add(this.sketch);
		sketch.setLayout(null);
		//sketch.init();  //Esto es necesario para iniciar el Sketch
		getContentPane().add(panel);	


		//sketch.init(); //Esto es necesario para iniciar el Sketch
		add(panel);
	}
	/**
	 * get the sketch of the Frame
	 * @return sketch
	 */
	public ProcessingPlayerMan getSketch() {
		return sketch;
	}
	/**
	 * display a error message
	 * @param errorMsg String to show
	 */
	void displayErrorMessage( String errorMsg )
	{
		JOptionPane.showMessageDialog(this, errorMsg);
	}

}
