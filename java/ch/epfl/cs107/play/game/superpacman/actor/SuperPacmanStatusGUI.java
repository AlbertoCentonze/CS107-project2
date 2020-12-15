package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SuperPacmanStatusGUI implements Graphics {
  ImageGraphics[] lifeGraphics = new ImageGraphics[5];
  TextGraphics scoreGraphics;
  private final static int DEPTH = 0;

  private int currentLife;
  private int score;

  protected SuperPacmanStatusGUI() {
  };

  public void setGUI(int currentLife, int score) {
    this.currentLife = currentLife;
    this.score = score;
  }

  @Override
  public void draw(Canvas canvas) {
    float width = canvas.getScaledWidth();
    float height = canvas.getScaledHeight();
    Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width / 2, height / 2));

    scoreGraphics = new TextGraphics("SCORE: " + Integer.toString((int) score), 1.f, Color.YELLOW, Color.BLACK, 0.025f,
        false, false, anchor.add(new Vector(8, height - 1.2f)));

    for (int i = 0; i < SuperPacmanPlayer.MAX_LIFE; ++i) {
      boolean yellow = i < currentLife ? true : false;
      lifeGraphics[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f,
          new RegionOfInterest(yellow ? 0 : 64, 0, 64, 64), anchor.add(new Vector(i, height - 1.375f)), 1, DEPTH);
    }
    for (ImageGraphics life : lifeGraphics)
      life.draw(canvas);

    scoreGraphics.draw(canvas);
  }

}
