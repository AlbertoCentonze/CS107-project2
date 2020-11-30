package ch.epfl.cs107.play.game.rpg.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Gate extends AreaEntity {
  Sprite gateSprite;

  public Gate(Area area, Orientation orientation, DiscreteCoordinates position) {
    super(area, orientation, position);
    gateSprite = new Sprite("superpacman/gate", 1.f, 1.f, this);
  }

  @Override
  public List<DiscreteCoordinates> getCurrentCells() {
    return Collections.singletonList(getCurrentMainCellCoordinates());
  }

  @Override
  public boolean takeCellSpace() {
    return true;
  }

  @Override
  public boolean isCellInteractable() {
    return false;
  }

  @Override
  public boolean isViewInteractable() {
    return false;
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
  }

  @Override
  public void draw(Canvas canvas) {
    gateSprite.draw(canvas);
  }

}
