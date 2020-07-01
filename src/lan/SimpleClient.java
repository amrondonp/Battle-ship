package lan;

import Util.Utilities;
import controller.Controller;
import controller.SimpleClientController;
import java.awt.Point;
import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;
import model.Match;
import model.Player;
import view.ProcessingManOpponent;
import view.SimpleClientFrame;

public class SimpleClient implements Runnable {
  private Socket s;
  private ObjectOutputStream oos;
  private String name;
  private Match game;
  private ObjectInputStream ois;
  private Thread thread;
  private int id;

  public int getId() {
    return id;
  }

  public SimpleClient() {
    thread = new Thread(this, "Client Trhead");
    SimpleClientController.getInstance().setClient(this);
  }

  public SimpleClient(String mes) {
    this.name = mes;
    thread = new Thread(this, "Client Trhead");
  }

  public void start() {
    thread.start();
  }

  private void readGame() throws ClassNotFoundException, IOException {
    Object aux = ois.readObject();
    if (aux != null && aux instanceof Match) {
      game = (Match) aux;
      Controller.getInstance().setTheModel(game);
    }
  }

  public void update() {
    try {
      ois = new ObjectInputStream(s.getInputStream());
      readGame();
      Controller.getInstance().setTheModel(game);
      if (Utilities.me().getField().checkForWinner()) {
        JOptionPane.showMessageDialog(null, "You Lose");
        System.exit(0);
      }
      if (ProcessingManOpponent.getTheOtherField().checkForWinner()) {
        JOptionPane.showMessageDialog(null, "You win");
        System.exit(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendShoot(Point shoot) {
    try {
      oos.writeObject(shoot);
      oos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendPlayer() {
    try {
      s = new Socket("localHost", 8888);
      Player player = new Player(
        Controller.getInstance().getTheModel().getOne().getField(),
        name
      );

      oos = new ObjectOutputStream(s.getOutputStream());
      oos.writeObject(player);

      System.out.println("jugador enviado");
      SimpleClientFrame.displayMessage("Nuevo jugador enviado al servidor");
      System.out.println("tu esres el jugador ");
    } catch (Exception e) {
      e.printStackTrace();
      SimpleClientFrame.displayMessage(
        "Error al conectar al servidor " + e.toString()
      );
    }
  }

  public int readId() {
    try {
      ois = new ObjectInputStream(s.getInputStream());
      Object aux = ois.readObject();
      if (aux != null && aux instanceof Integer) {
        int id;
        id = (int) aux;
        return id;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public void run() {
    sendPlayer();
    id = readId();
    System.out.println(id);
    Update up = new Update(this);
    up.start();
  }

  public void setName(String name) {
    this.name = name;
  }

  public Match getGame() {
    return this.game;
  }

  class Update implements Runnable {
    SimpleClient sc;
    Thread thread;

    public Update(SimpleClient sc) {
      this.sc = sc;
      this.thread = new Thread(this, "Update");
    }

    public void start() {
      thread.start();
    }

    @Override
    public void run() {
      while (true) {
        sc.update();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public String getName() {
    return name;
  }
}
