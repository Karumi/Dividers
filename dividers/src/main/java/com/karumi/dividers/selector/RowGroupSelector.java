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
import java.util.EnumSet;

/**
 * Selector that applies to all the items in a single row grouping them as a single item.
 */
public class RowGroupSelector extends AllItemsInRowSelector {

  public RowGroupSelector(int row) {
    super(row);
  }

  @Override public EnumSet<Direction> getDirectionsByPosition(Position position) {
    EnumSet<Direction> directions = EnumSet.complementOf(
        EnumSet.of(Direction.WEST, Direction.EAST));

    if (position.isFirstColumn()) {
      directions.add(Direction.WEST);
    }
    if (position.isLastColumn()) {
      directions.add(Direction.EAST);
    }

    return directions;
  }
}
