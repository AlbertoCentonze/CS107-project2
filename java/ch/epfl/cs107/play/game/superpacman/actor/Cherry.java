package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;

public class Cherry extends CollectableAreaEntity {
  public static final int VALUE = 200;
  Sprite cherrySprite;

  /**
   * Default constructor for Cherry
   * 
   * @param area     (Area): area of the cherry
   * @param position (Position): position where the cherry has to be spawned
   */
  public Cherry(Area area, DiscreteCoordinates position) {
    super(area, position);
    cherrySprite = new Sprite("superpacman/cherry", 1.f, 1.f, this);
  }

  @Override
  public void draw(Canvas canvas) {
    cherrySprite.draw(canvas);
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    ((SuperPacmanInteractionVisitor) v).interactWith(this);
    super.acceptInteraction(v);
  }
}
