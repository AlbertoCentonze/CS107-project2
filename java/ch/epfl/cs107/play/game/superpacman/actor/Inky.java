package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.LinkedList;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Inky extends GhostPathfinder {
  private static final int MAX_DISTANCE_WHEN_SCARED = 5;
  private static final int SPEED_WHEN_SCARED = 10;
  private boolean targetAcquired = false;

  private Queue<Orientation> path;

  private Path graphicPath; // TODO DEBUG

  public Inky(Area area, DiscreteCoordinates position) {
    super(area, position, "inky");
  }

  @Override
  protected Orientation getNextOrientation() {
    Orientation newDirection;
    SuperPacmanArea area = (SuperPacmanArea) getOwnerArea();
    DiscreteCoordinates playerPosition = area.getPlayerPosition();
    DiscreteCoordinates currentPosition = getCurrentMainCellCoordinates();

    if (playerPosition != null)
      targetAcquired = DiscreteCoordinates.distanceBetween(currentPosition, playerPosition) <= SIGHT_RADIUS;
    if (isScared()) {
      path = area.getPath(currentPosition, respawnPoint);
    } else if (targetAcquired) {
      path = area.getPath(currentPosition, playerPosition);
    }

    try {
      newDirection = path.poll();
    } catch (NullPointerException e) {
      return Orientation.pickRandomly();
    }

    if (path != null) // TODO DEBUG
      graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));

    return newDirection;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    setSpeed(isScared() ? SPEED_WHEN_SCARED : DEFAULT_SPEED);
  }

  @Override
  public void draw(Canvas canvas) {
    if (graphicPath != null)
      graphicPath.draw(canvas);

    super.draw(canvas);
  }

  @Override
  public void respawn() {
    targetAcquired = false;
    super.respawn();
  }

}
