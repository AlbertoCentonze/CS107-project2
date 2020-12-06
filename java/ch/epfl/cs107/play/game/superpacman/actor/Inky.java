package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.LinkedList;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Inky extends Ghost {
  private static final int MAX_DISTANCE_WHEN_SCARED = 5;
  private static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
  private static final int SPEED_WHEN_SCARED = 10;
  private boolean targetAcquired = false;

  private AreaGraph graph;
  private SuperPacmanArea area;
  private DiscreteCoordinates target;
  private Queue<Orientation> path;
  private DiscreteCoordinates lastPosition;

  private Path graphicPath; // TODO DEBUG

  int bella = 0;

  public Inky(Area area, DiscreteCoordinates position) {
    super(area, position, "inky");
    this.area = (SuperPacmanArea) area;
    this.graph = this.area.getGraph();
    this.lastPosition = getCurrentMainCellCoordinates();
  }

  @Override
  protected Orientation getNextOrientation() {
    if (path.poll() == null)
      return Orientation.pickRandomly();
    else
      return path.poll();
  }

  @Override
  public void draw(Canvas canvas) {
    if (graphicPath != null)
      graphicPath.draw(canvas);

    super.draw(canvas);
  }

  @Override
  public void update(float deltaTime) {
    DiscreteCoordinates currentPosition = getCurrentMainCellCoordinates();
    boolean didTheGhostMove = !lastPosition.equals(currentPosition);
    if (didTheGhostMove) {
      lastPosition = currentPosition;
      if (isScared()) {
        target = respawnPoint;
        path = graph.shortestPath(currentPosition, target);
      } else if (targetAcquired) {
        target = area.getPlayerPosition();
        path = graph.shortestPath(currentPosition, target);
      }
      orientate(getNextOrientation());
    }

    if (path != null)
      graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));
    setSpeed(isScared() ? SPEED_WHEN_SCARED : GHOST_DEFAULT_SPEED);
    super.update(deltaTime);
  }

  @Override
  public void respawn() {
    targetAcquired = false;
    super.respawn();
  }

}
