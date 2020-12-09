package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends GhostPathfinder {
  private static final int MAX_DISTANCE_SCARED = 5;
  private static final int MAX_DISTANCE_NOT_SCARED = 10;
  private static final int SPEED_WHEN_SCARED = 10;

  public Inky(Area area, DiscreteCoordinates position) {
    super(area, position, "inky");
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    setSpeed(isScared() ? SPEED_WHEN_SCARED : DEFAULT_SPEED);
  }

  @Override
  protected Queue<Orientation> getPathScared() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    DiscreteCoordinates target = area.getReachableRandomPointInRadius(respawnPoint, MAX_DISTANCE_SCARED);

    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

  @Override
  protected Queue<Orientation> getPathWaiting() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    DiscreteCoordinates target = area.getReachableRandomPointInRadius(respawnPoint, MAX_DISTANCE_NOT_SCARED);

    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

  @Override
  protected Queue<Orientation> getPathChasing() {
    SuperPacmanArea area = ((SuperPacmanArea) getOwnerArea());
    DiscreteCoordinates target = area.getPlayerPosition();

    return area.getPath(getCurrentMainCellCoordinates(), target);
  }

}
