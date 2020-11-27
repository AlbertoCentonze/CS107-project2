package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SuperPacmanStatusGUI implements Graphics { // TODO updatable?
  ImageGraphics[] lifeGraphics = new ImageGraphics[5];
  TextGraphics scoreGraphics;
  private final static int DEPTH = 5;

  private int currentLife = 3;
  private int score = 0;

  private float width;
  private float height;
  private Vector anchor;

  protected SuperPacmanStatusGUI() {
  }

  public void setLife(int currentLife) {
    this.currentLife = currentLife;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public void draw(Canvas canvas) {
    width = canvas.getScaledWidth();
    height = canvas.getScaledHeight();
    anchor = canvas.getTransform().getOrigin().sub(new Vector(width / 2, height / 2));

    scoreGraphics = new TextGraphics(Integer.toString((int) score), 1.f, Color.BLUE);
    scoreGraphics.setAnchor(anchor);

    for (int i = 0; i < SuperPacmanPlayer.MAX_LIFE; ++i) {
      boolean yellow = i < currentLife ? true : false;
      lifeGraphics[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f,
          new RegionOfInterest(yellow ? 0 : 64, 0, 64, 64), anchor.add(new Vector(i * 50, height - 1.375f)), 1, DEPTH);
      lifeGraphics[i].draw(canvas);
    }
    scoreGraphics.draw(canvas);
  }

}
