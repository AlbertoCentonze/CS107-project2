package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;
import java.util.LinkedList;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;

public abstract class GhostPathfinder extends Ghost {
  final static int SIGHT_RADIUS = 5;
  protected Queue<Orientation> path;
  private boolean playerSeen = false;
  private Path graphicPath; // TODO DEBUG
  private boolean runningAway = false;
  private GhostState previousState;
  private GhostState currentState;

  public GhostPathfinder(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, position, ghostType);
  }

  // abstract public DiscreteCoordinates getTarget();

  @Override
  protected Orientation getNextOrientation() {
    Orientation newDirection;

    try {
      newDirection = path.poll();
    } catch (NullPointerException e) {
      System.out.println("Error in the pathfinding");
      newDirection = Orientation.pickRandomly();
    }

    if (path != null) // TODO DEBUG
      graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));

    return newDirection;
  }

  private void updatePath() {
    if (path == null) {
      path = new LinkedList<>();
    }

    switch (currentState) {
      case CHASING:
        System.out.println("chasing player");
        path = getPathChasing();
        break;
      case SCARED:
        System.out.println("running away");
        path = getPathScared();
        break;
      case WANDERING:
        System.out.println("looking around");
        path = getPathWaiting();
        break;
      default:
        path = new LinkedList<Orientation>();
        path.add(Orientation.pickRandomly());
        break;

    }
  }

  @Override
  public void update(float deltaTime) {
    playerSeen = checkForPlayer();
    currentState = isScared() ? GhostState.SCARED : playerSeen ? GhostState.CHASING : GhostState.WANDERING;
    if (path == null || path.size() == 0 || currentState != previousState)
      updatePath();

    previousState = currentState;
    super.update(deltaTime);
  }

  abstract protected Queue<Orientation> getPathScared();

  abstract protected Queue<Orientation> getPathWaiting();

  abstract protected Queue<Orientation> getPathChasing();

  protected boolean checkForPlayer() {
    DiscreteCoordinates playerPosition = ((SuperPacmanArea) getOwnerArea()).getPlayerPosition();
    if (playerPosition == null)
      return false;

    return DiscreteCoordinates.distanceBetween(playerPosition, getCurrentMainCellCoordinates()) <= SIGHT_RADIUS;
  }

  @Override
  public void draw(Canvas canvas) {
    if (graphicPath != null)
      graphicPath.draw(canvas);

    super.draw(canvas);
  }

  @Override
  public void respawn() {
    playerSeen = false;
    super.respawn();
  }

  private enum GhostState {
    SCARED, WANDERING, CHASING
  }
}
