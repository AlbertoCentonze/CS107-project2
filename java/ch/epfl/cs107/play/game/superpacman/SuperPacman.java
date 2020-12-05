package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.LevelManager;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class SuperPacman extends RPG {
  public final static float CAMERA_SCALE_FACTOR = 15.f;
  public final static float STEP = 0.05f;

  private SuperPacmanPlayer player;

  // TODO maybe we could create a level structure class
  LevelManager levels = new LevelManager(
      new String[] { "superpacman/Level0", "superpacman/Level1", "superpacman/Level2" }, new DiscreteCoordinates[] {
          new DiscreteCoordinates(5, 1), new DiscreteCoordinates(15, 6), new DiscreteCoordinates(15, 29) });

  /**
   * Add all the areas
   */
  private void createAreas() {
    addArea(new Level0(levels));
    addArea(new Level1(levels));
    addArea(new Level2(levels));
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {

    if (super.begin(window, fileSystem)) {
      createAreas();
      Area area = setCurrentArea(levels.getLevelName(0), true);
      player = new SuperPacmanPlayer(area, levels.getLevelSpawnCoordinates(0));
      initPlayer(player);
      return true;
    }
    return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    ((SuperPacmanArea) getCurrentArea()).setAreGhostsScared(player.getIsInvulnerable());
  }

  @Override
  public void end() {
  }

  @Override
  public String getTitle() {
    return "Super Pacman";
  }

}
