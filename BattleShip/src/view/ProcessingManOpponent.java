package view;

import java.awt.Point;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.SimpleClientController;
import Util.Utilities;
import model.Field;
import gifAnimation.Gif;
import processing.core.*;

/**
 * Class that contains the sketch to shoot at the opponent on LAN mode
 * 
 * @author Mauricio Rendon
 * @author Julian Pulido
 *
 */
public class ProcessingManOpponent extends PApplet {

	private static final long serialVersionUID = 1L;
	XPicture eX;
	Gif image;
	private boolean aux = false;
	private Point point;

	/**
	 * initialize the sketch with a grid on board and animations
	 */
	public void setup() {
		size(500, 500);
		background(0, 127, 255);

		stroke(225);
		for (int i = 0; i <= height; i = i + height / 10) {// draw the rows
			strokeWeight(2);
			line(0, i, width, i);
		}

		for (int i = 0; i <= width; i = i + width / 10) {// draw the columns
			strokeWeight(2);
			line(i, 0, i, height);
		}
		smooth();

		image = new Gif(this, "explosion.gif");
	}

	/**
	 * Repaint constantly the screen
	 */
	public void draw() {
		if (aux)
			image(image, point.x * 50, point.y * 50, 50, 50);
	}

	/**
	 * get the respective field of the player
	 * 
	 * @return field the field of Player one or two
	 */
	public Field getField() {
		if (Utilities.isOne())
			return Controller.getTheOnlyInstance().getTheModel().getOne().getField();
		else
			return Controller.getTheOnlyInstance().getTheModel().getTwo().getField();
	}

	/**
	 * get the field of the opponent
	 * 
	 * @return field the field of Player one or two
	 */
	public static Field getTheOtherField() {
		Field aux;
		if (Utilities.isOne())
			aux = Controller.getTheOnlyInstance().getTheModel().getTwo().getField();

		else
			aux = Controller.getTheOnlyInstance().getTheModel().getOne().getField();

		return aux;

	}

	/**
	 * Set a shoot on board and Field when the mouse left button is pressed
	 */
	public void mousePressed() {

		aux = false;
		FramePlayerMan framePlayer = Controller.getTheOnlyInstance().getTheView().getFramePlayerMan();
		FrameManOpponent frameManOpponent = Controller.getTheOnlyInstance().getTheView().getFrameManOpponent();
		if (mouseButton == LEFT) {
			if (Controller.getInstance().getTheModel().getTurn() != Utilities.me().getID()) {
				JOptionPane.showMessageDialog(Controller.getInstance().getTheView().getFrameManOpponent(),
						"No es tu turno");
				return;
			}
			point = Utilities.getCoordenate(new Point(mouseX, mouseY));
			SimpleClientController.getInstance().getClient().sendShoot(point);
			Field fieldMan2 = getTheOtherField();
			fieldMan2.showField();
			if (fieldMan2.checkNavyAt(point.y, point.x)) {// rows, column

				image.jump(0);
				image.play();
				aux = true;

				// fieldMan2.setHit(point.y, point.x);
				// eX = new XPicture((int)point.getX(),(int)point.getY());
				stroke(139, 35, 35);
				// eX.display();//draw a red X (hit)
				if (fieldMan2.checkForWinner()) {
					framePlayer.displayErrorMessage("You are the winner");
					framePlayer.setVisible(false);
					frameManOpponent.setVisible(false);
				}

			} else {
				eX = new XPicture((int) point.getX(), (int) point.getY());
				stroke(0);
				eX.display();
				if (fieldMan2.checkForWinner()) {
					framePlayer.displayErrorMessage("You are the winner");
					framePlayer.setVisible(false);
					frameManOpponent.setVisible(false);
				}
			}

		}
	}

	/**
	 * Class of a cross figure
	 * 
	 * @author Mauricio Rondon
	 * @author Julian Pulido
	 *
	 */
	public class XPicture {

		int posX;// both [0,9] as a coordinate of a matrix
		int posY;

		/**
		 * The constructor of the Class
		 * 
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
		public void display() {

			line((this.posX * 50), (this.posY * 50), ((this.posX + 1) * 50), ((this.posY + 1) * 50));
			line(((this.posX + 1) * 50), ((this.posY) * 50), (this.posX * 50), ((this.posY + 1) * 50));
		}

		/**
		 * Constructor without fields
		 */
		public XPicture() {
			super();
		}
	}
}
