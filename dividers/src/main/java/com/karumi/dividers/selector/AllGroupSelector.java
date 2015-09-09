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

package com.karumi.dividers.selector;

import com.karumi.dividers.Direction;
import com.karumi.dividers.Position;
import java.util.Arrays;
import java.util.EnumSet;

public class AllGroupSelector extends AllItemsSelector {

  @Override public EnumSet<Direction> getDirectionsByPosition(Position position) {
    EnumSet<Direction> directions = EnumSet.noneOf(Direction.class);

    if (position.isFirstRow()) {
      directions.addAll(Arrays.asList(Direction.NORTH_WEST, Direction.NORTH, Direction.NORTH_EAST));
    }
    if (position.isLastRow()) {
      directions.addAll(Arrays.asList(Direction.SOUTH_WEST, Direction.SOUTH, Direction.SOUTH_EAST));
    }
    if (position.isFirstColumn()) {
      directions.addAll(Arrays.asList(Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST));
    }
    if (position.isLastColumn()) {
      directions.addAll(Arrays.asList(Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST));
    }

    return directions;
  }
}
