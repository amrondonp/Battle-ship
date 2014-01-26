package view;


import java.awt.Point;

import javax.swing.JPopupMenu;

import controller.Controller;
import controller.PopupMenu;
import model.Field;
import Util.Utilities;
import gifAnimation.Gif;
import processing.core.*;

/**
 * Class that contains the sketch to fix the navies on Player board and field on Man vs CPU
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */

public class ProcessingPlayer extends PApplet {

	private static final long serialVersionUID = 1L;

	private static int posX;
	private static int posY;

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

	private boolean aux;
	private Gif image;
	private Point point;
	/**
	 * initialize the sketch with a grid on board and animations
	 */
	public void setup() {
		size(500, 500);
		background(0,127,255);
		smooth();
		stroke(255);
		for (int i = 0; i <= height; i=i+height/10) {//draw the rows
			strokeWeight(2);
			line(0,i,width,i);
		}
		for (int i = 0; i <= width; i=i+width/10) {//draw the columns
			strokeWeight(2);
			line(i,0,i,height);
		}
		image = new Gif(this,"explosion.gif");
		stroke(0);

	}
	/**
	 * Repaint constantly the screen  
	 */
	public void draw() {
		if(aux)
			image(image,point.x*50,point.y*50,50,50);
	}
	/**
	 * Show a Popup Menu when is mouse left button to fix a type of navy on board
	 */
	public void mousePressed() {
		Field field = Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		if(mouseButton == LEFT){
			Point point = Utilities.getCoordenate(new Point(mouseX,mouseY));

		}
		else if((mouseButton == RIGHT) && (field.getCountNavy() != 0)){
			Point point = Utilities.getCoordenate(new Point(mouseX,mouseY));//coordinates as a matrix
			posX = (int)point.getX();
			posY = (int)point.getY();
			PopupMenu menu = new PopupMenu();
			menu.popup.show(ProcessingPlayer.this,mouseX,mouseY);//show the popup menu
		}

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
	 * Print a explosion when the opponent hits on a navy
	 * @param point  point where will be showed the animation 
	 */
	public void printExplosion(Point point) {
		// TODO Auto-generated method stub
		aux = true;
		this.point = point;//new Point(point.y,point.x);
		image.jump(0);
		image.play();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
