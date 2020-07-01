package model;

import java.awt.Point;
import java.io.Serializable;

/**
 * Class model of a player of the game battle ship
 *
 * @version 1.0
 * @author Mauricio Rondon and Julian Pulido
 *
 */
public class Player implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -6853205710763896130L;
  /**
   * Field of the player
   */
  private Field field;
  /**
   * Id of the player 0 or 1
   */
  protected int ID;
  /**
   * Name of player
   */
  private String name;
  /**
   * here is te actual information of is or not him turn
   */
  private boolean isMyTurn;

  /**
   * Return the ID of player, 0,1
   *
   * @return int id
   */
  public int getID() {
    return ID;
  }

  /**
   * Set the id of the player
   *
   * @param int iD
   */
  public void setID(int iD) {
    ID = iD;
  }

  /**
   * Constructor of the class, creates a new player whit a Field and and id
   *
   * @param field
   * @param ID
   */
  public Player(Field field, int ID) {
    this.field = field;
    this.ID = ID;
    if (this.ID == 0) isMyTurn = true; else if (this.ID == 1) isMyTurn = false;
  }

  /**
   * This method shoot in a point to the other player in match and return true if
   * have shoot a navy
   *
   * @param Poitn      point
   * @param Mathcmatch
   * @return boolean shootedAcerted
   */
  public boolean shoot(Point point, Match match) {
    if (ID == 0) {
      boolean aux;
      if (match.getTwo().getField().checkNavyAt(point.y, point.x)) aux =
        true; else aux = false;
      match.getTwo().field.setHit(point.y, point.x);
      isMyTurn = aux;
      return aux;
    } else {
      boolean aux;
      if (match.getOne().getField().checkNavyAt(point.y, point.x)) aux =
        true; else aux = false;
      match.getOne().getField().setHit(point.y, point.x);
      isMyTurn = aux;
      return aux;
    }
  }

  /**
   * get the boolean turn
   *
   * @return Boolean turn
   */
  public boolean geyMyturn() {
    return this.isMyTurn;
  }

  /**
   * get the field of the player
   *
   * @return Field field
   */
  public Field getField() {
    return field;
  }

  /**
   * Return the name of player
   *
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of player
   *
   * @param Stirng name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Constructor creates a new player with a field and name
   *
   * @param Field  field
   * @param String name
   */
  public Player(Field field, String name) {
    super();
    this.field = field;
    this.name = name;
  }
}
