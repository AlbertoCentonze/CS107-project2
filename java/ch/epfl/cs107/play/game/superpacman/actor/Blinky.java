package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Blinky extends Ghost {
  private static final int MAX = 4;
  private int frameCounter = 0;

  public Blinky(Area area, DiscreteCoordinates position) {
    super(area, position, "blinky");
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);

    ++frameCounter;
    if (frameCounter % GHOST_SPEED == 0)
      orientate(getNextOrientation());

  }

  @Override
  protected Orientation getNextOrientation() {
    int randomInt = RandomGenerator.getInstance().nextInt(MAX);
    return Orientation.fromInt(randomInt);
  }

}
