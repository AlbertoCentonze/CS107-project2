package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends GhostPathfinder {
  public static final int MAX_RANDOM_ATTEMPTS = 200;
  public static final int MIN_AFRAID_DISTANCE = 5;

  /**
   * Default constructor for Inky
   * 
   * @param area     (Area): area of the ghost
   * @param position (Position): position where the ghost has to be spawned
   */
  public Pinky(Area area, DiscreteCoordinates position) {
    super(area, position, "pinky");
  }

  @Override
  protected Queue<Orientation> getPathScared() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    int attempts = 0;
    float distanceFromPlayer = 0.f;
    DiscreteCoordinates target;

    do {
      target = area.getReachableRandomPointInRadius();
      distanceFromPlayer = DiscreteCoordinates.distanceBetween(target, area.getPlayerPosition());
      ++attempts;
    } while (attempts < MAX_RANDOM_ATTEMPTS || distanceFromPlayer < MIN_AFRAID_DISTANCE);
    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

  @Override
  protected Queue<Orientation> getPathWaiting() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    DiscreteCoordinates target = area.getReachableRandomPointInRadius();

    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

  @Override
  protected Queue<Orientation> getPathChasing() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    DiscreteCoordinates target = area.getPlayerPosition();

    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

}
