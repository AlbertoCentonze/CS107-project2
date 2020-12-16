package ch.epfl.cs107.play.game.hyperpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.hyperpacman.actor.HyperPacmanPlayer1;
import ch.epfl.cs107.play.game.hyperpacman.actor.HyperPacmanPlayer2;
import ch.epfl.cs107.play.game.hyperpacman.generator.Generator;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanArea extends Area {
  int size;
  HyperPacmanBehavior behavior;
  Generator mazeGenerator;
  HyperPacmanPlayer1 player1;
  HyperPacmanPlayer2 player2;

  public HyperPacmanArea(int size) {
    this.size = size;
    mazeGenerator = new Generator(size);
    player1 = new HyperPacmanPlayer1(this, new DiscreteCoordinates(1, 1));
    player2 = new HyperPacmanPlayer2(this, new DiscreteCoordinates(size - 2, size - 2));
  }

  protected void createArea() {
    behavior.registerActors(this);
    registerActor(new Background(this));
    registerActor(player1);
    registerActor(player2);
  }

  @Override
  public String getTitle() {
    return "superpacman/random";
  }

  @Override
  public boolean begin(Window window, FileSystem fileSystem) {
    if (super.begin(window, fileSystem)) {
      Generator.arrayToImage(mazeGenerator.getMaze());
      behavior = new HyperPacmanBehavior(window, getTitle());
      setBehavior(behavior);
      createArea();
      return true;
    }
    return false;
  }

  @Override
  public float getCameraScaleFactor() {
    return 40;
  }
}
