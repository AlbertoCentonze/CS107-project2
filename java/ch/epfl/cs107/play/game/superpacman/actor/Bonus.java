package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Bonus extends CollectableAreaEntity {
  Sprite bonusSprite;

  public Bonus(Area area, DiscreteCoordinates position) {
    super(area, position);
    bonusSprite = new Sprite("superpacman/bonus", 1.f, 1.f, this);
  }

  @Override
  public void draw(Canvas canvas) {
    bonusSprite.draw(canvas);

  }

}
