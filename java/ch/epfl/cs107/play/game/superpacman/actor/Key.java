package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class Key extends CollectableAreaEntity implements Logic {
  Sprite bonusSprite;

  /**
   * Default constructor for Key
   * 
   * @param area     (Area): area of the key
   * @param position (Position): position where the key has to be spawned
   */
  public Key(Area area, DiscreteCoordinates position) {
    super(area, position);
    bonusSprite = new Sprite("superpacman/key", 1.f, 1.f, this);
  }

  @Override
  public void draw(Canvas canvas) {
    bonusSprite.draw(canvas);
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    ((SuperPacmanInteractionVisitor) v).interactWith(this);
    super.acceptInteraction(v);
  }

  @Override
  public boolean isOn() {
    return isCollected();
  }

  @Override
  public boolean isOff() {
    return !isCollected();
  }

  @Override
  public float getIntensity() {
    return isOn() ? 1 : 0;
  }
}
