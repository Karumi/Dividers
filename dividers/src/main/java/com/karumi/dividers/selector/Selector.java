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
 * A selector is responsible for deciding which items are affected by a layer and which
 * parts those affected items have to be rendered.
 */
public interface Selector {
  /**
   * Returns true whether the position is affected by this selector.
   */
  boolean isPositionSelected(Position position);

  /**
   * For an affected item, returns the directions that has to be rendered.
   */
  EnumSet<Direction> getDirectionsByPosition(Position position);
}
