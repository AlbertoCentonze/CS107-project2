package ch.epfl.cs107.play.game.superpacman.area;

import java.util.Queue;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.signal.logic.Logic;

abstract public class SuperPacmanArea extends Area implements Logic {
  private SuperPacmanBehavior behavior;
  private boolean ghostsScared = false;
  private boolean diamondsCollected = false;
  private DiscreteCoordinates playerPosition;

  /** Creates the area by initializing its corresponding behavior */
  protected void createArea() {
    behavior.registerActors(this);
    registerActor(new Background(this));
  }

  @Override
  public float getCameraScaleFactor() {
    return 25.f;
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

  /**
   * Getter to know if the ghosts have to run away
   * 
   * @return (boolean): true if the ghosts are scared
   */
  public boolean isGhostsScared() {
    return ghostsScared;
  }

  /**
   * Getter to know the position of the player
   * 
   * @return (DiscreteCoordinates): Coordinates of the player
   */
  public DiscreteCoordinates getPlayerPosition() {
    return playerPosition;
  }

  /**
   * Side effect that lets the area now various informations that need to be
   * shared between entities
   * 
   * @param player (SuperPacmanPlayer): the player
   */
  public void updateAreaState(SuperPacmanPlayer player) {
    diamondsCollected = player.getCollectedDiamonds() == behavior.totalDiamonds;
    ghostsScared = player.isInvulnerable();
    playerPosition = (player.getCurrentCells().get(0));
  }

  /**
   * getter to obtain a path from areagraph
   * 
   * @param from (DiscreteCoordinates): starting point
   * @param to   (DiscreteCoordinates): target point
   * @return (Queue<Orientation>): List of the direction to reach the objective
   */
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

  /**
   * Getter to access a random point in range
   * 
   * @param center (DiscreteCoordinates): the center of the square
   * @param radius (int): half the length of the square's sides
   * @return (DiscreteCoordinates): The coordinates of the random point
   */
  public DiscreteCoordinates getReachableRandomPointInRadius(DiscreteCoordinates center, int radius) {
    return behavior.getRandomFreePoint(center, radius);
  }

  /**
   * Getter to access a random point in range
   * 
   * @return (DiscreteCoordinates): The coordinates of the random point
   */
  public DiscreteCoordinates getReachableRandomPointInRadius() {
    return behavior.getRandomFreePoint();
  }

  /**
   * Getter for the respawnPoint of the player
   * 
   * @return (DiscreteCoordinates): the point where the player has to respawn
   */
  abstract public DiscreteCoordinates getRespawnPoint();

  /**
   * Setter to change the signal of a node
   * 
   * @param entity (Entity): entity from which you want to take the coordinates
   * @param signal (Logic): connected signal that you want to link to the entity
   */
  public void setGraphSignal(Entity entity, Logic signal) {
    behavior.graph.setSignal(new DiscreteCoordinates(entity.getPosition()), signal);
  }

}
