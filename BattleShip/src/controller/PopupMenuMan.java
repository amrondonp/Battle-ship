package controller;

/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly 
*/
// PopupMenuExample.java
// A simple example of JPopupMenu. 
//

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import view.FrameManOpponent;
import view.ProcessingPlayerMan;
import Util.Utilities;
import model.Field;

public class PopupMenuMan extends JPanel {

  public JPopupMenu popup;
  
  public PopupMenuMan() {
    popup = new JPopupMenu();
    popup.add(orientationMenu());
        
  }

  
  public JMenu orientationMenu(){//menu with orientations and contains the navyMenu
	  JMenu m = new JMenu("Orientation");
	  m.add(navyMenu("Up"));
	  m.add(navyMenu("Right"));
	  m.add(navyMenu("Left"));
	  m.add(navyMenu("Down"));
	  return m;
  }


  public JMenu navyMenu(String title){//menu to choose the type of navy
	  JMenu menu = new JMenu(title);
	  JMenuItem tmpItemMenu1 = new JMenuItem("Boat");
	  JMenuItem tmpItemMenu2 = new JMenuItem("Vessel");
	  JMenuItem tmpItemMenu3 = new JMenuItem("Flattop");
	  if(title.equals("Up")){
		  tmpItemMenu1.addActionListener(new NavyUpManListener() );
		  tmpItemMenu2.addActionListener(new NavyUpManListener() );
		  tmpItemMenu3.addActionListener(new NavyUpManListener() );
		  menu.add(tmpItemMenu1);
		  menu.add(tmpItemMenu2);
		  menu.add(tmpItemMenu3);
	  }
	  if(title.equals("Down")){
		  tmpItemMenu1.addActionListener(new NavyDownManListener() );
		  tmpItemMenu2.addActionListener(new NavyDownManListener() );
		  tmpItemMenu3.addActionListener(new NavyDownManListener() );
		  menu.add(tmpItemMenu1);
		  menu.add(tmpItemMenu2);
		  menu.add(tmpItemMenu3);
	  }
	  if(title.equals("Left")){
		  tmpItemMenu1.addActionListener(new NavyLeftManListener() );
		  tmpItemMenu2.addActionListener(new NavyLeftManListener() );
		  tmpItemMenu3.addActionListener(new NavyLeftManListener() );
		  menu.add(tmpItemMenu1);
		  menu.add(tmpItemMenu2);
		  menu.add(tmpItemMenu3);
	  }
	  if(title.equals("Right")){
		  tmpItemMenu1.addActionListener(new NavyRightManListener() );
		  tmpItemMenu2.addActionListener(new NavyRightManListener() );
		  tmpItemMenu3.addActionListener(new NavyRightManListener() );
		  menu.add(tmpItemMenu1);
		  menu.add(tmpItemMenu2);
		  menu.add(tmpItemMenu3);
	  }
			  
	  return menu;

	  
  }
  
}

class NavyUpManListener  implements ActionListener
{
	public void showRemaining(){
		  Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		  
		  Controller.getTheOnlyInstance().getTheView().getFramePlayer().displayErrorMessage("there are left " + field.getCountNavy()+" Navies" );
	  }
	
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			FrameManOpponent opponentManFrame = Controller.getTheOnlyInstance().getTheView().getFrameManOpponent();
			Point point = new Point(ProcessingPlayerMan.getPosX(),ProcessingPlayerMan.getPosY());
			Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
			ProcessingPlayerMan sketch = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan().getSketch();
			
			String str = (arg0.getSource().toString());
			str = str.substring((str.length()-10),str.length()-1);
			StringTokenizer st = new StringTokenizer(str,"=");
			st.nextToken(); 
			String navyType = st.nextToken(); 
			if(navyType.equals("Boat") && field.setNavy(point,8,1) && (field.getCountNavy()>= 1) == true ){
				sketch.displayNavy(point, 8,1);
				field.decreaseCountNavy(1);
				showRemaining();
				
			}
				
			if(navyType.equals("Vessel") && field.setNavy(point,8,3) && (field.getCountNavy()>= 3) == true){
				sketch.displayNavy(point, 8,3);
				field.decreaseCountNavy(3);
				showRemaining();
			}
				
			if(navyType.equals("Flattop") && field.setNavy(point,8,5) && (field.getCountNavy()>= 5) == true){
				sketch.displayNavy(point, 8,5);
				field.decreaseCountNavy(5);
				showRemaining();
			}
			if(field.getCountNavy() == 0 ){
				//opponentManFrame.getSketch().init();
				//SimpleClientController.getInstance().getfClient().setVisible(true);
				//opponentManFrame.setVisible(true);
				Controller.getInstance().getTheView().getFramePlayerMan().getSketch().setState(1);
				
				
				
				
				
			}
				
			
				
		} catch (Exception e) {
			System.err.println("Something was wrong");
			e.printStackTrace();
		}
	}
	
}
class NavyDownManListener  implements ActionListener
{
	public void showRemaining(){
		  Field field = Utilities.me().getField();// Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		  Controller.getTheOnlyInstance().getTheView().getFramePlayer().displayErrorMessage("there are left " + field.getCountNavy()+" Navies" );
	  }
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			FrameManOpponent opponentManFrame = Controller.getTheOnlyInstance().getTheView().getFrameManOpponent();
			Point point = new Point(ProcessingPlayerMan.getPosX(),ProcessingPlayerMan.getPosY());
			Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
			ProcessingPlayerMan sketch = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan().getSketch();
			
			String str = (arg0.getSource().toString());
			str = str.substring((str.length()-10),str.length()-1);
			StringTokenizer st = new StringTokenizer(str,"=");
			st.nextToken(); 
			String navyType = st.nextToken(); 
			if(navyType.equals("Boat") && field.setNavy(point,2,1) && (field.getCountNavy()>= 1) == true ){
				sketch.displayNavy(point, 2,1);
				field.decreaseCountNavy(1);
				showRemaining();
			}
				
			if(navyType.equals("Vessel") && field.setNavy(point,2,3) && (field.getCountNavy()>= 3) == true){
				sketch.displayNavy(point, 2,3);
				field.decreaseCountNavy(3);
				showRemaining();
			}
				
			if(navyType.equals("Flattop") && field.setNavy(point,2,5) && (field.getCountNavy()>= 5) == true){
				sketch.displayNavy(point, 2,5);
				field.decreaseCountNavy(5);
				showRemaining();
			}
			if(field.getCountNavy() == 0 ){
				//opponentManFrame.getSketch().init();
				//SimpleClientController.getInstance().getfClient().setVisible(true);
				Controller.getInstance().getTheView().getFramePlayerMan().getSketch().setState(1);
				//opponentManFrame.setVisible(true);
			}
				
			
				
		} catch (Exception e) {
			System.err.println("Something was wrong");
			e.printStackTrace();
		}
	}
	
}
class NavyLeftManListener  implements ActionListener
{
	public void showRemaining(){
		  Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		  Controller.getTheOnlyInstance().getTheView().getFramePlayer().displayErrorMessage("there are left " + field.getCountNavy()+" Navies" );
	  }
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			FrameManOpponent opponentManFrame = Controller.getTheOnlyInstance().getTheView().getFrameManOpponent();
			Point point = new Point(ProcessingPlayerMan.getPosX(),ProcessingPlayerMan.getPosY());
			Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
			ProcessingPlayerMan sketch = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan().getSketch();
			
			String str = (arg0.getSource().toString());
			str = str.substring((str.length()-10),str.length()-1);
			StringTokenizer st = new StringTokenizer(str,"=");
			st.nextToken(); 
			String navyType = st.nextToken(); 
			if(navyType.equals("Boat") && field.setNavy(point,4,1) && (field.getCountNavy()>= 1) == true ){
				sketch.displayNavy(point, 4,1);
				field.decreaseCountNavy(1);
				showRemaining();
			}
				
			if(navyType.equals("Vessel") && field.setNavy(point,4,3) && (field.getCountNavy()>= 3) == true){
				sketch.displayNavy(point, 4,3);
				field.decreaseCountNavy(3);
				showRemaining();
			}
				
			if(navyType.equals("Flattop") && field.setNavy(point,4,5) && (field.getCountNavy()>= 5) == true){
				sketch.displayNavy(point, 4,5);
				field.decreaseCountNavy(5);
				showRemaining();
			}
			if(field.getCountNavy() == 0 ){
				//opponentManFrame.getSketch().init();
				//SimpleClientController.getInstance().getfClient().setVisible(true);
				Controller.getInstance().getTheView().getFramePlayerMan().getSketch().setState(1);
				//opponentManFrame.setVisible(true);
			}
				
			
				
		} catch (Exception e) {
			System.err.println("Something was wrong");
			e.printStackTrace();
		}
	}
	
}
class NavyRightManListener  implements ActionListener
{
	public void showRemaining(){
		  Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		  Controller.getTheOnlyInstance().getTheView().getFramePlayer().displayErrorMessage("there are left " + field.getCountNavy()+" Navies" );
	  }
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			FrameManOpponent opponentManFrame = Controller.getTheOnlyInstance().getTheView().getFrameManOpponent();
			Point point = new Point(ProcessingPlayerMan.getPosX(),ProcessingPlayerMan.getPosY());
			Field field = Utilities.me().getField();//Controller.getTheOnlyInstance().getTheModel().getOne().getField();
			ProcessingPlayerMan sketch = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan().getSketch();
			System.out.println(sketch);
			System.out.println(point);
			System.out.println(arg0.getSource());
			String str = (arg0.getSource().toString());
			str = str.substring((str.length()-10),str.length()-1);
			StringTokenizer st = new StringTokenizer(str,"=");
			st.nextToken(); 
			String navyType = st.nextToken(); 
			if(navyType.equals("Boat") && field.setNavy(point,6,1) && (field.getCountNavy()>= 1) == true ){
				sketch.displayNavy(point, 6,1);
				field.decreaseCountNavy(1);
				showRemaining();
			}
				
			if(navyType.equals("Vessel") && field.setNavy(point,6,3) && (field.getCountNavy()>= 3) == true){
				sketch.displayNavy(point, 6,3);
				field.decreaseCountNavy(3);
				showRemaining();
			}
				
			if(navyType.equals("Flattop") && field.setNavy(point,6,5) && (field.getCountNavy()>= 5) == true){
				sketch.displayNavy(point, 6,5);
				field.decreaseCountNavy(5);
				showRemaining();
			}
			if(field.getCountNavy() == 0 ){
				//opponentManFrame.getSketch().init();
				//SimpleClientController.getInstance().getfClient().setVisible(true);
				Controller.getInstance().getTheView().getFramePlayerMan().getSketch().setState(1);
				//opponentManFrame.setVisible(true);
			}
				
		
				
		} catch (Exception e) {
			System.err.println("Something was wrong");
			e.printStackTrace();
		}
	}
	
}

