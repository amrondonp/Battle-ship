package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.FrameHome;
import view.FrameManOpponent;
import view.FrameOpponent;
import view.FramePlayer;
import view.FramePlayerMan;
import view.View;
import lan.NewGameThread;
import model.Field;
import model.Match;
import model.Player;

public class Controller {
	private View theView;
	private Match theModel;
	private static Controller theOnlyInstance;

	public Controller(View theView, Match theModel) {
		super();
		this.theView = theView;
		this.theModel = theModel;
		this.theView.getFrameHome().addButtonPcListener(new ButtonPCListener());
		this.theView.getFrameHome().addButtonNewGListener(new ButtonNewGListener());
		this.theView.getFrameHome().addButtonJoinListener(new ButtonJoinListener());
	}

	public static synchronized Controller getInstance() {
		if (theOnlyInstance == null) {
			FramePlayer framePlayer = new FramePlayer();
			FrameOpponent frameOpponent = new FrameOpponent();
			FrameHome frameHome = new FrameHome();
			FrameManOpponent framManOpponent = new FrameManOpponent();
			FramePlayerMan framePlayerMan = new FramePlayerMan();
			Match model = new Match(new Player((new Field()), 1), new Player((new Field()), 2), "255");
			View view = new View(framePlayer, frameOpponent, frameHome, framManOpponent, framePlayerMan);
			theOnlyInstance = new Controller(view, model);
		}
		return theOnlyInstance;
	}

	public View getTheView() {
		return theView;
	}

	public void setTheView(View theView) {
		this.theView = theView;
	}

	public Match getTheModel() {
		return theModel;
	}

	public void setTheModel(Match theModel) {
		this.theModel = theModel;
	}

	public static Controller getTheOnlyInstance() {
		return theOnlyInstance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}

	class ButtonPCListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Field fieldPlayer = Controller.getTheOnlyInstance().getTheModel().getOne().getField();
				Field fieldOpponent = Controller.getTheOnlyInstance().getTheModel().getTwo().getField();
				int r = 6;// how many boats can you put
				getTheView().getFramePlayer()
						.displayErrorMessage("Set your Navies\n" + "You can use " + r + " squares");
				getTheView().getFramePlayer().getSketch().init();
				getTheView().getFramePlayer().setVisible(true);
				fieldPlayer.setCountNavy(r);
				fieldOpponent.fillField(r);
				System.out.println("Opponent");
				fieldOpponent.showField();
				System.out.println("##############################");
			} catch (Exception ex) {
				System.err.println("Something was wrong 2");
				ex.printStackTrace();
			}
		}
	}

	class ButtonNewGListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				NewGameThread newGameThread = new NewGameThread();
				newGameThread.start();

			} catch (Exception e) {
			}
		}
	}

	class ButtonJoinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Controller controller = Controller.getInstance();

				Field fieldPlayer = Controller.getTheOnlyInstance().getTheModel().getOne().getField();

				int r = 6;// how many boats can you put

				fieldPlayer.setCountNavy(r);

				NewGameThread.createPlayerClient();
				controller.getTheView().getFramePlayerMan().getSketch().init();
				controller.getTheView().getFramePlayerMan().setVisible(true);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
