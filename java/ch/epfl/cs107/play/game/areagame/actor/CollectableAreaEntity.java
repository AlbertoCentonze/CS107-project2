package ch.epfl.cs107.play.game.areagame.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class CollectableAreaEntity extends AreaEntity {

  public CollectableAreaEntity(Area area, DiscreteCoordinates position) {
    super(area, Orientation.DOWN, position);
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
    getOwnerArea().unregisterActor(this);
  }
}