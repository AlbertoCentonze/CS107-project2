package ch.epfl.cs107.play.game.superpacman.area;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;

abstract public class SuperPacmanArea extends Area implements Logic {
  private SuperPacmanBehavior behavior;
  private boolean ghostsScared = false;
  private boolean diamondsCollected = false;
  private DiscreteCoordinates playerPosition;

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

  public boolean isGhostsScared() {
    return ghostsScared;
  }

  public DiscreteCoordinates getPlayerPosition() {
    return playerPosition;
  }

  public void updateAreaState(SuperPacmanPlayer player) {
    diamondsCollected = player.getCollectedDiamonds() == behavior.totalDiamonds;
    ghostsScared = player.isInvulnerable();
    Vector playerVector = (player.getCurrentCells().get(player.getCurrentCells().size() - 1)).toVector().add(1f, 1f);
    playerPosition = new DiscreteCoordinates((int) playerVector.x, (int) playerVector.y);
  }

  public Queue<Orientation> getPath(DiscreteCoordinates from, DiscreteCoordinates to) {
    return behavior.graph.shortestPath(from, to);
  }

  @Override
  public boolean isOn() {
    return diamondsCollected;
  }

  @Override
  public boolean isOff() {
    return !diamondsCollected;
  }

  @Override
  public float getIntensity() {
    return diamondsCollected ? 1 : 0;
  }
}
