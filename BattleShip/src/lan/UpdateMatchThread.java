package lan;

public class UpdateMatchThread implements Runnable {

	private ServerPlayers sp;
	private Thread thread;
	private int index;
	
	public UpdateMatchThread(ServerPlayers sp, int index)
	{
		this.index = index;
		this.sp = sp;
		thread = new Thread(this,"UpdateMatch");
	}
	
	public void start()
	{
		this.thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(sp.getState() == 1 && !sp.getServerSocket().isClosed())
		{
			sp.reciveShoots(index);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

}
