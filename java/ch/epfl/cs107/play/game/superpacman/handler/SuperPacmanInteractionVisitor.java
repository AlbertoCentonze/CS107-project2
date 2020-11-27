package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {
  /**
   * Simulate and interaction between Interactor and a Player
   * 
   * @param player (Player), not null
   */
  default void interactWith(SuperPacmanPlayer player) {
    // by default the interaction is empty
  }
}
