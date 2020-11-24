package ch.epfl.cs107.play.game.tutos.area.tuto2;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.Tuto2Area;
import ch.epfl.cs107.play.math.Vector;

public class Ferme extends Tuto2Area {
  Entity ghost2 = new SimpleGhost(new Vector(1, 1), "ghost.1");

  @Override
  public String getTitle() {
    return "zelda/Ferme";
  }

  public void createArea() {
    registerActor(ghost2);
    registerActor(new Background(this));
    registerActor(new Foreground(this));
  }

  public float getCameraScaleFactor() {
    return 10.f;
  }
}