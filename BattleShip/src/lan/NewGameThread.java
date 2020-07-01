package lan;

import controller.ServerPlayersController;
import controller.SimpleClientController;
import view.ServerPlayersFrame;
import view.SimpleClientFrame;

/**
 * Class Thread to server game<br>
 * this class implements runnable and method start creates a new sever
 *
 * @version 1.0
 * @author Mauricio Rondon and Julian Pulido
 *
 */
public class NewGameThread implements Runnable {
  /**
   * Thread to implements runnable
   */
  private Thread thread;

  /**
   * Contructor of the class, create a new Trhread whith this class (Runnable)
   */
  public NewGameThread() {
    thread = new Thread(this, "NewGameThread");
  }

  /**
   * Creates the server
   */
  public static void createPlayerServer() {
    ServerPlayers ss = new ServerPlayers();
    ServerPlayersFrame spf = new ServerPlayersFrame();
    ServerPlayersController spc = ServerPlayersController.getInstance();
    spc.setSp(ss);
    spc.setSpf(spf);
    ss.setController(spc);
    spc.getSpf().setVisible(true);
    spc.getSp().startToRecivePlayers();
  }

  /**
   * Creates the client
   */
  public static void createPlayerClient() {
    SimpleClient sc = new SimpleClient();
    SimpleClientFrame scf = new SimpleClientFrame();
    SimpleClientController scc = SimpleClientController.getInstance();
    scc.setClient(sc);
    scc.setfClient(scf);
    scc.getfClient().setVisible(true);
  }

  /**
   * Start the trhread
   */
  public void start() {
    thread.start();
  }

  /**
   * run and create a sever
   */
  @Override
  public void run() {
    // TODO Auto-generated method stub
    createPlayerServer();
  }
}
