package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
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
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class SuperPacmanPlayer extends Player {
  private Sprite[][] spriteSheets = new Sprite[4][4];
  private Animation[] pacmanAnimations = new Animation[4];

  private final static int ANIMATION_DURATION = 6;
  private final static int BONUS_DURAION = 10;
  public final static int MAX_LIFE = 5;

  private int score = 0;
  private int life = 3;
  private int collectedDiamonds = 0;

  public boolean invulnerable = false;
  public float bonusTimer;

  private SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();
  private SuperPacmanStatusGUI hud = new SuperPacmanStatusGUI();

  public SuperPacmanPlayer(Area ownerArea, DiscreteCoordinates position) {
    super(ownerArea, Orientation.RIGHT, position);
    spriteSheets = RPGSprite.extractSprites("superpacman/pacman", 4, 1.f, 1.f, this, 64, 64,
        new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
    pacmanAnimations = Animation.createAnimations(3, spriteSheets);
    resetMotion();
  }

  @Override
  public void update(float deltaTime) {
    Area playerArea = getOwnerArea();
    Keyboard keyboard = playerArea.getKeyboard();
    boolean canGoForward = playerArea.canEnterAreaCells(this, getNextCurrentCells());

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

    if (invulnerable) {
      hud.setGUI((int) (bonusTimer / 2 + 1), score);
    } else {
      hud.setGUI(life, score);
    }

    if (bonusTimer >= 0)
      bonusTimer -= deltaTime;
    else
      invulnerable = false;

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

  /** Handle the respawn of the player when it gets eaten by a ghost */
  public void loseLife() {
    getOwnerArea().leaveAreaCells(this, getEnteredCells());
    setCurrentPosition(((SuperPacmanArea) getOwnerArea()).getRespawnPoint().toVector());
    getOwnerArea().enterAreaCells(this, getCurrentCells());
    resetMotion();
    life -= 1;
  }

  /**
   * Getter for the invulnerability of the player
   * 
   * @return (boolean): true if the player is invulnerable
   *
   */
  public boolean isInvulnerable() {
    return invulnerable;
  }

  @Override
  public List<DiscreteCoordinates> getCurrentCells() {
    return Collections.singletonList(getCurrentMainCellCoordinates());
  }

  @Override
  public List<DiscreteCoordinates> getFieldOfViewCells() {
    return new ArrayList<DiscreteCoordinates>(); // TODO fix this
  }

  @Override
  public boolean wantsCellInteraction() {
    return true;
  }

  @Override
  public boolean wantsViewInteraction() {
    return true;
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

  public int getCollectedDiamonds() {
    return collectedDiamonds;
  }

  private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
    @Override
    public void interactWith(SuperPacmanPlayer player) {
    }

    @Override
    public void interactWith(Door door) {
      invulnerable = false;
      collectedDiamonds = 0;
      setIsPassingADoor(door);
    }

    @Override
    public void interactWith(Diamond diamond) {
      score += Diamond.VALUE;
      ++collectedDiamonds;
    }

    @Override
    public void interactWith(Cherry cherry) {
      score += Cherry.VALUE;
    }

    @Override
    public void interactWith(Bonus bonus) {
      invulnerable = true;
      bonusTimer = BONUS_DURAION;
    }

    public void interactWith(Ghost ghost) {
      if (invulnerable) {
        score += Ghost.SCORE;
        ghost.respawn();
      } else {
        loseLife();
      }
    }
  }

}
