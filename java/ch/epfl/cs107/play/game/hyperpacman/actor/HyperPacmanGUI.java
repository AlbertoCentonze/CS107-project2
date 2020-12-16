package ch.epfl.cs107.play.game.hyperpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanStatusGUI;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.window.Canvas;

public class HyperPacmanGUI extends SuperPacmanStatusGUI {
  boolean secondPlayer;
  TextGraphics victoryText;
  Logic win;

  /**
   * Default constructor for HyperPacmanGUI
   * 
   * @param secondPlayer (boolean): true if it's the second player
   * @param win          (Logic): signal that has to be activated collecting the
   *                     key
   */
  protected HyperPacmanGUI(boolean secondPlayer, Logic win) {
    this.secondPlayer = secondPlayer;
    this.win = win;
  }

  @Override
  public void draw(Canvas canvas) {
    float width = canvas.getScaledWidth();
    float height = canvas.getScaledHeight();
    Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width / 2, height / 2));

    float offset = 0f;
    String victory = "";
    if (secondPlayer) {
      offset = height - 1.375f;
      victory = win.isOn() ? "player 1 rules" : "";
    } else {
      offset = 0.5f;
      victory = win.isOn() ? "player 2 is the best" : "";
    }

    victoryText = new TextGraphics(victory, 1.f, Color.YELLOW, Color.BLACK, 0.025f, false, false,
        anchor.add(new Vector(15, offset)));
    scoreGraphics = new TextGraphics("SCORE: " + Integer.toString((int) score), 1.f, Color.YELLOW, Color.BLACK, 0.025f,
        false, false, anchor.add(new Vector(8, offset)));

    for (int i = 0; i < SuperPacmanPlayer.MAX_LIFE; ++i) {
      boolean yellow = i < currentLife ? true : false;
      lifeGraphics[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f,
          new RegionOfInterest(yellow ? 0 : 64, 0, 64, 64), anchor.add(new Vector(i, offset)), 1, DEPTH);
    }
    for (ImageGraphics life : lifeGraphics)
      life.draw(canvas);

    victoryText.draw(canvas);
    scoreGraphics.draw(canvas);
  }
}
