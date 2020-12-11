package ch.epfl.cs107.play.game.superpacman.area;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.signal.logic.Logic;

abstract public class SuperPacmanArea extends Area implements Logic {
  protected SuperPacmanBehavior behavior;
  private boolean ghostsScared = false;
  private boolean diamondsCollected = false;
  private DiscreteCoordinates playerPosition;

  protected void createArea() {
    behavior.registerActors(this);
  };

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
    playerPosition = (player.getCurrentCells().get(0));
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

  public DiscreteCoordinates getReachableRandomPointInRadius(DiscreteCoordinates center, int radius) {
    return behavior.getRandomFreePoint(center, radius);
  }
}
