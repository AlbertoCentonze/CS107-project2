package ch.epfl.cs107.play.game.tutos.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.Color;

public class Player extends Entity {

  public double health = 10;
  private TextGraphics hpText;
  private Sprite sprite;

  public Player(Vector initialPosition, String spriteName) {
    super(initialPosition);
    this.sprite = new Sprite(spriteName, 1.0f, 1.0f, this);
    this.hpText = new TextGraphics(Integer.toString((int) this.health), 1f, Color.BLUE);
    this.hpText.setParent(this);
    this.hpText.setAnchor(new Vector(0f, 1f));
  }

  public boolean isWeak() {
    return this.health <= 0;
  }

  public void strengthen() {
    this.health = 10;
  }

  public void draw(Canvas canvas) {
    this.sprite.draw(canvas);
    this.hpText.draw(canvas);
  }

  @Override
  public void update(float deltaTime) {
    this.health -= deltaTime;
    this.hpText.setText((Integer.toString((int) this.health)));
  }

  public void moveUp() {
    setCurrentPosition(getPosition().add(0.0f, 0.2f));
  }
}
