package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Diamond extends CollectableAreaEntity {
  public static final int VALUE = 10;
  Sprite diamondSprite;

  public Diamond(Area area, DiscreteCoordinates position) {
    super(area, position);
    diamondSprite = new Sprite("superpacman/diamond", 1f, 1f, this);
  }

  @Override
  public void draw(Canvas canvas) {
    diamondSprite.draw(canvas);
  }

}
