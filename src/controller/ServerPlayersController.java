package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import lan.ServerPlayers;
import view.ServerPlayersFrame;

public class ServerPlayersController {
  private ServerPlayers sp;
  private ServerPlayersFrame spf;
  public static ServerPlayersController instance = null;

  private ServerPlayersController() {}

  public static synchronized ServerPlayersController getInstance() {
    if (instance == null) {
      instance = new ServerPlayersController();
    }
    return instance;
  }

  public ServerPlayers getSp() {
    return sp;
  }

  public void setSp(ServerPlayers sp) {
    this.sp = sp;
  }

  public ServerPlayersFrame getSpf() {
    return spf;
  }

  public void setSpf(ServerPlayersFrame spf) {
    this.spf = spf;
    spf.addListener1(new Listener1());
  }

  public class Listener1 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      // TODO Auto-generated method stub
      try {
        int aux = Integer.parseInt(spf.getTextField().getText());
        sp.setNumberOfPlayers(aux);
        spf.getLabel().setText("Recibido numero  " + aux);
      } catch (NumberFormatException e) {
        // TODO: handle exception
        JOptionPane.showMessageDialog(spf, "Must in numbers " + e.toString());
      }
    }
  }
}
