package main;
import controller.Controller;
import controller.SimpleClientController;


public class Main {
	public static void main(String[] args){
		
		
			Controller controller = Controller.getInstance();
			SimpleClientController.getInstance().getClient();
			//controller.getTheView().getFramePlayer().setVisible(true);
			//controller.getTheView().getFrameOpponent().setVisible(true);
			controller.getTheView().getFrameHome().setVisible(true);
		
		
	}
}