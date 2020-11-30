package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class Bonus extends CollectableAreaEntity {
  Sprite[] bonusSpritesheet = new Sprite[4];
  Animation bonusAnimation;

  public Bonus(Area area, DiscreteCoordinates position) {
    super(area, position);
    for (int frame = 0; frame < bonusSpritesheet.length; ++frame) {
      bonusSpritesheet[frame] = new Sprite("superpacman/Coin", 1.f, 1.f, this,
          new RegionOfInterest(frame * 16, 0, 16, 16));
    }
    bonusAnimation = new Animation(6, bonusSpritesheet);
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    bonusAnimation.update(deltaTime);
  }

  @Override
  public void draw(Canvas canvas) {
    bonusAnimation.draw(canvas);
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    ((SuperPacmanInteractionVisitor) v).interactWith(this);
    super.acceptInteraction(v);
  }
}
