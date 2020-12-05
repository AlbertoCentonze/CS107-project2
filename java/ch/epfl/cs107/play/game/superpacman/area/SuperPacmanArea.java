package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

abstract public class SuperPacmanArea extends Area {
  private SuperPacmanBehavior behavior;
  private boolean ghostsScared;
  private boolean diamondsCollected = false;

  /**
   * Create the area by adding it all actors called by begin method Note it set
   * the Behavior as needed !
   */
  protected void createArea() {
    behavior.registerActors(this);
  };

  @Override
  public final float getCameraScaleFactor() {
    return SuperPacman.CAMERA_SCALE_FACTOR;
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {
    if (super.begin(window, fileSystem)) {
      // Set the behavior map
      behavior = new SuperPacmanBehavior(window, getTitle());
      setBehavior(behavior);
      createArea();
      return true;
    }
    return false;
  }

  public boolean isEveryGhostScared() {
    return ghostsScared;
  }

  public boolean isEveryDiamondCollected() {
    return diamondsCollected;
  }

  public void updateAreaInformations(SuperPacmanPlayer player) {
    diamondsCollected = player.getCollectedDiamonds() == behavior.totalDiamonds;
    ghostsScared = player.isInvulnerable();
  }

}
