package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
/**
 * Class of the Frame of the Client to join at a Server, LAN mode
 * @author Mauricio Rendon
 * @author Julian Pulido
 *  @version 1.0
 */
public class SimpleClientFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton btnEnviar = new JButton("Enviar");
	/**
	 * Get the JButton Object of btnEnviar
	 * @return btnEnviar
	 */
	public JButton getBtnEnviar()
	{
		return btnEnviar;
	}
	/**
	 * add an ActionListener to the button btnEnviar
	 * @param listener the ActionListener to add
	 */
	public void addClientListener(ActionListener listener)
	{
		this.btnEnviar.addActionListener(listener);
	}
	/**
	 * Creates the frame
	 */
	public SimpleClientFrame() {
		this.setSize(250, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(101, 57, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(32, 60, 46, 14);
		getContentPane().add(lblNombre);


		//btnEnviar.addActionListener(controller); 
		btnEnviar.setBounds(68, 99, 89, 23);
		getContentPane().add(btnEnviar);

		JLabel lblJugadorNuevo = new JLabel("Jugador Nuevo");
		lblJugadorNuevo.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		lblJugadorNuevo.setBackground(new Color(0, 255, 0));
		lblJugadorNuevo.setForeground(new Color(0, 0, 0));
		lblJugadorNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadorNuevo.setBounds(32, 11, 155, 30);
		getContentPane().add(lblJugadorNuevo);
	}
	/**
	 * Get the JTextField Object of textField
	 * @return textField
	 */
	public JTextField getTextField()
	{
		return this.textField;
	}
	/**
	 * display on screen a Message 
	 * @param errorMsg the String to show
	 */
	public static void displayMessage( String errorMsg )
	{
		JOptionPane.showMessageDialog(null, errorMsg);
	}
}
