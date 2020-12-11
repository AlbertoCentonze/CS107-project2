package ch.epfl.cs107.play.game.hyperpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.game.hyperpacman.generator.Generator;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanBehavior extends SuperPacmanBehavior {

  public HyperPacmanBehavior(Window window, String name) {
    super(window, name);
    // TODO Auto-generated constructor stub
  }

  public void generateMaze(Area area) {
    int size = 50;
    Generator mazeGenerator = new Generator(size);
    boolean[][] maze = mazeGenerator.getMaze();
    for (int i = 0; i < maze.length - 1; ++i) {
      for (int j = 0; j < maze[j].length - 1; ++j) {
        if (maze[i][j])
          area.registerActor(new Wall(area, new DiscreteCoordinates(i, j),
              new boolean[][] { { true, true, true }, { true, true, true }, { true, true, true } }));
      }
    }
  }

}
