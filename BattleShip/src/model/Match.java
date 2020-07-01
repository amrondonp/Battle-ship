package model;

import java.io.Serializable;

/**
 * Class model of the game batlleShip
 * 
 * @version 1.0
 * @author Mauricio Rondon and Julian Pulido
 *
 */
public class Match implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6715782495954091653L;
	/**
	 * the actual turn in the match
	 */
	private int turn = 0;
	/**
	 * Match identification
	 */
	private String id;
	/**
	 * First Player
	 */
	private Player one;
	/**
	 * Second Player
	 */
	private Player two;

	/**
	 * return true if the player (param) is the first player, false if is the two
	 * player
	 * 
	 * @param player
	 * @return Boolean amIOne
	 */
	public boolean amIone(Player player) {
		if (player == one) {
			return true;
		}
		return false;
	}

	/**
	 * Constructor of the class ,Player that was put first will be the first player
	 * 
	 * @param one
	 * @param two
	 * @param id
	 */
	public Match(Player one, Player two, String id) {
		this.one = one;
		one.ID = 1;
		this.two = two;
		two.ID = 2;
		this.id = id;
	}

	public void Save() {

	}

	/**
	 * Return the firts Playe
	 * 
	 * @return Player one
	 */
	public Player getOne() {
		return one;
	}

	/**
	 * Return the two player
	 * 
	 * @return Player two
	 */
	public Player getTwo() {
		return two;
	}

	/**
	 * return the match id
	 * 
	 * @return String id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set The match id
	 * 
	 * @param String id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get the actual turn
	 * 
	 * @return turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * set the actual turn
	 * 
	 * @param int turn
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

}
