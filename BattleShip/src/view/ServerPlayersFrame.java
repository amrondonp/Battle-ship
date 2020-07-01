package view;

import Util.Serializator;
import controller.ServerPlayersController;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Match;

/**
 * Class of the Frame to start a new Server, LAN mode
 *
 * @author Mauricio Rendon
 * @author Julian Pulido
 * @version 1.0
 */
public class ServerPlayersFrame extends JFrame {
  private JLabel label = new JLabel("default");
  ServerPlayersController controller;
  private JLabel label1 = new JLabel("default");
  private JButton btnEnviar = new JButton("Enviar");

  /**
   * get the Label Object of label
   *
   * @return label
   */
  public JLabel getLabel() {
    return label;
  }

  /**
   * get the Label Object of label1
   *
   * @return label1
   */
  public JLabel getLabel1() {
    return label1;
  }

  /**
   * Create the Frame
   */
  public ServerPlayersFrame() {
    super("Jugadores");
    getContentPane().setLayout(null);
    setSize(300, 300);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setToolTipText("Default\r\n");
    label.setBounds(10, 46, 264, 24);
    getContentPane().add(label);
    label1.setHorizontalAlignment(SwingConstants.CENTER);
    label1.setBackground(SystemColor.windowBorder);
    label1.setBounds(10, 11, 264, 24);
    getContentPane().add(label1);

    JLabel lblNumeroDeJugadores = new JLabel("Numero de Jugadores");
    lblNumeroDeJugadores.setBounds(42, 82, 113, 33);
    getContentPane().add(lblNumeroDeJugadores);

    textField = new JTextField();
    textField.setBounds(176, 92, 58, 20);
    getContentPane().add(textField);
    textField.setColumns(10);
    btnEnviar.setBounds(102, 134, 89, 23);
    getContentPane().add(btnEnviar);

    JButton btnSave = new JButton("Save");
    btnSave.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          Match game = ServerPlayersController.getInstance().getSp().getGame();
          Serializator s = new Serializator();
          s.writeObject(game, "game.txt");
          JOptionPane.showMessageDialog(null, "Guerdado correctamente");
        }
      }
    );
    btnSave.setBounds(42, 192, 89, 23);
    getContentPane().add(btnSave);

    JButton btnLoad = new JButton("Load");
    btnLoad.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          // NewGameThread.createPlayerServer();
          Serializator s = new Serializator();
          Match game = (Match) s.readObject("game.txt");
          ServerPlayersController.getInstance().getSp().setIsload(true);
          ServerPlayersController.getInstance().getSp().setMatch(game);
          JOptionPane.showMessageDialog(null, "Cargado correctamente");
        }
      }
    );
    btnLoad.setBounds(165, 192, 89, 23);
    getContentPane().add(btnLoad);
  }

  private static final long serialVersionUID = -3580886264478772403L;
  private JTextField textField;

  /**
   * add a ActionListener to the button btnEnviar
   *
   * @param listener the ActionListener to add
   */
  public void addListener1(ActionListener listener) {
    this.btnEnviar.addActionListener(listener);
  }

  /**
   * Get the JTextField Object of the Frame
   *
   * @return textField
   */
  public JTextField getTextField() {
    // TODO Auto-generated method stub
    return textField;
  }
}
