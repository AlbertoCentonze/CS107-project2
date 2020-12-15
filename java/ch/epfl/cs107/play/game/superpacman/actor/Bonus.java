package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class Bonus extends CollectableAreaEntity {
  final static int BONUS_DURATION = 10;

  Animation bonusAnimation;

  /**
   * Default constructor for Bonus
   * 
   * @param area     (Area): area of the bonus
   * @param position (Position): position where the bonus has to be spawned
   */
  public Bonus(Area area, DiscreteCoordinates position) {
    super(area, position);
    Sprite[] bonusSpritesheet = RPGSprite.extractSprites("superpacman/coin", 4, 1.f, 1.f, this, 16, 16);
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
