package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {
  private final static int ANIMATION_DURATION = 6;
  public final static int MAX_LIFE = 5;

  private int score = 0;
  private int life = 3;
  private boolean isInvincible = false;

  // TODO Maybe create a spritesheet class?
  private Sprite[][] spriteSheets = new Sprite[4][4];
  private Animation[] pacmanAnimations = new Animation[4];

  private SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();

  private SuperPacmanStatusGUI hud = new SuperPacmanStatusGUI();

  public SuperPacmanPlayer(Area ownerArea, DiscreteCoordinates position) {
    super(ownerArea, Orientation.RIGHT, position);
    spriteSheets = RPGSprite.extractSprites("superpacman/pacman", 4, 1.f, 1.f, this, 64, 64,
        new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
    pacmanAnimations = Animation.createAnimations(3, spriteSheets);
    resetMotion();
  }

  private List<DiscreteCoordinates> getNeighbourPosition() {
    Vector resultVector;
    Vector orientationVector;

    switch (getOrientation()) {
      case DOWN:
        orientationVector = new Vector(0, -1);
        break;
      case LEFT:
        orientationVector = new Vector(-1, 0);
        break;
      case RIGHT:
        orientationVector = new Vector(1, 0);
        break;
      case UP:
        orientationVector = new Vector(0, 1);
        break;
      default:
        orientationVector = new Vector(0, 0);
        break;

    }

    resultVector = getPosition().add(orientationVector);

    return new ArrayList<DiscreteCoordinates>(
        Arrays.asList(new DiscreteCoordinates((int) resultVector.x, (int) resultVector.y)));
  }

  @Override
  public void update(float deltaTime) {
    Area playerArea = getOwnerArea();
    Keyboard keyboard = playerArea.getKeyboard();
    boolean canGoForward = playerArea.canEnterAreaCells(this, getNeighbourPosition());

    if (!isDisplacementOccurs()) {
      // Listen for directions
      moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
      moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP));
      moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
      moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

      if (canGoForward) {
        // Move the player according to the current orientation
        move(ANIMATION_DURATION);
      } else {
        for (Animation anim : pacmanAnimations) {
          anim.reset();
        }
      }
    }
    for (Animation anim : pacmanAnimations) {
      anim.update(deltaTime);
    }

    hud.setGUI(life, score);

    super.update(deltaTime);

  }

  /**
   * 
   * Orientate or Move this player in the given orientation if the given button
   * is*down**
   * 
   * @param orientation (Orientation): given orientation, not null
   * @param b           (Button): button corresponding to the given orientation,
   *                    not null
   */
  private void moveOrientate(Orientation orientation, Button b) {
    if (b.isDown() && getOrientation() != orientation)
      orientate(orientation);

  }

  @Override
  public void draw(Canvas canvas) {
    hud.draw(canvas);

    switch (getOrientation()) {
      case DOWN:
        pacmanAnimations[2].draw(canvas);
        break;
      case LEFT:
        pacmanAnimations[3].draw(canvas);
        break;
      case RIGHT:
        pacmanAnimations[1].draw(canvas);
        break;
      case UP:
        pacmanAnimations[0].draw(canvas);
        break;
      default:
        pacmanAnimations[0].draw(canvas);
        break;
    }
  }

  @Override
  public List<DiscreteCoordinates> getCurrentCells() {
    return Collections.singletonList(getCurrentMainCellCoordinates());
  }

  @Override
  public List<DiscreteCoordinates> getFieldOfViewCells() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean wantsCellInteraction() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean wantsViewInteraction() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void interactWith(Interactable other) {
    other.acceptInteraction(handler);
  }

  @Override
  public boolean takeCellSpace() {
    return true;
  }

  @Override
  public boolean isCellInteractable() {
    return true;
  }

  @Override
  public boolean isViewInteractable() {
    return true;
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    ((SuperPacmanInteractionVisitor) v).interactWith(this);
  }

  private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
    @Override
    public void interactWith(SuperPacmanPlayer player) {
    }

    @Override
    public void interactWith(Door door) {
      setIsPassingADoor(door);
    }

    @Override
    public void interactWith(Diamond diamond) {
      score += Diamond.VALUE;
    }

    @Override
    public void interactWith(Cherry cherry) {
      score += Cherry.VALUE;
      System.out.println("cherry");
    }

    @Override
    public void interactWith(Bonus bonus) {
      // TODO
      System.out.println("bonus");
    }

    @Override
    public void interactWith(Key key) {
      // TODO
      System.out.println("key");
    }

  }
}
