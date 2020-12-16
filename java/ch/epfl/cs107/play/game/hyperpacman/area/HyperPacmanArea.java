package ch.epfl.cs107.play.game.hyperpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.hyperpacman.actor.HyperPacmanPlayer;
import ch.epfl.cs107.play.game.hyperpacman.generator.Generator;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanArea extends Area {
  int size;
  HyperPacmanBehavior behavior;
  Generator mazeGenerator;
  HyperPacmanPlayer player1;
  HyperPacmanPlayer player2;
  Key victoryKey;

  /**
   * Default constructor for HyperPacmanArea
   * 
   * @param size (int): the size of the random arena
   */
  public HyperPacmanArea(int size) {
    this.size = size;
    mazeGenerator = new Generator(size);
  }

  /** Creates the area by initializing its corresponding behavior */
  protected void createArea() {
    behavior.registerActors(this, victoryKey);
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
      victoryKey = new Key(this, new DiscreteCoordinates(getHeight() / 2, getWidth() / 2));
      player1 = new HyperPacmanPlayer(this, new DiscreteCoordinates(1, 1), false);
      player2 = new HyperPacmanPlayer(this, new DiscreteCoordinates(size - 2, size - 2), true);
      createArea();
      return true;
    }
    return false;
  }

  @Override
  public float getCameraScaleFactor() {
    return 35f;
  }
}
