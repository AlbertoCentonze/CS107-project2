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
  public final static int SCORE = 500;
  public final static int DEFAULT_SPEED = 16;
  private Animation afraidAnimation;
  private Animation[] ghostAnimations;

  protected DiscreteCoordinates respawnPoint;

  private int ghostCurrentSpeed;

  public Ghost(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, Orientation.UP, position);
    this.respawnPoint = position;
    this.ghostCurrentSpeed = DEFAULT_SPEED;

    Sprite[][] ghostSpritesheets = RPGSprite.extractSprites("superpacman/ghost." + ghostType, 2, 1.f, 1.f, this, 16, 16,
        new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
    ghostAnimations = Animation.createAnimations(3, ghostSpritesheets);

    Sprite[] afraidSpritesheet = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1.f, 1.f, this, 16, 16);
    afraidAnimation = new Animation(3, afraidSpritesheet);
  }

  protected abstract Orientation getNextOrientation();

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);

    if (!isDisplacementOccurs()) {
      orientate(getNextOrientation());
      move(ghostCurrentSpeed);
    }

    if (isScared())
      afraidAnimation.update(deltaTime);
    else
      switch (getOrientation()) {
        case DOWN:
          ghostAnimations[0].update(deltaTime);
          break;
        case LEFT:
          ghostAnimations[1].update(deltaTime);
          break;
        case RIGHT:
          ghostAnimations[3].update(deltaTime);
          break;
        case UP:
          ghostAnimations[2].update(deltaTime);
          break;
        default:
          break;
      }
  }

  @Override
  public void draw(Canvas canvas) {
    if (isScared())
      afraidAnimation.draw(canvas);
    else
      switch (getOrientation()) {
        case DOWN:
          ghostAnimations[0].draw(canvas);
          break;
        case LEFT:
          ghostAnimations[1].draw(canvas);
          break;
        case RIGHT:
          ghostAnimations[3].draw(canvas);
          break;
        case UP:
          ghostAnimations[2].draw(canvas);
          break;
        default:
          break;
      }
  }

  protected boolean isScared() {
    return ((SuperPacmanArea) getOwnerArea()).isGhostsScared();
  }

  protected void setSpeed(int newSpeed) {
    this.ghostCurrentSpeed = newSpeed;
  }

  public void respawn() {
    getOwnerArea().leaveAreaCells(this, getEnteredCells());
    setCurrentPosition(respawnPoint.toVector());
    getOwnerArea().enterAreaCells(this, getCurrentCells());
    resetMotion();
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
