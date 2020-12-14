package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {
  /**
   * Interaction between Interactor and a Player
   * 
   * @param player (Player), not null
   */
  default void interactWith(SuperPacmanPlayer player) {
    // by default the interaction is empty
  }

  /**
   * Interaction between Interactor and a Cherry
   * 
   * @param cherry (Cherry), not null
   */
  default void interactWith(Cherry cherry) {
    // by default the interaction is empty
  }

  /**
   * Interaction between Interactor and a Diamond
   * 
   * @param diamond (Diamond), not null
   */
  default void interactWith(Diamond diamond) {
    // by default the interaction is empty
  }

  /**
   * Interaction between Interactor and a Bonus
   * 
   * @param bonus (Bonus), not null
   */
  default void interactWith(Bonus bonus) {
    // by default the interaction is empty
  }

  /**
   * Interaction between Interactor and a Key
   * 
   * @param key (Key), not null
   */
  default void interactWith(Key key) {
    // by default the interaction is empty
  }

  /**
   * Interaction between Interactor and a Ghost
   * 
   * @param ghost (Ghost), not null
   */
  default void interactWith(Ghost ghost) {
    // by default the interaction is empty
  }
}
