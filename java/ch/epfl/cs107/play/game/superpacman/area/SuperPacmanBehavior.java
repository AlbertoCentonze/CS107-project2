package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {
  public enum SuperPacmanCellType {
    NONE(0), // never used as real content
    WALL(-16777216), // black
    FREE_WITH_DIAMOND(-1), // white
    FREE_WITH_BLINKY(-65536), // red
    FREE_WITH_PINKY(-157237), // pink
    FREE_WITH_INKY(-16724737), // cyan
    FREE_WITH_CHERRY(-36752), // light red
    FREE_WITH_BONUS(-16478723), // light blue
    FREE_EMPTY(-6118750); // sort of gray

    final int type;

    SuperPacmanCellType(int type) {
      this.type = type;
    }

    public static SuperPacmanCellType toType(int type) {
      for (SuperPacmanCellType ict : SuperPacmanCellType.values()) {
        if (ict.type == type)
          return ict;
      }
      return NONE;
    }
  }

  /**
   * Default SuperPacmanBehavior Constructor
   * 
   * @param window (Window), not null
   * @param name   (String): Name of the Behavior, not null
   */
  public SuperPacmanBehavior(Window window, String name) {
    super(window, name);
    int height = getHeight();
    int width = getWidth();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int translatedY = height - 1 - y;
        SuperPacmanCellType type = SuperPacmanCellType.toType(getRGB(translatedY, x));
        setCell(x, y, new SuperPacmanCell(x, y, type));
      }
    }
  }

  protected void registerActors(Area area) {
    int height = getHeight();
    int width = getWidth();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int translatedY = height - 1 - y;
        SuperPacmanCell currentCell = (SuperPacmanCell) getCell(x, translatedY);
        if (currentCell.getType() == SuperPacmanCellType.WALL) {
          DiscreteCoordinates currentPosition = new DiscreteCoordinates(x, translatedY);

          boolean[][] neighbours = getNeighbours(currentPosition);

          area.registerActor(new Wall(area, currentPosition, neighbours));
        }
      }
    }
  }

  public boolean[][] getNeighbours(DiscreteCoordinates point) {
    int height = getHeight();
    int width = getWidth();
    boolean[][] neighbours = new boolean[3][3];

    for (int yOffset = -1; yOffset < 2; ++yOffset) {
      for (int xOffset = -1; xOffset < 2; ++xOffset) {
        DiscreteCoordinates currentNeighbour = new DiscreteCoordinates(point.x + xOffset, point.y + yOffset);
        if (currentNeighbour.x < 0 || currentNeighbour.y < 0 || currentNeighbour.x >= width
            || currentNeighbour.y >= height)
          continue;
        SuperPacmanCell cell = (SuperPacmanCell) getCell(currentNeighbour.x, currentNeighbour.y);
        SuperPacmanCellType type = cell.getType();
        if (type == SuperPacmanCellType.WALL)
          neighbours[xOffset + 1][2 - (yOffset + 1)] = true;
      }
    }
    return neighbours;
  }

  /**
   * Cell adapted to the SuperPacman game
   */
  public class SuperPacmanCell extends AreaBehavior.Cell {
    /// Type of the cell following the enum
    private final SuperPacmanCellType type;

    /**
     * Default SuperPacmanCell Constructor
     * 
     * @param x    (int): x coordinate of the cell
     * @param y    (int): y coordinate of the cell
     * @param type (EnigmeCellType), not null
     */
    public SuperPacmanCell(int x, int y, SuperPacmanCellType type) {
      super(x, y);
      this.type = type;
    }

    public SuperPacmanCellType getType() {
      return this.type;
    }

    @Override
    protected boolean canLeave(Interactable entity) {
      return true;
    }

    @Override
    protected boolean canEnter(Interactable entity) {
      return true; // TODO ????
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
