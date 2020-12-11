package ch.epfl.cs107.play.game.hyperpacman;

import java.util.Collections;
import java.util.List;

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
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class HyperPacman extends RPG {
  public final static float STEP = 0.05f;

  private SuperPacmanPlayer player;

  List<SuperPacmanArea> levels;

  private void createAreas() {
    addArea(new Level0());
    addArea(new Level1());
    addArea(new Level2());
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {

    if (super.begin(window, fileSystem)) {
      createAreas();
      Area area = setCurrentArea("superpacman/Level0", true);
      player = new SuperPacmanPlayer(area, Level0.PLAYER_SPAWN_POSITION);
      initPlayer(player);
      return true;
    }
    return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    SuperPacmanArea area = ((SuperPacmanArea) getCurrentArea());
    area.updateAreaState(player);
  }

  @Override
  public void end() {
  }

  @Override
  public String getTitle() {
    return "Super Pacman";
  }

}
