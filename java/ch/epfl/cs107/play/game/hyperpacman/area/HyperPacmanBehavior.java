package ch.epfl.cs107.play.game.hyperpacman.area;

import java.util.Random;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanBehavior extends AreaBehavior {
  public enum HyperPacmanCellType {
    // enumerations elements
    NONE(0), // never used as real content
    WALL(-16777216), // black
    DIAMOND(-1); // white

    // type from color
    final int type;

    /**
     * Default constructor
     * 
     * @param type: (int) color of the cell
     */
    HyperPacmanCellType(int type) {
      this.type = type;
    }

    /**
     * Converts the the cell from its type to the corresponding value in the
     * enumeration
     * 
     * @param type
     * @return (HyperPacmanCellType) corresponding cell
     */
    public static HyperPacmanCellType toType(int type) {
      for (HyperPacmanCellType cell : HyperPacmanCellType.values()) {
        if (cell.type == type)
          return cell;
      }
      return NONE;
    }
  }

  int height;
  int width;

  protected int totalDiamonds = 0;
  protected AreaGraph graph;

  /**
   * Default SuperPacmanBehavior Constructor
   * 
   * @param window (Window), not null
   * @param name   (String): Name of the Behavior, not null
   */
  public HyperPacmanBehavior(Window window, String name) {
    super(window, name);
    graph = new AreaGraph();
    height = getHeight();
    width = getWidth();
    // png to array
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // y needs to be translated to start from the opposite direction
        int translatedY = height - 1 - y;
        // extract the cell type from the image
        HyperPacmanCellType type = HyperPacmanCellType.toType(getRGB(translatedY, x));
        setCell(x, y, new HyperPacmanCell(x, y, type));
      }
    }
    // graph generation
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        HyperPacmanCellType cellType = ((HyperPacmanCell) getCell(x, y)).getType();
        // if the cell is not a wall adds a node
        if (cellType != HyperPacmanCellType.WALL) {
          DiscreteCoordinates currentPoint = new DiscreteCoordinates(x, y);
          graph.addNode(currentPoint, isEdgeFree(currentPoint, Orientation.LEFT),
              isEdgeFree(currentPoint, Orientation.UP), isEdgeFree(currentPoint, Orientation.RIGHT),
              isEdgeFree(currentPoint, Orientation.DOWN));
        }
      }
    }
  }

  /**
   * Checks if a cell close to an other one has a wall or not
   * 
   * @param point       (DiscreteCoordinates): the point to check
   * @param orientation (Orientation): the orientation of the edge of the point
   * @return (boolean): true if the edge of the cell checked doesn't have a wall
   */
  private boolean isEdgeFree(DiscreteCoordinates point, Orientation orientation) {
    DiscreteCoordinates edgeCoordinates = new DiscreteCoordinates(point.toVector().add(orientation.toVector()));

    if (edgeCoordinates.x < 0 || edgeCoordinates.y < 0 || edgeCoordinates.x >= width || edgeCoordinates.y >= height)
      return false;
    else {
      HyperPacmanCellType edgeType = ((HyperPacmanCell) getCell(edgeCoordinates.x, edgeCoordinates.y)).type;
      return edgeType != HyperPacmanCellType.WALL;
    }
  }

  /**
   * Returns a random point that is not a wall in a square radius from a center.
   * 
   * @param center (DiscreteCoordinates): the center of the square
   * @param radius (int): half the length of the square's sides
   * @return (DiscreteCoordinates): The coordinates of the random point
   */
  public DiscreteCoordinates getRandomFreePoint(DiscreteCoordinates center, int radius) {
    Random r = RandomGenerator.getInstance();

    int min = -radius;
    int max = radius;

    int xRandom;
    int yRandom;
    HyperPacmanCell currentCell = null;
    boolean wall = false;

    do {
      // while the coordinates are out of bound attempt to find a new one
      do {
        xRandom = r.ints(1, min, max).findFirst().getAsInt() + center.x;
      } while (xRandom < 0 || xRandom >= width);
      do {
        yRandom = r.ints(1, min, max).findFirst().getAsInt() + center.y;
      } while (yRandom < 0 || yRandom >= height);
      currentCell = (HyperPacmanCell) getCell(xRandom, yRandom);
      wall = currentCell.getType() == HyperPacmanCellType.WALL;
      // repeat untill it's a wall
    } while (wall);

    return currentCell.getCurrentCells().get(0);

  }

  /**
   * Returns a random point that is not a wall randomly picked from the whole
   * stage.
   * 
   * @return (DiscreteCoordinates): The coordinates of the random point
   */
  public DiscreteCoordinates getRandomFreePoint() {
    Random r = RandomGenerator.getInstance();
    int xRandom;
    int yRandom;
    HyperPacmanCell currentCell;
    boolean wall = false;

    do {
      // while the coordinates are out of bound attempt to find a new one
      do {
        xRandom = r.nextInt(width);
      } while (xRandom < 0 || xRandom > width);
      do {
        yRandom = r.nextInt(height);
      } while (yRandom < 0 || yRandom > height);
      currentCell = (HyperPacmanCell) getCell(xRandom, yRandom);
      wall = (currentCell.getType() == HyperPacmanCellType.WALL);
      // repeat untill it's a wall
    } while (wall);

    return currentCell.getCurrentCells().get(0);
  }

  /**
   * Reads from the behavior image and correctly add each Entity to the right
   * position
   * 
   * @param area (Area): the specific area in which actors have to be registered
   */
  protected void registerActors(Area area, Key key) {
    height = getHeight();
    width = getWidth();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // y needs to be translated to start from the opposite direction
        int translatedY = height - 1 - y;
        DiscreteCoordinates currentPosition = new DiscreteCoordinates(x, translatedY);
        HyperPacmanCell currentCell = (HyperPacmanCell) getCell(currentPosition.x, currentPosition.y);
        // Adds for each type the corresponding entity
        if (y == height / 2 && x == width / 2) {
          area.registerActor(key);
          continue;
        }
        if (currentCell.getType() == HyperPacmanCellType.WALL) {
          boolean[][] neighbours = getNeighbours(currentPosition);
          area.registerActor(new Wall(area, currentPosition, neighbours));
        } else if (currentCell.getType() == HyperPacmanCellType.DIAMOND) {
          if (RandomGenerator.getRandomBoolean(.02f)) { // possibility of spawning a Cherry
            area.registerActor(new Cherry(area, currentPosition));
          } else {
            area.registerActor(new Diamond(area, currentPosition));
          }
          ++totalDiamonds;
        }
      }
    }
  }

  /**
   * Build a 2D-array containing true if there's a wall in that point
   * 
   * @param point (DiscreteCoordinates): cordinates of the point of which you want
   *              to check the neighborhood
   * @return (boolean[][]): 3X3 2d-array, the center at [1][1] is the point itself
   *         and the other points are the respective neighbours
   */
  private boolean[][] getNeighbours(DiscreteCoordinates point) {
    boolean[][] neighbours = new boolean[3][3];
    for (int yOffset = -1; yOffset < 2; ++yOffset) {
      for (int xOffset = -1; xOffset < 2; ++xOffset) {
        DiscreteCoordinates currentNeighbour = new DiscreteCoordinates(point.x + xOffset, point.y + yOffset);
        // Check if currentNeighbour is out of bound
        if (currentNeighbour.x < 0 || currentNeighbour.y < 0 || currentNeighbour.x >= width
            || currentNeighbour.y >= height)
          continue;
        // Gets the corresponding cell
        HyperPacmanCell cell = (HyperPacmanCell) getCell(currentNeighbour.x, currentNeighbour.y);
        HyperPacmanCellType type = cell.getType();
        // Check if it's a wall
        if (type == HyperPacmanCellType.WALL)
          neighbours[xOffset + 1][2 - (yOffset + 1)] = true;
      }
    }
    return neighbours;
  }

  /**
   * Cell adapted to the SuperPacman game
   */
  public class HyperPacmanCell extends AreaBehavior.Cell {
    /// Type of the cell following the enum
    private final HyperPacmanCellType type;

    /**
     * Default HyperPacmanCell Constructor
     * 
     * @param x    (int): x coordinate of the cell
     * @param y    (int): y coordinate of the cell
     * @param type (EnigmeCellType), not null
     */
    public HyperPacmanCell(int x, int y, HyperPacmanCellType type) {
      super(x, y);
      this.type = type;
    }

    /**
     * Getter for the type of the cell
     * 
     * @return (HyperPacmanCellType): return the type of the cell
     */
    public HyperPacmanCellType getType() {
      return this.type;
    }

    @Override
    protected boolean canLeave(Interactable entity) {
      return true;
    }

    @Override
    protected boolean canEnter(Interactable entity) {
      return !this.hasNonTraversableContent();
    }

    @Override
    public boolean isCellInteractable() {
      return true;
    }

    @Override
    public boolean isViewInteractable() {
      return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }

  }
}
