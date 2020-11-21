package ch.epfl.cs107.play.game.tutos.area.tuto1;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.math.Vector;

public class Ferme extends SimpleArea {
  Entity ghost2 = new SimpleGhost(new Vector(1, 1), "ghost.1");

  

  @Override
  public String getTitle() {
    return "zelda/Ferme";
  }
  

  public void createArea() {
    registerActor(ghost2);

  }

  public float getCameraScaleFactor() {
    return 10.f;
  }
}
