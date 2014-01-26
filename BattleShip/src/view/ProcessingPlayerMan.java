package view;


import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPopupMenu;

import controller.PopupMenuMan;
import controller.SimpleClientController;
import model.Field;
import processing.core.*;
import Util.Utilities;;
/**
 * Class that contains the sketch to fix the navies on Player board and field on LAN mode
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */
public class ProcessingPlayerMan extends PApplet {

	private static final long serialVersionUID = 1L;

	private static int posX;
	private static int posY;
	XPicture ex;

	private int state=0;
	/**
	 * Get the X coordinate when the mouse is clicked
	 * @return posX coordinate X on the sketch
	 */
	public static int getPosX() {
		return posX;
	}
	/**
	 * Get the Y coordinate when the mouse is clicked
	 * @return posY coordinate Y on the sketch
	 */
	public static int getPosY() {
		return posY;
	}
	/**
	 * initialize the sketch with a grid on board and animations
	 */
	public void setup() {
		size(500, 500);
		background(0,127,255);
		smooth();
		stroke(225);
		for (int i = 0; i <= height; i=i+height/10) {//draw the rows
			strokeWeight(2);
			line(0,i,width,i);
		}
		for (int i = 0; i <= width; i=i+width/10) {//draw the columns
			strokeWeight(2);
			line(i,0,i,height);
		}

		stroke(0);

	}
	/**
	 * Repaint constantly the screen  
	 */
	public void draw() {
		if(state == 1)
		{
			ArrayList <Point> shootedPoints = new ArrayList<Point>();
			frameRate(3);
			shootedPoints.addAll(Utilities.me().getField().getShootedPoints());


			for (Point point : shootedPoints) {
				ex = new XPicture(point.y,point.x);
				ex.display();
			}


		}
	}
	/**
	 * Show a Popup Menu when is mouse left button to fix a type of navy on board
	 */
	public void mousePressed() {
		Field field;


		field = Utilities.me().getField();
		/*if(mouseButton == LEFT){
			Point point = Utilities.getCoordenate(new Point(mouseX,mouseY));

		}*/
		if((mouseButton == RIGHT) && (field.getCountNavy() != 0)){
			Point point = Utilities.getCoordenate(new Point(mouseX,mouseY));//coordinates as a matrix
			posX = (int)point.getX();
			posY = (int)point.getY();
			PopupMenuMan menu = new PopupMenuMan();
			menu.popup.show(ProcessingPlayerMan.this,mouseX,mouseY);//show the popup menu
		}

	}
	/**
	 * fix the state 
	 * @param state
	 */
	public void setState(int state)
	{
		this.state = state;
	}
	/**
	 * Displays the navy on the board 
	 * @param point the point where will be displayed
	 * @param orientation the direction of the navy
	 * @param lenght length of the navy
	 */
	public void displayNavy(Point point,int orientation, int lenght){
		int y =(int) point.getY(); 
		int x = (int) point.getX();


		if(orientation == 8){
			y = y-((lenght-1)/2);
			fill(192,192,192);
			ellipse(((x*50)+25),((y*50)+25),50,lenght*50);
			rectMode(CENTER);

			fill(255);

			rect(((x*50)+25),((y*50)+25),25,lenght*25);
			fill(0);
			rect(((x*50)+25),((y*50)+25),12,12);
		}
		if(orientation == 2){
			y = y+((lenght-1)/2);
			fill(192,192,192);
			ellipse(((x*50)+25),((y*50)+25),50,lenght*50);
			fill(255);
			rectMode(CENTER);

			rect(((x*50)+25),((y*50)+25),25,lenght*25);
			fill(0);
			ellipse(((x*50)+25),((y*50)+25),12,12);
		}
		if(orientation == 4){
			x = x-((lenght-1)/2);
			fill(192,192,192);
			ellipse(((x*50)+25),((y*50)+25),lenght*50,50);
			rectMode(CENTER);

			fill(225);
			rect(((x*50)+25),((y*50)+25),lenght*25,25);
			fill(0);
			rect(((x*50)+25),((y*50)+25),12,12);
		}
		if(orientation == 6){
			x = x+((lenght-1)/2);
			fill(192,192,192);
			ellipse(((x*50)+25),((y*50)+25),lenght*50,50);
			rectMode(CENTER);

			fill(225);
			rect(((x*50)+25),((y*50)+25),lenght*25,25);

			fill(0);
			rect(((x*50)+25),((y*50)+25),12,12);
		}

	}
	/**
	 * Class of a cross figure 
	 * @author Mauricio Rondon
	 * @author Julian Pulido
	 *
	 */
	public class XPicture  {

		int posX;// both [0,9] as a coordinate of a matrix
		int posY;
		/**
		 * The constructor of the Class
		 * @param posX X coordinates where will be printed
		 * @param posY y coordinates where will be printed
		 */
		public XPicture(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
		/**
		 * Display the cross on the sketch
		 */
		public void display(){

			line((this.posX*50),(this.posY*50),((this.posX+1)*50),((this.posY+1)*50));
			line(((this.posX+1)*50),((this.posY)*50),(this.posX*50),((this.posY+1)*50));
		}
		public XPicture() {
			super();
		}


	}


}