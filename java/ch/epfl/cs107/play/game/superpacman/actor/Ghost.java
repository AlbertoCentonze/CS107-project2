package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

abstract public class Ghost extends MovableAreaEntity {
  final static int GHOST_SCORE = 500;
  final static int SIGHT_RADIUS = 5;
  final static int GHOST_DEFAULT_SPEED = 16;

  private Animation afraidAnimation;
  private Animation ghostAnimation;

  protected DiscreteCoordinates respawnPoint;

  private int ghostCurrentSpeed = GHOST_DEFAULT_SPEED;

  public Ghost(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, Orientation.UP, position);
    this.respawnPoint = position;

    Sprite[] ghostSpritesheet = RPGSprite.extractSprites("superpacman/ghost." + ghostType, 2, 1.f, 1.f, this, 16, 16);
    ghostAnimation = new Animation(3, ghostSpritesheet);

    Sprite[] afraidSpritesheet = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1.f, 1.f, this, 16, 16);
    afraidAnimation = new Animation(3, afraidSpritesheet);
  }

  protected abstract Orientation getNextOrientation();

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);

    if (!isDisplacementOccurs() && getNextOrientation() != null) {
      orientate(getNextOrientation());
      move(ghostCurrentSpeed);
    }

    if (isScared())// TODO update animation in the level
      afraidAnimation.update(deltaTime);
    else
      ghostAnimation.update(deltaTime);
  }

  @Override
  public void draw(Canvas canvas) {
    if (isScared())
      afraidAnimation.draw(canvas);
    else
      ghostAnimation.draw(canvas);
  }

  protected boolean isScared() {
    return ((SuperPacmanArea) getOwnerArea()).isGhostsScared();
  }

  protected void setSpeed(int newSpeed) {
    this.ghostCurrentSpeed = newSpeed;
  }

  public void respawn() {
    setCurrentPosition(respawnPoint.toVector());
  }

  @Override
  final public boolean isViewInteractable() {
    return false;
  }

  @Override
  final public boolean isCellInteractable() {
    return true;
  }

  @Override
  public final boolean takeCellSpace() {
    return false;
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    ((SuperPacmanInteractionVisitor) v).interactWith(this);
  }

  @Override
  public List<DiscreteCoordinates> getCurrentCells() {
    return Collections.singletonList(getCurrentMainCellCoordinates());
  }
}
