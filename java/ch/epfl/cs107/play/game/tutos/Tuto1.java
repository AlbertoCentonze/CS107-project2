package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {
  private void createAreas() {
    addArea(new Village());
    setCurrentArea("zelda/Village", true);
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {
    if (super.begin(window, fileSystem)) {
      createAreas();
      return true;
    } else
      return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
  }

  @Override
  public String getTitle() {
    return "Tuto1";
  }

  @Override
  public void end() {
  }
}
