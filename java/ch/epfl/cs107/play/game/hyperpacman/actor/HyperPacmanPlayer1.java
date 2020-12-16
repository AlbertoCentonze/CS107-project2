package ch.epfl.cs107.play.game.hyperpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;

public class HyperPacmanPlayer1 extends SuperPacmanPlayer {

  public HyperPacmanPlayer1(Area ownerArea, DiscreteCoordinates position) {
    super(ownerArea, position);
  }

  @Override
  protected void handleMovement() {
    Keyboard keyboard = getOwnerArea().getKeyboard();

    if (!isDisplacementOccurs()) {
      // Listen for directions
      moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.A));
      moveOrientate(Orientation.UP, keyboard.get(Keyboard.W));
      moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.D));
      moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.S));
    }
  }
}
