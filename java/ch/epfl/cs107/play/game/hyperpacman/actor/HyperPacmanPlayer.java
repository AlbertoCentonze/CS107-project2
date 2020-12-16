package ch.epfl.cs107.play.game.hyperpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Keyboard;

public class HyperPacmanPlayer extends SuperPacmanPlayer implements Logic {
  private boolean secondPlayer;
  public Boolean win;

  /**
   * Default constructor for the players of HyperPacman
   * 
   * @param ownerArea    (Area): the area of the player
   * @param position     (DiscreteCoordinates): the initial position of the player
   * @param secondPlayer (boolean): true if it's the second player
   */
  public HyperPacmanPlayer(Area ownerArea, DiscreteCoordinates position, boolean secondPlayer) {
    super(ownerArea, position);
    handler = new HyperPacmanPlayerHandler();
    this.secondPlayer = secondPlayer;
    this.win = false;
    if (secondPlayer) {
      Sprite[][] spriteSheets = RPGSprite.extractSprites("superpacman/pacmangreen", 4, 1.f, 1.f, this, 64, 64,
          new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
      pacmanAnimations = Animation.createAnimations(3, spriteSheets);
    }
    hud = new HyperPacmanGUI(secondPlayer, this);
  }

  @Override
  protected void handleMovement() {
    if (secondPlayer) {
      Keyboard keyboard = getOwnerArea().getKeyboard();
      if (!isDisplacementOccurs()) {
        // Listen for directions
        moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.A));
        moveOrientate(Orientation.UP, keyboard.get(Keyboard.W));
        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.D));
        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.S));
      }
    } else
      super.handleMovement();
  }

  private class HyperPacmanPlayerHandler extends SuperPacmanPlayer.SuperPacmanPlayerHandler {
    @Override
    public void interactWith(Key key) {
      win = true;
      super.interactWith(key);
    }
  }

  @Override
  public boolean isOn() {
    return win;
  }

  @Override
  public boolean isOff() {
    return !win;
  }

  @Override
  public float getIntensity() {
    return isOn() ? 1 : 0;
  }
}
