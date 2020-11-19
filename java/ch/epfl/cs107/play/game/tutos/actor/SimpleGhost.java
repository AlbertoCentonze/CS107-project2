package ch.epfl.cs107.play.game.tutos.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.Color;

public class SimpleGhost extends Entity {

  public double health = 10;
  private TextGraphics hpText;
  private Sprite sprite;

  public SimpleGhost(Vector position, String spriteName) {
    sprite = new Sprite(spriteName, 1.0f, 1.0f, this);
    hpText = new TextGraphics(Integer.toString((int) health), 0.4f, Color.BLUE);
    hpText.setParent(this);
    this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
  }

  public boolean isWeak() {
    return this.health <= 0;
  }

  public void strengthen() {
    this.health = 10;
  }

  public void draw(Canvas canvas){
    sprite.draw(canvas);
    hpText.draw(canvas);
  }

  public void update(float deltaTime){
    health -= deltaTime;
  }

}
