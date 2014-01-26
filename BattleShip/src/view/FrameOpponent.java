package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import processing.core.*;
/**
 * Class that contains the sketch to shoot at the opponent player on mode Man vs. CPU
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */

public class FrameOpponent extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private ProcessingOpponent sketch;
	/**
	 * Creates the Frame
	 */
	public FrameOpponent() {
		setTitle("Opponent");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700,50, 600, 600);
		//this.setSize(600, 600); //Las Dimensiones de la Ventana
		sketch = new ProcessingOpponent();
		sketch.setBounds(50, 20, 500, 500);

		panel = new JPanel();
		panel.setBounds(50, 20, 501, 521);
		panel.setLayout(null);

		panel.add(this.sketch);
		sketch.setLayout(null);

		getContentPane().add(panel);	
	}
	/**
	 * get the sketch of the Frame
	 * @return sketch
	 */
	public ProcessingOpponent getSketch() {
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
