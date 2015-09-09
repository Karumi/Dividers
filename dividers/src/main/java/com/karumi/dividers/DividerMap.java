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

import java.util.Collection;

/**
 * Internal representation for all the dividers.
 * This class holds an internal representation of the grid being rendered and is responsible for
 * resolving which layer should be rendered in a specific location.
 */
class DividerMap {

  private final Collection<Layer> layers;
  private final DividerMapCache cache;

  public DividerMap(Collection<Layer> layers) {
    this.layers = layers;
    this.cache = new DividerMapCache();
  }

  /**
   * Returns the divider for a given position.
   * It resolves which layer should be drawn in every different side of the item.
   *
   * @param position Position of the divider
   * @param positionAdapter The position adapter used for the view
   * @return Divider for the position, properly resolved
   */
  public Divider getDivider(Position position, PositionAdapter positionAdapter) {
    if (cache.containsPosition(position)) {
      return cache.get(position);
    }

    Divider divider = getDivider(position);

    /**
     * Once we have the divider for a single position we need to see if there is a surrounding
     * item containing a layer that has more priority.
     *
     * East position can override all north-east, east and south-east sides.
     * South-east position can only override the south-east side.
     * South position can override all south-west, south and south-east sides.
     */
    Position eastPosition = positionAdapter.getAdjacentItemPosition(position, Direction.EAST);
    Position southPosition = positionAdapter.getAdjacentItemPosition(position, Direction.SOUTH);
    Position southEastPosition =
        positionAdapter.getAdjacentItemPosition(position, Direction.SOUTH_EAST);

    Divider eastDivider = getDivider(eastPosition);
    Divider southDivider = getDivider(southPosition);
    Divider southEastDivider = getDivider(southEastPosition);

    divider = divider.overrideSublayer(eastDivider, Direction.NORTH_WEST, Direction.NORTH_EAST);
    divider = divider.overrideSublayer(eastDivider, Direction.WEST, Direction.EAST);
    divider = divider.overrideSublayer(eastDivider, Direction.SOUTH_WEST, Direction.SOUTH_EAST);
    divider = divider.overrideSublayer(southDivider, Direction.NORTH, Direction.SOUTH);
    divider = divider.overrideSublayer(southDivider, Direction.NORTH_WEST, Direction.SOUTH_WEST);
    divider = divider.overrideSublayer(southDivider, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    divider =
        divider.overrideSublayer(southEastDivider, Direction.NORTH_WEST, Direction.SOUTH_EAST);

    cache.put(position, divider);

    return divider;
  }

  /**
   * Returns the divider applied for the selected position taking into account
   * only the selectors for that position.
   *
   * @param position Position of the divider
   * @return Divider for the position, properly resolved
   */
  private Divider getDivider(Position position) {
    Divider itemDivider = DividerBuilder.fromEmpty().build();

    if (!position.isValid()) {
      return itemDivider;
    }

    int depth = 0;
    for (Layer layer : layers) {
      if (layer.isPositionSelected(position)) {
        itemDivider =
            itemDivider.override(layer.getDivider(), layer.getDirectionsByPosition(position),
                depth);
      }
      depth += 1;
    }

    return itemDivider;
  }
}
