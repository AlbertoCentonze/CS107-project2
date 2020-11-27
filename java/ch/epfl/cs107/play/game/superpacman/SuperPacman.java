package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {
  public final static float CAMERA_SCALE_FACTOR = 15.f;
  public final static float STEP = 0.05f;

  private Player player;

  // TODO maybe we could create a level structure class
  private final String[] areas = { "superpacman/Level0", "superpacman/Level1", "superpacman/Level2" };
  public static final DiscreteCoordinates[] PLAYER_SPAWN_COORDINATES = { new DiscreteCoordinates(5, 1),
      new DiscreteCoordinates(15, 6), new DiscreteCoordinates(15, 29) };
  private int areaIndex;

  /**
   * Add all the areas
   */
  private void createAreas() {
    addArea(new Level0());
    addArea(new Level1());
    addArea(new Level2());
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {

    if (super.begin(window, fileSystem)) {
      areaIndex = 0;
      createAreas();
      Area area = setCurrentArea(areas[areaIndex], true);
      player = new SuperPacmanPlayer(area, PLAYER_SPAWN_COORDINATES[areaIndex]);
      initPlayer(player);
      return true;
    }
    return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);

  }

  @Override
  public void end() {
  }

  @Override
  public String getTitle() {
    return "Super Pacman";
  }

  // player.leaveArea();
  // player.enterArea(currentArea, startingPositions[areaIndex]);
}
