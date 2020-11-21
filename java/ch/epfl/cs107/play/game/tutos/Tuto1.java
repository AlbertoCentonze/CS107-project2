package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.actor.Player;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {
  private SimpleArea village = new Village();
  private Player ghostPlayer = new Player(new Vector(15, 7), "ghost.2");
  private SimpleArea ferme = new Ferme();

  private void createAreas() {
    addArea(village);
    addArea(ferme);
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {
    if (super.begin(window, fileSystem)) {
      createAreas();
      setCurrentArea("zelda/Village", true);
      village.registerActor(ghostPlayer);
      village.setViewCandidate(ghostPlayer);
      return true;
    } else
      return false;
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    if (ghostPlayer.isWeak()) {
      switchArea();
    }

    Keyboard keyboard = getWindow().getKeyboard();
    Button up = keyboard.get(Keyboard.UP);
    Button down = keyboard.get(Keyboard.DOWN);
    Button right = keyboard.get(Keyboard.RIGHT);
    Button left = keyboard.get(Keyboard.LEFT);
    if (up.isDown()) {
      ghostPlayer.move(new Vector(0, 1));
    }
    if (down.isDown()) {
      ghostPlayer.move(new Vector(0, -1));
    }
    if (right.isDown()) {
      ghostPlayer.move(new Vector(1, 0));
    }
    if (left.isDown()) {
      ghostPlayer.move(new Vector(-1, 0));
    }
  }

  @Override
  public String getTitle() {
    return "Game";
  }

  @Override
  public void end() {
  }

  private void switchArea() {
    if (getCurrentArea().getTitle().equals("zelda/Village")) {
      village.unregisterActor(ghostPlayer);
      setCurrentArea("zelda/Ferme", true);
      ferme.registerActor(ghostPlayer);
      ghostPlayer.strengthen();
      ferme.setViewCandidate(ghostPlayer);
    } else if (getCurrentArea().getTitle().equals("zelda/Ferme")) {
      ferme.unregisterActor(ghostPlayer);
      setCurrentArea("zelda/Village", true);
      village.registerActor(ghostPlayer);
      ghostPlayer.strengthen();
      village.setViewCandidate(ghostPlayer);
    }

  }
}
