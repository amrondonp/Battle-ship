package view;
import java.awt.Point;
import java.util.ArrayList;

import controller.Controller;
import model.Field;
import gifAnimation.Gif;
import Util.Utilities;
import processing.core.*;
/**
 * Class that contains the sketch to shoot  at the opponent on Man vs CPU mode 
 * @author Mauricio Rendon
 * @author Julian Pulido
 *
 */

public class ProcessingOpponent extends PApplet {

	private static final long serialVersionUID = 1L;
	XPicture eX  ;
	Gif image;
	private ArrayList<Point> pointsCpu;
	private Point point = new Point(-50,-50);
	private boolean aux;
	/**
	 * initialize the sketch with a grid on board and animations
	 */
	public void setup() {
		pointsCpu = Utilities.allPointsCpu();
		size(500, 500);
		background(0,127,255);

		stroke(225);
		for (int i = 0; i <= height; i=i+height/10) {//draw the rows
			strokeWeight(2);
			line(0,i,width,i);
		}

		for (int i = 0; i <= width; i=i+width/10) {//draw the columns
			strokeWeight(2);
			line(i,0,i,height);
		}
		smooth();
		image = new Gif(this,"explosion.gif");
	}
	/**
	 * Repaint constantly the screen  
	 */
	public void draw() {
		if(aux)
			image(image,point.x*50,point.y*50,50,50);
	}
	/**
	 * Set a shoot on board and Field when the mouse left button is pressed and plays the CPU
	 */
	public void mousePressed() {


		aux = false;
		Field fieldCPU = Controller.getTheOnlyInstance().getTheModel().getTwo().getField();
		Field fieldMan = Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		ProcessingPlayer processingPlayer = Controller.getTheOnlyInstance().getTheView().getFramePlayer().getSketch();
		FramePlayerMan framePlayer = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan();
		FrameOpponent frameOpponent = Controller.getTheOnlyInstance().getTheView().getFrameOpponent();
		if(mouseButton == LEFT){
			point = Utilities.getCoordenate(new Point(mouseX,mouseY));
			//System.out.println(point.toString());

			if(fieldCPU.checkNavyAt(point.y, point.x)){// rows, column
				image.jump(0);
				image.play();
				aux = true;
				fieldCPU.setHit(point.y, point.x);

				//eX = new XPicture((int)point.getX(),(int)point.getY());
				stroke(139,35,35);
				//eX.display();//draw a red X (hit)
				if(fieldCPU.checkForWinner()){
					framePlayer.displayErrorMessage("You are the winner");
					framePlayer.setVisible(false);
					frameOpponent.setVisible(false);
				}
				//aux = false;
				return;
			}
			else{
				eX = new XPicture((int)point.getX(),(int)point.getY());
				stroke(0);
				eX.display();
			}
			boolean turn=true;
			//here the CPU plays
			do{
				int index = Utilities.getRandomInteger(0, pointsCpu.size()-1);
				turn = fieldMan.setHit(pointsCpu.get(index).y, pointsCpu.get(index).x);

				System.out.println(pointsCpu.get(index).toString());
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(turn){
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					processingPlayer.printExplosion(pointsCpu.get(index));
				}
				if(!turn){
					int x = (int)pointsCpu.get(index).x;
					int y = (int)pointsCpu.get(index).y;
					processingPlayer.line((x*50),(y*50),((x+1)*50),((y+1)*50));
					processingPlayer.line(((x+1)*50),((y)*50),(x*50),((y+1)*50));
				}
				pointsCpu.remove(index);
				if(fieldMan.checkForWinner()){
					framePlayer.displayErrorMessage("You are the looser");
					framePlayer.setVisible(false);
					frameOpponent.setVisible(false);
				}

			}while(turn);

			/////////////////////////
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
		/**
		 * Constructor without fields
		 */
		public XPicture() {
			super();
		}


	}

}
