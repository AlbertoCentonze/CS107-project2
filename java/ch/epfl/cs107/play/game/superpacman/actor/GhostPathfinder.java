package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;

/** Superclass of ghosts that adds pathfinding capabilities to it */
public abstract class GhostPathfinder extends Ghost implements Interactor {
  final static int SIGHT_RADIUS = 5;
  protected Queue<Orientation> path;
  private boolean playerSeen = false;
  // private Path graphicPath; // TODO DEBUG
  private GhostState previousState;
  private GhostState currentState;

  private GhostHandler handler;

  /**
   * Default constructor for a ghost that has to be able to look for the player
   * intelligently
   * 
   * @param area      (Area): area of the ghost
   * @param position  (Position): position where the ghost has to be spawned
   * @param ghostType (String): name of the ghost
   */
  public GhostPathfinder(Area area, DiscreteCoordinates position, String ghostType) {
    super(area, position, ghostType);
    handler = new GhostHandler();
  }

  @Override
  protected Orientation getNextOrientation() {
    Orientation newDirection;

    if (path != null && path.size() != 0)
      newDirection = path.poll();
    else {
      System.out.println("Error in the pathfinding");
      newDirection = Orientation.pickRandomly();
    }

    // if (path != null) // TODO DEBUG
    // graphicPath = new Path(this.getPosition(), new
    // LinkedList<Orientation>(path));

    return newDirection;

  }

  /** A method that updates the path according to the state of the ghost */
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
    // Center of the square
    DiscreteCoordinates point = new DiscreteCoordinates(getPosition());
    // Radius of the square
    int radius = SIGHT_RADIUS;
    // Bounds of the area
    int height = getOwnerArea().getHeight();
    int width = getOwnerArea().getWidth();
    // Empty List that will contain the cells in the field of view
    List<DiscreteCoordinates> fieldOfView = new ArrayList<DiscreteCoordinates>();

    // Cordinates in the radius that are not out of bound
    int minX = (point.x - radius) < 0 ? 0 : point.x - radius;
    int maxX = (point.x + radius) > width ? width : point.x + radius;
    int minY = (point.y - radius) < 0 ? 0 : point.y - radius;
    int maxY = (point.y + radius) > height ? height : point.x + radius;

    // Add each coordinate in the radius
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

  /**
   * Method that computes the path that the ghost has to follow when it's scared
   * 
   * @return (Queue<Orientation>): The list of directions that the ghost has to
   *         follow to reach the target
   */
  abstract protected Queue<Orientation> getPathScared();

  /**
   * Method that computes the path that the ghost has to follow he's looking for
   * the player
   * 
   * @return (Queue<Orientation>): The list of directions that the ghost has to
   *         follow to reach the target
   */
  abstract protected Queue<Orientation> getPathWaiting();

  /**
   * Method that computes the path that the ghost has to follow when it's chasing
   * the player
   * 
   * @return (Queue<Orientation>): The list of directions that the ghost has to
   *         follow to reach the target
   */
  abstract protected Queue<Orientation> getPathChasing();

  @Override
  public void draw(Canvas canvas) {
    // TODO DEBUG if (graphicPath != null)
    // TODO DEBUG graphicPath.draw(canvas);

    super.draw(canvas);
  }

  @Override
  public void respawn() {
    playerSeen = false;
    super.respawn();
  }

  /** extremely basic state machine to manage ghosts states in the pathfinding */
  private enum GhostState {
    SCARED, WANDERING, CHASING
  }

  /**
   * Interaction handler for Ghosts to check if the player is in their
   * SIGHT_RADIUS
   */
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
