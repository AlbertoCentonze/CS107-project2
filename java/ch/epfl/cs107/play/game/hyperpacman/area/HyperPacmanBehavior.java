package ch.epfl.cs107.play.game.hyperpacman.area;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.window.Window;

public class HyperPacmanBehavior extends SuperPacmanBehavior {
  public HyperPacmanBehavior(Window window, String name) {
    super(window, name);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void registerActors(Area area) {
    super.registerActors(area);
  }

}
