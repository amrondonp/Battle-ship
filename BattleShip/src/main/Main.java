package main;
import controller.Controller;
import controller.SimpleClientController;

import javax.swing.*;


public class Main {
	public static void main(String[] args){

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Controller controller = Controller.getInstance();
			SimpleClientController.getInstance().getClient();
			//controller.getTheView().getFramePlayer().setVisible(true);
			//controller.getTheView().getFrameOpponent().setVisible(true);
			controller.getTheView().getFrameHome().setVisible(true);
		
		
	}
}