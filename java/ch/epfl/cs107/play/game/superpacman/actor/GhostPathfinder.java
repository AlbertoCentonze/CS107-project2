package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;

public abstract class GhostPathfinder extends Ghost implements Interactor {
  final static int SIGHT_RADIUS = 5;
  protected Queue<Orientation> path;
  private boolean playerSeen = false;
  private Path graphicPath; // TODO DEBUG
  private GhostState previousState;
  private GhostState currentState;

  private GhostHandler handler;

  public GhostPathfinder(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, position, ghostType);
    handler = new GhostHandler();
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
    currentState = isScared() ? GhostState.SCARED : playerSeen ? GhostState.CHASING : GhostState.WANDERING;
    if (path == null || path.size() == 0 || currentState != previousState)
      updatePath();

    previousState = currentState;
    playerSeen = false;
    super.update(deltaTime);
  }

  @Override
  public List<DiscreteCoordinates> getFieldOfViewCells() {
    DiscreteCoordinates point = new DiscreteCoordinates((int) getPosition().x, (int) getPosition().y);
    int radius = SIGHT_RADIUS;
    int height = getOwnerArea().getHeight();
    int width = getOwnerArea().getWidth();
    List<DiscreteCoordinates> fieldOfView = new ArrayList<DiscreteCoordinates>();

    int minX = (point.x - radius) < 0 ? 0 : point.x - radius;
    int maxX = (point.x + radius) > width ? width : point.x + radius;
    int minY = (point.y - radius) < 0 ? 0 : point.y - radius;
    int maxY = (point.y + radius) > height ? height : point.x + radius;

    for (int i = minX; i < maxX; ++i) {
      for (int j = minY; j < maxY; ++j) {
        fieldOfView.add(new DiscreteCoordinates(i, j));
      }
    }

    return fieldOfView;
  }

  @Override
  public boolean wantsCellInteraction() {
    return false;
  }

  @Override
  public boolean wantsViewInteraction() {
    return true;
  }

  @Override
  public void interactWith(Interactable other) {
    other.acceptInteraction(handler);
  }

  abstract protected Queue<Orientation> getPathScared();

  abstract protected Queue<Orientation> getPathWaiting();

  abstract protected Queue<Orientation> getPathChasing();

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

  private class GhostHandler implements SuperPacmanInteractionVisitor {
    @Override
    public void interactWith(Ghost ghost) {
      // Do nothing
    }

    @Override
    public void interactWith(SuperPacmanPlayer player) {
      System.out.println("Player in range");
      playerSeen = true;
    }
  }
}
