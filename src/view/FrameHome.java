package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Class that contains the JFrame with the buttons of game modes
 *
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */

public class FrameHome extends JFrame {
  private static final long serialVersionUID = -4659657335097876554L;

  private JPanel contentPane;
  JButton buttonPC;
  JButton buttonNewG;
  JButton buttonLoad;
  JButton buttonJoin;

  /**
   * Create the Home Frame.
   */
  public FrameHome() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 516, 385);

    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    contentPane.setBounds(0, 0, this.getHeight(), this.getWidth());

    JLabel lblWellcomeToBattleship = new JLabel("Wellcome to Battleship Game");
    lblWellcomeToBattleship.setBounds(5, 5, 504, 36);
    lblWellcomeToBattleship.setHorizontalAlignment(SwingConstants.CENTER);
    lblWellcomeToBattleship.setFont(new Font("Dialog", Font.BOLD, 30));
    contentPane.add(lblWellcomeToBattleship);

    JLabel lblWhatDoYou = new JLabel("What do you want to do?");
    lblWhatDoYou.setFont(new Font("Dialog", Font.BOLD, 20));
    lblWhatDoYou.setBounds(98, 53, 353, 46);
    contentPane.add(lblWhatDoYou);

    JLabel lblMenVsPc = new JLabel("Men VS CPU");
    lblMenVsPc.setFont(new Font("Dialog", Font.BOLD, 16));
    lblMenVsPc.setBounds(87, 126, 145, 24);
    contentPane.add(lblMenVsPc);

    JLabel lblCreateANew = new JLabel("Start a new Game ");
    lblCreateANew.setFont(new Font("Dialog", Font.BOLD, 16));
    lblCreateANew.setBounds(87, 172, 214, 24);
    contentPane.add(lblCreateANew);

    JLabel lblLoadAGame = new JLabel("Load a Game");
    lblLoadAGame.setFont(new Font("Dialog", Font.BOLD, 16));
    lblLoadAGame.setBounds(87, 221, 145, 24);
    contentPane.add(lblLoadAGame);

    JLabel lblJoinInA = new JLabel("Join in a Game");
    lblJoinInA.setFont(new Font("Dialog", Font.BOLD, 16));
    lblJoinInA.setBounds(87, 270, 145, 24);
    contentPane.add(lblJoinInA);

    buttonPC = new JButton("MEN VS CPU");
    buttonPC.setBounds(334, 127, 127, 23);
    contentPane.add(buttonPC);

    buttonNewG = new JButton("NEW GAME");
    buttonNewG.setBounds(334, 173, 127, 24);
    contentPane.add(buttonNewG);

    buttonLoad = new JButton("LOAD GAME");
    buttonLoad.setBounds(334, 222, 127, 23);
    contentPane.add(buttonLoad);

    buttonJoin = new JButton("JOIN GAME");
    buttonJoin.setBounds(334, 271, 127, 23);
    contentPane.add(buttonJoin);
  }

  /**
   * Add an ActionListener to the button buttonPC
   *
   * @param listener ActionListener
   */
  public void addButtonPcListener(ActionListener listener) {
    this.buttonPC.addActionListener(listener);
  }

  /**
   * Add an ActionListener to the button buttonNewG
   *
   * @param listener ActionListener
   */
  public void addButtonNewGListener(ActionListener listener) {
    this.buttonNewG.addActionListener(listener);
  }

  /**
   * Add an ActionListener to the button buttonLoad
   *
   * @param listener ActionListener
   */
  public void adButtonLoadListener(ActionListener listener) {
    this.buttonLoad.addActionListener(listener);
  }

  /**
   * Add an ActionListener to the button buttonJoin
   *
   * @param listener ActionListener
   */
  public void addButtonJoinListener(ActionListener listener) {
    this.buttonJoin.addActionListener(listener);
  }
}
