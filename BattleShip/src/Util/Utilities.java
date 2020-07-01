package Util;

import java.awt.Point;
import java.util.ArrayList;

import controller.Controller;
import controller.SimpleClientController;
import model.Field;
import model.Player;

/**
 * Class with useful functions
 * 
 * @author Mauricio Rondon
 * @author Julian Pulido
 */
public class Utilities {

	/**
	 * Check if the Client is the Player one
	 * 
	 * @return boolean
	 */
	public static boolean isOne() {
		if (SimpleClientController.getInstance().getClient().getId() == 0)
			return true;
		else
			return false;
	}

	/**
	 * get the Player object of the Client who call it
	 * 
	 * @return Player
	 */

	public static Player me() {
		if (isOne())
			return Controller.getInstance().getTheModel().getOne();
		else
			return Controller.getInstance().getTheModel().getTwo();
	}

	/**
	 * Check if a number is zero
	 * 
	 * @param int a number to check
	 * @return the number or zero
	 */
	public static int intPart(int a) {

		if (a == 0)
			return 0;
		else
			return a;
	}

	/**
	 * convert a pixels coordinate into board coordinates
	 * 
	 * @param point1 point to convert
	 * @return the point converted
	 */
	public static Point getCoordenate(Point point1) {
		int x = intPart((point1.x) / 50);
		int y = intPart((point1.y) / 50);
		return new Point(x, y);
	}

	/**
	 * Create a ArrayList with all the cells points of the Board
	 * 
	 * @return points ArrayList
	 */

	public static ArrayList<Point> allPointsCpu() {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Point tmp = new Point(i, j);
				points.add(tmp);
			}
		}
		return points;

	}

	/**
	 * create a new random cell on the board
	 * 
	 * @return
	 */
	public static Point instanciatePoint() {
		// System.out.println("x:");
		int x = getRandomInteger(1, 10);
		// System.out.println("y:");
		int y = getRandomInteger(1, 10);
		return new Point(x, y);
	}

	/**
	 * Check if a point has the same component like other one
	 * 
	 * @param a point
	 * @param b point
	 * @return boolean
	 */
	public static boolean isValid(Point a, Point b) {
		if (b.getX() == a.getX() || b.getY() == a.getY())
			return true;
		else
			return false;
	}

	/**
	 * Method that returns true under gives probability from 0 to 1
	 * 
	 * @param probability
	 * @return zero or one, depends of the random
	 */

	public static int getRandomFlag(double probability) {
		if (Math.random() < probability)
			return 1;
		else
			return 0;
	}

	/**
	 * Put all the point of a matrix which are one and put in ArrayList
	 * 
	 * @param field
	 * @return point ArrayList
	 */
	public static ArrayList<Point> getArrayPoint(Field field) {
		ArrayList<Point> points = new ArrayList<Point>();
		int size = field.getSize();
		int[][] matrix = field.getFieldMatrix();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j] == 1) {
					Point tmp = new Point(j, i);
					points.add(tmp);
				}
			}
		}
		return points;
	}

	/**
	 * Method that returns a "pseudo"Random Integer number in the range(min,max)
	 * 
	 * @param max maximum number
	 * @param min minimum number
	 * @return
	 */

	public static Integer getRandomInteger(int max, int min) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	/**
	 * confirm if all the cells of a matrix are zero
	 * 
	 * @param field
	 * @return boolean
	 */
	public static boolean matrixIsCero(Field field) {
		int matrix[][] = field.getFieldMatrix();
		for (int i = 0; i < field.getSize(); i++) {
			for (int j = 0; j < field.getSize(); j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}

		}
		return true;
	}

}
