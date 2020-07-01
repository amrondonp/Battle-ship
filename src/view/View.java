package view;

/**
 * Class contains all relevant to the View of the program, Class View of the MVC
 * pattern
 *
 * @author Mauricio Rondon
 * @author Julian Pulido
 * @version 1.0
 */
public class View {
  private FramePlayer framePlayer;
  private FrameOpponent frameOpponent;
  private FrameHome frameHome;
  private FrameManOpponent frameManOpponent;
  private FramePlayerMan framePlayerMan;

  /**
   * Get the Frame of Player on Man vs CPU mode
   *
   * @return framePlayer
   */
  public FramePlayer getFramePlayer() {
    return framePlayer;
  }

  /**
   * Set the Frame of Player on Man vs CPU mode
   *
   * @param framePlayer
   */
  public void setFramePlayer(FramePlayer framePlayer) {
    this.framePlayer = framePlayer;
  }

  /**
   * Get the Frame of Opponent on Man vs CPU mode
   *
   * @return frameOpponent
   */
  public FrameOpponent getFrameOpponent() {
    return frameOpponent;
  }

  /**
   * Set the Frame of Opponent on Man vs CPU mode
   *
   * @param frameOpponent
   */
  public void setFrameOpponent(FrameOpponent frameOpponent) {
    this.frameOpponent = frameOpponent;
  }

  /**
   * Constructor with all the Frames
   *
   * @param framePlayer
   * @param frameOpponent
   * @param frameHome
   * @param frameManOpponent
   * @param framePlayerMan
   */
  public View(
    FramePlayer framePlayer,
    FrameOpponent frameOpponent,
    FrameHome frameHome,
    FrameManOpponent frameManOpponent,
    FramePlayerMan framePlayerMan
  ) {
    super();
    this.framePlayer = framePlayer;
    this.frameOpponent = frameOpponent;
    this.frameHome = frameHome;
    this.frameManOpponent = frameManOpponent;
    this.framePlayerMan = framePlayerMan;
  }

  /**
   * Get the Principal Frame
   *
   * @return frameHome
   */
  public FrameHome getFrameHome() {
    return frameHome;
  }

  /**
   * Set the Principal Frame
   *
   * @return frameHome
   */
  public void setFrameHome(FrameHome frameHome) {
    this.frameHome = frameHome;
  }

  /**
   * Get the Frame of Opponent on LAN mode
   *
   * @return frameManOpponent
   */
  public FrameManOpponent getFrameManOpponent() {
    return frameManOpponent;
  }

  /**
   * Set the Frame of Opponent on LAN mode
   *
   * @param frameManOpponent
   */
  public void setFrameManOpponent(FrameManOpponent frameManOpponent) {
    this.frameManOpponent = frameManOpponent;
  }

  /**
   * Get the Frame of Player on LAN mode
   *
   * @return framePlayerMan
   */
  public FramePlayerMan getFramePlayerMan() {
    return framePlayerMan;
  }

  /**
   * Set the Frame of Player on LAN mode
   *
   * @param framePlayerMan
   */
  public void setFramePlayerMan(FramePlayerMan framePlayerMan) {
    this.framePlayerMan = framePlayerMan;
  }
}
