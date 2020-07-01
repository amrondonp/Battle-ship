package lan;

import controller.ServerPlayersController;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Match;
import model.Player;

/**
 * This class handles the connection of the players in the game<br>
 * uses default port 8888 uses the Model MVC
 *
 * @version 1.0
 * @author Mauricio Rondon and Julian Pulido
 *
 */
public class ServerPlayers implements Runnable {
  /**
   * Server socket that manage the connection
   */
  private ServerSocket ss;
  /**
   * Socket objetc to conect with the server
   */
  private Socket s;
  /**
   * Controllator of the model MVC
   */
  private ServerPlayersController controller;
  /**
   * ObjectOutputStream to send data
   */
  private ObjectOutputStream oos;
  /**
   * OnbjetInputStream to read datas
   */
  private ObjectInputStream ois;
  /**
   * Match : the match is handle here
   */
  private Match game;

  /**
   * ArrayList<Player> that manage the players
   */
  private ArrayList<Player> players = new ArrayList<Player>();
  /**
   * Number of players in a game
   */
  private int numberOfPlayers = 0;
  /**
   * State to indicate to the server if wait more players or start game
   */
  private int state = 0;
  /**
   * turn actual of the game
   */
  private int turn = 0;
  /**
   * ArrayList<Socket> one socket for each client
   */
  private ArrayList<Socket> sockets;
  /**
   * ArrayList<ObjectInputStream> inputs To recive information of every players
   */
  private ArrayList<ObjectInputStream> inputs = new ArrayList<ObjectInputStream>();
  /**
   * This method acpets the conexion from the client and add new player to
   * arrayList
   */
  private boolean isload = false;

  public void addPlayer() {
    controller.getSpf().getLabel().setText("Esperando M�s jugadores");
    try {
      s = ss.accept();
      sockets.add(s);
      ois = new ObjectInputStream(s.getInputStream());
      inputs.add(ois);
      Object aux;
      aux = ois.readObject();
      if (aux != null && aux instanceof Player) {
        Player player = (Player) aux;
        players.add(player);
        controller
          .getSpf()
          .getLabel()
          .setText("Agregado 1 player, " + player.getName());
      }
      // s.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Send the id of each player
   */
  public void sendId() {
    for (int i = 0; i < numberOfPlayers; i++) {
      try {
        // s = ss.accept();
        oos = new ObjectOutputStream(sockets.get(i).getOutputStream());
        game.getOne().setID(0);
        game.getTwo().setID(1);
        oos.writeObject(i);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Creates a new server and instanciate the arrayList<Socket>
   */
  public ServerPlayers() {
    sockets = new ArrayList<Socket>();
  }

  /**
   * CLose the sever
   */
  public void close() {
    try {
      oos.close();
      ois.close();
      s.close();
      ss.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Show the actual state of game
   */
  private void showMatch() {
    System.out.println("Player 1");
    game.getOne().getField().showField();
    System.out.println("Player 2");
    game.getTwo().getField().showField();
  }

  /**
   * Creates a new match and prepare the server to read the shoots
   */
  private void startMatch() {
    game = new Match(players.get(0), players.get(1), "Nueva Partida");
    showMatch();
    state = 1;
  }

  /**
   * Wait until a number of players is input to the server and run()
   */
  public void startToRecivePlayers() {
    while (numberOfPlayers == 0) {
      controller.getSpf().getLabel1().setText("Ingresa el numero de jugadores");
    }
    try {
      ss = new ServerSocket(8888);
      Thread.sleep(100);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    controller.getSpf().getLabel1().setText("Recibido");
    run();
  }

  /**
   * Recive one shoot and actualizate the game the index is for indicate index of
   * player in arrayList or more specify the ID
   *
   * @param index
   */
  public void reciveShoots(int index) {
    try {
      // s = ss.accept();
      // ois = new ObjectInputStream(sockets.get(index).getInputStream());

      Object aux = inputs.get(index).readObject();
      if (turn == 0 && aux != null && aux instanceof Point && index == 0) {
        Point shoot = (Point) aux;
        boolean s = game.getOne().shoot(shoot, game);
        if (!s) {
          turn = 1;
          game.setTurn(1);
        }
      }
      if (turn == 1 && aux != null && aux instanceof Point && index == 1) {
        Point shoot = (Point) aux;
        boolean s = game.getTwo().shoot(shoot, game);
        if (!s) {
          turn = 0;
          game.setTurn(0);
        }
      }
      showMatch();
      // ois.close();
    } catch (IOException | ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Send the match of each one of clients
   */
  public void sendMatch() {
    for (int i = 0; i < numberOfPlayers; i++) {
      try {
        // s = ss.accept();
        oos = new ObjectOutputStream(sockets.get(i).getOutputStream());
        oos.writeObject(game);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * add players and runs the other threads of the server
   */
  @Override
  public void run() {
    for (int i = 0; i < numberOfPlayers; i++) {
      addPlayer();
      controller
        .getSpf()
        .getLabel1()
        .setText(
          "" +
          players.get(players.size() - 1).getName() +
          " Jugador # " +
          (players.size() - 1)
        );
    }

    controller
      .getSpf()
      .getLabel1()
      .setText("Jugadores listos, iniciando partida");

    startMatch();

    sendId();

    UpdateMatchThread umt = new UpdateMatchThread(this, 0);
    umt.start();

    UpdateMatchThread umt1 = new UpdateMatchThread(this, 1);
    umt1.start();

    UpdateServerThread upt = new UpdateServerThread(this);
    upt.start();
  }

  /**
   * Return the arrayList of player connected
   *
   * @return ArrayList<Player> players
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * set The controller of the model MVC to this server
   *
   * @param ServerPlayersController spc
   */
  public void setController(ServerPlayersController spc) {
    this.controller = spc;
  }

  /**
   * Set the number of Player in a game
   *
   * @param int numberOfPlayers
   */
  public void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  /**
   * return the actual state of server
   *
   * @return int state
   */
  public int getState() {
    return state;
  }

  /**
   * return the output stream
   *
   * @return ObjectOutputStream oos
   */
  public ObjectOutputStream getOos() {
    return oos;
  }

  /**
   * Return the inputSream
   *
   * @return ObjectInputStream ois
   */
  public ObjectInputStream getOis() {
    return ois;
  }

  /**
   * Return the game
   *
   * @return Match game
   */
  public Match getGame() {
    return game;
  }

  /**
   * Return the server socket of this serve
   *
   * @return ServerSocket ss
   */
  public ServerSocket getServerSocket() {
    return this.ss;
  }

  public boolean isIsload() {
    return isload;
  }

  public void setIsload(boolean isload) {
    this.isload = isload;
  }

  public void setMatch(Match game) {
    // TODO Auto-generated method stub
    this.game = game;
  }
}
