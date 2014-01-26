package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lan.SimpleClient;
import model.Field;
import view.FrameManOpponent;
import view.FrameOpponent;
import view.FramePlayerMan;
import view.SimpleClientFrame;

public class SimpleClientController {

	private SimpleClient client;
	private SimpleClientFrame fClient;
	private static SimpleClientController scc;
	
	public static synchronized SimpleClientController getInstance()
	{
		if(scc == null)
		{
			scc = new SimpleClientController();
		}
		return scc;
	}
	
	public SimpleClientController() {
		// TODO Auto-generated constructor stub

	}

	public SimpleClient getClient() {
		return client;
	}
	public void setClient(SimpleClient client) {
		this.client = client;
		
	}
	public SimpleClientFrame getfClient() {
		return fClient;
		
	}
	public void setfClient(SimpleClientFrame fClient) {
		this.fClient = fClient;
		fClient.addClientListener(new MyListener());
	}

	
	
	class MyListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		
		
		String name = fClient.getTextField().getText();
		fClient.getTextField().setText("");
		client.setName(name);
		Controller.getInstance().getTheView().getFrameManOpponent().setVisible(true);
		Controller.getInstance().getTheView().getFrameManOpponent().getSketch().init();
	
		client.start();	
		fClient.setVisible(false);
		}
	}

}
