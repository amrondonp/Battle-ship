package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import model.Field;
import view.FrameOpponent;
import view.ProcessingPlayer;

public class PopupMenu extends JPanel {
  private static final long serialVersionUID = -7067173163434944956L;

  public JPopupMenu popup;

  public PopupMenu() {
    popup = new JPopupMenu();
    popup.add(orientationMenu());
  }

  public JMenu orientationMenu() { // menu with orientations and contains the navyMenu
    JMenu m = new JMenu("Orientation");
    m.add(navyMenu("Up"));
    m.add(navyMenu("Right"));
    m.add(navyMenu("Left"));
    m.add(navyMenu("Down"));
    return m;
  }

  public JMenu navyMenu(String title) { // menu to choose the type of navy
    JMenu menu = new JMenu(title);
    JMenuItem tmpItemMenu1 = new JMenuItem("Boat");
    JMenuItem tmpItemMenu2 = new JMenuItem("Vessel");
    JMenuItem tmpItemMenu3 = new JMenuItem("Flattop");
    if (title.equals("Up")) {
      tmpItemMenu1.addActionListener(new NavyUpListener());
      tmpItemMenu2.addActionListener(new NavyUpListener());
      tmpItemMenu3.addActionListener(new NavyUpListener());
      menu.add(tmpItemMenu1);
      menu.add(tmpItemMenu2);
      menu.add(tmpItemMenu3);
    }
    if (title.equals("Down")) {
      tmpItemMenu1.addActionListener(new NavyDownListener());
      tmpItemMenu2.addActionListener(new NavyDownListener());
      tmpItemMenu3.addActionListener(new NavyDownListener());
      menu.add(tmpItemMenu1);
      menu.add(tmpItemMenu2);
      menu.add(tmpItemMenu3);
    }
    if (title.equals("Left")) {
      tmpItemMenu1.addActionListener(new NavyLeftListener());
      tmpItemMenu2.addActionListener(new NavyLeftListener());
      tmpItemMenu3.addActionListener(new NavyLeftListener());
      menu.add(tmpItemMenu1);
      menu.add(tmpItemMenu2);
      menu.add(tmpItemMenu3);
    }
    if (title.equals("Right")) {
      tmpItemMenu1.addActionListener(new NavyRightListener());
      tmpItemMenu2.addActionListener(new NavyRightListener());
      tmpItemMenu3.addActionListener(new NavyRightListener());
      menu.add(tmpItemMenu1);
      menu.add(tmpItemMenu2);
      menu.add(tmpItemMenu3);
    }

    return menu;
  }
}

class NavyUpListener implements ActionListener {

  public void showRemaining() {
    Field field = Controller
      .getTheOnlyInstance()
      .getTheModel()
      .getOne()
      .getField();

    Controller
      .getTheOnlyInstance()
      .getTheView()
      .getFramePlayer()
      .displayErrorMessage(
        "there are left " + field.getCountNavy() + " Navies"
      );
  }

  public void actionPerformed(ActionEvent arg0) {
    try {
      FrameOpponent opponentFrame = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFrameOpponent();
      Point point = new Point(
        ProcessingPlayer.getPosX(),
        ProcessingPlayer.getPosY()
      );
      Field field = Controller
        .getTheOnlyInstance()
        .getTheModel()
        .getOne()
        .getField();
      ProcessingPlayer sketch = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFramePlayer()
        .getSketch();

      String str = (arg0.getSource().toString());
      str = str.substring((str.length() - 10), str.length() - 1);
      StringTokenizer st = new StringTokenizer(str, "=");
      st.nextToken();
      String navyType = st.nextToken();
      if (
        navyType.equals("Boat") &&
        field.setNavy(point, 8, 1) &&
        (field.getCountNavy() >= 1) == true
      ) {
        sketch.displayNavy(point, 8, 1);
        field.decreaseCountNavy(1);
        showRemaining();
      }

      if (
        navyType.equals("Vessel") &&
        field.setNavy(point, 8, 3) &&
        (field.getCountNavy() >= 3) == true
      ) {
        sketch.displayNavy(point, 8, 3);
        field.decreaseCountNavy(3);
        showRemaining();
      }

      if (
        navyType.equals("Flattop") &&
        field.setNavy(point, 8, 5) &&
        (field.getCountNavy() >= 5) == true
      ) {
        sketch.displayNavy(point, 8, 5);
        field.decreaseCountNavy(5);
        showRemaining();
      }

      if (field.getCountNavy() == 0) {
        opponentFrame.getSketch().init();
        opponentFrame.setVisible(true);
        System.out.println("Player 1");
        Controller.getInstance().getTheModel().getOne().getField().showField();
      }
    } catch (Exception e) {
      System.err.println("Something was wrong");
    }
  }
}

class NavyDownListener implements ActionListener {

  public void showRemaining() {
    Field field = Controller
      .getTheOnlyInstance()
      .getTheModel()
      .getOne()
      .getField();
    Controller
      .getTheOnlyInstance()
      .getTheView()
      .getFramePlayer()
      .displayErrorMessage(
        "there are left " + field.getCountNavy() + " Navies"
      );
  }

  public void actionPerformed(ActionEvent arg0) {
    try {
      FrameOpponent opponentFrame = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFrameOpponent();
      Point point = new Point(
        ProcessingPlayer.getPosX(),
        ProcessingPlayer.getPosY()
      );
      Field field = Controller
        .getTheOnlyInstance()
        .getTheModel()
        .getOne()
        .getField();
      ProcessingPlayer sketch = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFramePlayer()
        .getSketch();

      String str = (arg0.getSource().toString());
      str = str.substring((str.length() - 10), str.length() - 1);
      StringTokenizer st = new StringTokenizer(str, "=");
      st.nextToken();
      String navyType = st.nextToken();

      if (
        navyType.equals("Boat") &&
        field.setNavy(point, 2, 1) &&
        (field.getCountNavy() >= 1) == true
      ) {
        sketch.displayNavy(point, 2, 1);
        field.decreaseCountNavy(1);
        showRemaining();
      }

      if (
        navyType.equals("Vessel") &&
        field.setNavy(point, 2, 3) &&
        (field.getCountNavy() >= 3) == true
      ) {
        sketch.displayNavy(point, 2, 3);
        field.decreaseCountNavy(3);
        showRemaining();
      }

      if (
        navyType.equals("Flattop") &&
        field.setNavy(point, 2, 5) &&
        (field.getCountNavy() >= 5) == true
      ) {
        sketch.displayNavy(point, 2, 5);
        field.decreaseCountNavy(5);
        showRemaining();
      }
      if (field.getCountNavy() == 0) {
        opponentFrame.getSketch().init();
        opponentFrame.setVisible(true);
        System.out.println("Player 1");
        Controller.getInstance().getTheModel().getOne().getField().showField();
      }
    } catch (Exception e) {
      System.err.println("Something was wrong");
    }
  }
}

class NavyLeftListener implements ActionListener {

  public void showRemaining() {
    Field field = Controller
      .getTheOnlyInstance()
      .getTheModel()
      .getOne()
      .getField();
    Controller
      .getTheOnlyInstance()
      .getTheView()
      .getFramePlayer()
      .displayErrorMessage(
        "there are left " + field.getCountNavy() + " Navies"
      );
  }

  public void actionPerformed(ActionEvent arg0) {
    try {
      FrameOpponent opponentFrame = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFrameOpponent();
      Point point = new Point(
        ProcessingPlayer.getPosX(),
        ProcessingPlayer.getPosY()
      );
      Field field = Controller
        .getTheOnlyInstance()
        .getTheModel()
        .getOne()
        .getField();
      ProcessingPlayer sketch = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFramePlayer()
        .getSketch();

      String str = (arg0.getSource().toString());
      str = str.substring((str.length() - 10), str.length() - 1);
      StringTokenizer st = new StringTokenizer(str, "=");
      st.nextToken();
      String navyType = st.nextToken();

      if (
        navyType.equals("Boat") &&
        field.setNavy(point, 4, 1) &&
        (field.getCountNavy() >= 1) == true
      ) {
        sketch.displayNavy(point, 4, 1);
        field.decreaseCountNavy(1);
        showRemaining();
      }

      if (
        navyType.equals("Vessel") &&
        field.setNavy(point, 4, 3) &&
        (field.getCountNavy() >= 3) == true
      ) {
        sketch.displayNavy(point, 4, 3);
        field.decreaseCountNavy(3);
        showRemaining();
      }

      if (
        navyType.equals("Flattop") &&
        field.setNavy(point, 4, 5) &&
        (field.getCountNavy() >= 5) == true
      ) {
        sketch.displayNavy(point, 4, 5);
        field.decreaseCountNavy(5);
        showRemaining();
      }

      if (field.getCountNavy() == 0) {
        opponentFrame.getSketch().init();
        opponentFrame.setVisible(true);
        System.out.println("Player 1");
        Controller.getInstance().getTheModel().getOne().getField().showField();
      }
    } catch (Exception e) {
      System.err.println("Something was wrong");
    }
  }
}

class NavyRightListener implements ActionListener {

  public void showRemaining() {
    Field field = Controller
      .getTheOnlyInstance()
      .getTheModel()
      .getOne()
      .getField();
    Controller
      .getTheOnlyInstance()
      .getTheView()
      .getFramePlayer()
      .displayErrorMessage(
        "there are left " + field.getCountNavy() + " Navies"
      );
  }

  public void actionPerformed(ActionEvent arg0) {
    try {
      FrameOpponent opponentFrame = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFrameOpponent();
      Point point = new Point(
        ProcessingPlayer.getPosX(),
        ProcessingPlayer.getPosY()
      );
      Field field = Controller
        .getTheOnlyInstance()
        .getTheModel()
        .getOne()
        .getField();
      ProcessingPlayer sketch = Controller
        .getTheOnlyInstance()
        .getTheView()
        .getFramePlayer()
        .getSketch();

      String str = (arg0.getSource().toString());
      str = str.substring((str.length() - 10), str.length() - 1);
      StringTokenizer st = new StringTokenizer(str, "=");
      st.nextToken();
      String navyType = st.nextToken();

      if (
        navyType.equals("Boat") &&
        field.setNavy(point, 6, 1) &&
        (field.getCountNavy() >= 1) == true
      ) {
        sketch.displayNavy(point, 6, 1);
        field.decreaseCountNavy(1);
        showRemaining();
      }

      if (
        navyType.equals("Vessel") &&
        field.setNavy(point, 6, 3) &&
        (field.getCountNavy() >= 3) == true
      ) {
        sketch.displayNavy(point, 6, 3);
        field.decreaseCountNavy(3);
        showRemaining();
      }

      if (
        navyType.equals("Flattop") &&
        field.setNavy(point, 6, 5) &&
        (field.getCountNavy() >= 5) == true
      ) {
        sketch.displayNavy(point, 6, 5);
        field.decreaseCountNavy(5);
        showRemaining();
      }

      if (field.getCountNavy() == 0) {
        opponentFrame.getSketch().init();
        opponentFrame.setVisible(true);
        System.out.println("Player 1");
        Controller.getInstance().getTheModel().getOne().getField().showField();
      }
    } catch (Exception e) {
      System.err.println("Something was wrong");
    }
  }
}
