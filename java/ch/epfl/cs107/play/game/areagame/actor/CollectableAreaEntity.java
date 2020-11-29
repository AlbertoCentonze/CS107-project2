package ch.epfl.cs107.play.game.areagame.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class CollectableAreaEntity extends AreaEntity {

  public CollectableAreaEntity(Area area, DiscreteCoordinates position, String spriteName) {
    // super(area, Orientation.DOWN, position);
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<DiscreteCoordinates> getCurrentCells() {
    return Collections.singletonList(getCurrentMainCellCoordinates());
  }

  @Override
  public boolean takeCellSpace() {
    return false;
  }

  @Override
  public boolean isCellInteractable() {
    return true;
  }

  @Override
  public boolean isViewInteractable() {
    return false;
  }

  @Override
  public void acceptInteraction(AreaInteractionVisitor v) {
    // TODO Auto-generated method stub

  }

  @Override
  public void draw(Canvas canvas) {
    // TODO Auto-generated method stub

  }

}
