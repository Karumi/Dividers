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

import com.karumi.dividers.selector.AllItemsSelector;
import com.karumi.dividers.selector.Selector;
import java.util.EnumSet;

/**
 * A layer represents the most basic unit that can be rendered.
 * It defines a set of items that will be rendered and how they will be rendered.
 */
public class Layer implements Selector {

  private final Selector selector;
  private final Divider divider;

  public Layer(Divider divider) {
    this.selector = new AllItemsSelector();
    this.divider = divider;
  }

  public Layer(Selector selector, Divider divider) {
    this.selector = selector;
    this.divider = divider;
  }

  @Override public boolean isPositionSelected(Position position) {
    return selector.isPositionSelected(position);
  }

  @Override public EnumSet<Direction> getDirectionsByPosition(Position position) {
    return selector.getDirectionsByPosition(position);
  }

  public Divider getDivider() {
    return divider;
  }
}
