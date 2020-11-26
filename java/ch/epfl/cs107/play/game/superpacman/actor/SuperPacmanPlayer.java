package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {
  private final static int ANIMATION_DURATION = 6;

  private Sprite playerSprite;

  public SuperPacmanPlayer(Area ownerArea, DiscreteCoordinates position) {
    super(ownerArea, Orientation.RIGHT, position);
    playerSprite = new Sprite("ghost.1", 1.f, 1.f, this);

    resetMotion();
  }

  private List<DiscreteCoordinates> getNeighbourPosition() {
    List<DiscreteCoordinates> result;
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
        Arrays.asList(new DiscreteCoordinates((int) orientationVector.x, (int) orientationVector.y)));
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

      // Move the player according to the current orientation
      move(ANIMATION_DURATION);
    }

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
    playerSprite.draw(canvas);
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
    return false;
  }

  @Override
  public boolean wantsViewInteraction() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void interactWith(Interactable other) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean takeCellSpace() {
    // TODO Auto-generated method stub
    return false;
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
  }

  // private float hp;
  // private TextGraphics message;
  // private Sprite sprite;
  ///// Animation duration in frame number

  // public SuperPacmanPlayer(Area owner, Orientation orientation,
  // DiscreteCoordinates coordinates, String spriteName) {
  // super(owner, orientation, coordinates);
  // this.hp = 10;
  // message = new TextGraphics(Integer.toString((int) hp), 0.4f, Color.BLUE);
  // message.setParent(this);
  // message.setAnchor(new Vector(-0.3f, 0.1f));
  // sprite = new Sprite(spriteName, 1.f, 1.f, this);
  //
  // resetMotion();
  // }
  //

  //
  /// **
  // * Leave an area by unregister this player
  // */
  // public void leaveArea() {
  // getOwnerArea().unregisterActor(this);
  // }
  //
  /// **
  // *
  // * @param area (Area): initial area, not null
  // * @param position (DiscreteCoordinates): initial position, not null
  // */
  // public void enterArea(Area area, DiscreteCoordinates position) {
  // area.registerActor(this);
  // area.setViewCandidate(this);
  // setOwnerArea(area);
  // setCurrentPosition(position.toVector());
  // resetMotion();
  // }
  //

  //
  // public boolean isWeak() {
  // return (hp <= 0.f);
  // }
  //
  // public void strengthen() {
  // hp = 10;
  // }
  //
  //// Player implements Interactable
  //
  // @Override
  // public boolean takeCellSpace() {
  // return true;
  // }
  //
  // @Override
  // public boolean isCellInteractable() {
  // return true;
  // }
  //
  // @Override
  // public boolean isViewInteractable() {
  // return true;
  // }
  //
}
