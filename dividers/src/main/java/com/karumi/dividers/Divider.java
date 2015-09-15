/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.dividers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of how a single item must be rendered.
 * It makes it possible to specify different drawables for different zones of the item.
 */
public class Divider {

  static final Drawable ERASER = null;

  private static final Map<Direction, Area> AREAS = new HashMap<>();

  static {
    AREAS.put(Direction.WEST, new Area(Area.HorizontalMarker.LEFT, Area.HorizontalMarker.INNER_LEFT,
        Area.VerticalMarker.INNER_TOP, Area.VerticalMarker.INNER_BOTTOM));
    AREAS.put(Direction.NORTH_WEST,
        new Area(Area.HorizontalMarker.LEFT, Area.HorizontalMarker.INNER_LEFT,
            Area.VerticalMarker.TOP, Area.VerticalMarker.INNER_TOP));
    AREAS.put(Direction.NORTH,
        new Area(Area.HorizontalMarker.LEFT, Area.HorizontalMarker.INNER_RIGHT,
            Area.VerticalMarker.TOP, Area.VerticalMarker.INNER_TOP));
    AREAS.put(Direction.NORTH_EAST,
        new Area(Area.HorizontalMarker.INNER_RIGHT, Area.HorizontalMarker.RIGHT,
            Area.VerticalMarker.TOP, Area.VerticalMarker.INNER_TOP));
    AREAS.put(Direction.EAST,
        new Area(Area.HorizontalMarker.INNER_RIGHT, Area.HorizontalMarker.RIGHT,
            Area.VerticalMarker.INNER_TOP, Area.VerticalMarker.INNER_BOTTOM));
    AREAS.put(Direction.SOUTH_EAST,
        new Area(Area.HorizontalMarker.INNER_RIGHT, Area.HorizontalMarker.RIGHT,
            Area.VerticalMarker.INNER_BOTTOM, Area.VerticalMarker.BOTTOM));
    AREAS.put(Direction.SOUTH,
        new Area(Area.HorizontalMarker.LEFT, Area.HorizontalMarker.INNER_RIGHT,
            Area.VerticalMarker.INNER_BOTTOM, Area.VerticalMarker.BOTTOM));
    AREAS.put(Direction.SOUTH_WEST,
        new Area(Area.HorizontalMarker.LEFT, Area.HorizontalMarker.INNER_LEFT,
            Area.VerticalMarker.INNER_BOTTOM, Area.VerticalMarker.BOTTOM));
  }

  private final EnumMap<Direction, Sublayer> sublayers = new EnumMap<>(Direction.class);

  private Divider(Divider copy) {
    for (EnumMap.Entry<Direction, Sublayer> sublayer : copy.sublayers.entrySet()) {
      sublayers.put(sublayer.getKey(), sublayer.getValue());
    }
  }

  Divider(EnumMap<Direction, Sublayer> sublayers) {
    for (EnumMap.Entry<Direction, Sublayer> sublayer : sublayers.entrySet()) {
      this.sublayers.put(sublayer.getKey(), sublayer.getValue());
    }
  }

  void draw(Canvas canvas, View view, EnumSet<Direction> directions) {
    for (Direction direction : directions) {
      if (hasSublayer(direction)) {
        Drawable drawable = getDrawable(direction);
        if (drawable != null) {
          drawArea(AREAS.get(direction), view, drawable, canvas);
        }
      }
    }
  }

  Divider override(Divider override, EnumSet<Direction> overrideDirections, int depth) {
    Divider overridden = new Divider(this);

    for (Direction direction : overrideDirections) {
      Sublayer sublayer = override.getSublayer(direction);

      if (sublayer != null && (!overridden.hasSublayer(direction) || depth >= getSublayer(direction)
          .getDepth())) {
        overridden.sublayers.put(direction, new Sublayer(sublayer.getDrawable(), depth));
      }
    }

    return overridden;
  }

  Divider overrideSublayer(Divider override, Direction sourceDirection,
      Direction targetDirection) {
    Divider overridden = new Divider(this);

    if (override.hasSublayer(sourceDirection)) {
      Sublayer overrideSublayer = override.getSublayer(sourceDirection);
      if (!overridden.hasSublayer(targetDirection) || overrideSublayer.isCloserThan(
          overridden.getSublayer(targetDirection))) {
        overridden.sublayers.put(targetDirection, overrideSublayer);
      }
    }

    return overridden;
  }

  private void drawArea(Area area, View view, Drawable drawable, Canvas canvas) {
    int left = area.getLeft(view, drawable);
    int top = area.getTop(view, drawable);
    int right = area.getRight(view, drawable);
    int bottom = area.getBottom(view, drawable);

    if (drawable != null) {
      drawable.setBounds(left, top, right, bottom);
      drawable.draw(canvas);
    }
  }

  private Sublayer getSublayer(Direction direction) {
    return sublayers.get(direction);
  }

  private boolean hasSublayer(Direction direction) {
    return sublayers.containsKey(direction);
  }

  private Drawable getDrawable(Direction direction) {
    return sublayers.get(direction).getDrawable();
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Divider divider = (Divider) o;

    return !(sublayers != null ? !sublayers.equals(divider.sublayers) : divider.sublayers != null);
  }

  @Override public int hashCode() {
    return sublayers != null ? sublayers.hashCode() : 0;
  }

  @Override public String toString() {
    return "Divider{" + "sublayers=" + sublayers + '}';
  }
}
