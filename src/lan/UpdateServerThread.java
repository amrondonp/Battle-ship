package lan;

public class UpdateServerThread implements Runnable {
  private ServerPlayers sp;
  private Thread thread;

  public UpdateServerThread(ServerPlayers sp) {
    this.sp = sp;
    thread = new Thread(this, "UpdateServer");
  }

  public void start() {
    this.thread.start();
  }

  @Override
  public void run() {
    while (sp.getState() == 1 && !sp.getServerSocket().isClosed()) {
      sp.sendMatch();
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
