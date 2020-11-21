package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.actor.Player;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {
  private SimpleArea village = new Village();
  private Player player = new Player(new Vector(15, 7), "ghost.2");

  private void createAreas() {
    addArea(village);
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {
    if (super.begin(window, fileSystem)) {
      createAreas();
      setCurrentArea("zelda/Village", true);
      village.registerActor(player);
      return true;
    } else
      return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    Keyboard keyboard = getWindow().getKeyboard();
    Button up = keyboard.get(Keyboard.UP);
    Button down = keyboard.get(Keyboard.DOWN);
    Button right = keyboard.get(Keyboard.RIGHT);
    Button left = keyboard.get(Keyboard.LEFT);
    if (up.isDown()) {
      player.move(new Vector(0, 1));
    }
    if (down.isDown()) {
      player.move(new Vector(0, -1));
    }
    if (right.isDown()) {
      player.move(new Vector(1, 0));
    }
    if (left.isDown()) {
      player.move(new Vector(-1, 0));
    }
  }

  @Override
  public String getTitle() {
    return "Game";
  }

  @Override
  public void end() {
  }
}
