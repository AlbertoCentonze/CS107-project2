package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Cherry extends CollectableAreaEntity {
  Sprite cherrySprite;

  public Cherry(Area area, DiscreteCoordinates position) {
    super(area, position);
    cherrySprite = new Sprite("superpacman/cherry", 1.f, 1.f, this);
  }

  @Override
  public void draw(Canvas canvas) {
    cherrySprite.draw(canvas);
  }

}
