package ch.epfl.cs107.play.game.rpg.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Axis;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Gate extends AreaEntity {
  Sprite gateSprite;

  public Gate(Area area, Axis axis, DiscreteCoordinates position) {
    super(area, axis.toOrientation(), position);
    gateSprite = new Sprite("superpacman/gate", 1.f, 1.f, this,
        new RegionOfInterest(0, axis == Axis.HORIZONTAL ? 64 : 0, 64, 64));
  }

  @Override
  public void draw(Canvas canvas) {
    gateSprite.draw(canvas);
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

}
