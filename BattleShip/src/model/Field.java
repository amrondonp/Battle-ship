package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import Util.Utilities;

/**
 * Field Class to handle the game field
 * 
 * @version 1.0
 * @author Mauricio Rondon and Julian Pulido
 *
 */
public class Field implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6809204297414186445L;
	/**
	 * This field by default is 10 has the size of the playing field
	 */
	private static int size = 10;
	/**
	 * This field stores a matrix of size defined size
	 */
	private  int [][] fieldMatrix = new int [size][size];
	/**
	 * Initially has the number of position to be used by navies
	 */
	private int countNavy;
	
	/**
	 * this method return the points that has been shooted positions in  the matrix that contains "2"
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getShootedPoints()
	{
		ArrayList<Point> shootedPoints = new ArrayList<Point>();
		for(int i =0; i<size; i++){
			for(int j = 0; j<size; j++ ){
				if(fieldMatrix[i][j]==2)
				{
					Point aux = new Point(i,j);
					shootedPoints.add(aux);
				}
					
			}
		}
		return shootedPoints;
	}
	 
	/**
	 * Returns the countNavy of the field
	 * @return Integer countNavy
	 */
	public int getCountNavy() {
		return countNavy;
	}
	
	/**
	 * This method is use to set the countNavy of the field
	 * @param countNavy
	 */
	public void setCountNavy(int countNavy) {
		this.countNavy = countNavy;
	}
	/**
	 * decreases the count navy in a couuntNavy parameter
	 * @param int countNavy
	 */
	public void decreaseCountNavy(int countNavy) {
		this.countNavy = this.countNavy - countNavy;
	}
	
	/**
	 * Constructor of the class
	 */
	public Field(){}
	
	/**
	 * Return the size of field
	 * @return int size
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * return the matrix of the field 
	 * @return int[][] fieldMatrix
	 */
	public int[][] getFieldMatrix() {
		return fieldMatrix;
	}
	/**
	 * returns the value of the field matrix in a row, col
	 * @param int rows
	 * @param int columns
	 * @return int value
	 */
	public int getMatrixAt(int rows,int columns){
		return fieldMatrix[rows][columns];
	}
	/**
	 * return true if in the field matrix are a Navy false if not
	 * @param int rows
	 * @param int columns
	 * @return Boolean isNavyThere
	 */
	public Boolean checkNavyAt(int rows,int columns){
		if(fieldMatrix[rows][columns] == 1){
			return true;
		}
		return false;
	}
	/**
	 * return true if in this field no navys left (lose game)
	 * @return boolean thereAreWinner
	 */
	public Boolean checkForWinner(){
		for(int i =0; i<size; i++){
			for(int j = 0; j<size; j++ ){
				if((this.fieldMatrix[i][j] == 1))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * set a '1' value (Navy) in the specified position
	 * 	 * @param rows
	 * @param columns
	 */
	public  void setPointAtMatrix(int rows,int columns){
		this.fieldMatrix[rows][columns] = 1;//navy
	}
	/**
	 * set a '2' value in the matrix in that position had '1' value return true, false in other case
	 * @param int rows
	 * @param int columns
	 * @return Boolean hasShootedToANavy
	 */
	public boolean setHit(int rows, int columns){
		boolean ret;
		if(checkNavyAt(rows, columns))
			ret = true;
		else
			ret = false;
		this.fieldMatrix[rows][columns] = 2;
		return ret;
	}
	/**
	 * this method set a '2' value in a random position
	 * @return Point pointOfShoot
	 */
	public Point setRandomHit(){
		int row = Utilities.getRandomInteger(9, 0);
		int column = Utilities.getRandomInteger(9, 0);
		this.fieldMatrix[row][column] = 2;
		return (new Point(column,row));//x,y
	}
	/**
	 * This method shows in console the matrix of this field
	 */
	public void showField(){
		for(int i =0; i<size; i++){
			for(int j = 0; j<size; j++ ){
				System.out.print(fieldMatrix[i][j]);
			}
		System.out.println();
		}
	}
	/**
	 * This method set '1' in n positions random on matrix
	 * @param int n
	 */
	public void fillField(int n){// set n boats in field randomly
		int row;
		int column;
		while(n != 0){
			 row = Utilities.getRandomInteger(9, 0);
			 column = Utilities.getRandomInteger(9, 0);
			 if(!checkNavyAt(row,column))//if there is not a navy already
				 setPointAtMatrix(row,column);
				 n--;
		}
		
		
	}

	/**
	 * This method positions a ship in a Point, with an orientation and a size, returns true if it was placed, false if it failed to place
	 * @param Point initialPos
	 * @param int dir
	 * @param int lenght
	 * @return Boolean navyHasSet
	 */
	public  Boolean setNavy(Point initialPos, int dir,int lenght){//up 8, left 4,right 6, down 2, none 0
		int row = (int) initialPos.getY();
		int column = (int) initialPos.getX();
		int cnt = lenght;
		while(cnt != 0){// look for a wrong position
			if (column>9 || column<0 || row>9 || row<0)// check the bounds of the field
				return false;
			if (getMatrixAt(row,column) == 1)// check for a navy in that position
				return false;
			if (dir == 8)
				row--;
			if (dir == 2)
				row++;
			if (dir == 4)
				column--;
			if (dir == 6)
				column++;
			cnt --;
		}
		row = (int) initialPos.getY();
		column = (int) initialPos.getX();
		while(lenght != 0){// set the navy on field
			setPointAtMatrix(row,column);//first rows then columns
			if (dir == 8)
				row--;
			if (dir == 2)
				row++;
			if (dir == 4)
				column--;
			if (dir == 6)
				column++;
			lenght --;
		}
		return true;
		
			
	}

}
