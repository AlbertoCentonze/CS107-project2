package ch.epfl.cs107.play.game.hyperpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.hyperpacman.area.HyperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class HyperPacman extends AreaGame {
  private void createAreas() {
    addArea(new HyperPacmanArea(25));
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {

    if (super.begin(window, fileSystem)) {
      createAreas();
      Area area = setCurrentArea("superpacman/random", true);
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
    return "Hyper Pacman";
  }

}
