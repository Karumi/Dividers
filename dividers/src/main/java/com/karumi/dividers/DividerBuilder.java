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

import android.graphics.drawable.Drawable;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Utility class to create dividers.
 */
public class DividerBuilder {

  private final EnumMap<Direction, Sublayer> sublayers = new EnumMap<>(Direction.class);

  /**
   * Returns a new builder with no configuration.
   */
  public static DividerBuilder get() {
    return new DividerBuilder();
  }

  /**
   * Returns a new builder with the empty drawable applied to all the sides of the divider.
   */
  public static DividerBuilder fromEmpty() {
    return from(Divider.EMPTY_DRAWABLE);
  }

  /**
   * Returns a new builder with the drawable applied to all the sides of the divider.
   */
  public static DividerBuilder from(Drawable drawable) {
    DividerBuilder builder = new DividerBuilder();
    builder.with(drawable);
    return builder;
  }

  /**
   * Adds the drawable to all the sides of the divider.
   */
  public DividerBuilder with(Drawable drawable) {
    for (Direction direction : Direction.values()) {
      sublayers.put(direction, new Sublayer(drawable));
    }
    return this;
  }

  /**
   * Adds the drawable to the specific direction for the divider.
   */
  public DividerBuilder with(Drawable drawable, Direction direction) {
    sublayers.put(direction, new Sublayer(drawable));
    return this;
  }

  /**
   * Adds the drawable to all the directions provided for the divider.
   */
  public DividerBuilder with(Drawable drawable, EnumSet<Direction> directions) {
    for (Direction direction : directions) {
      sublayers.put(direction, new Sublayer(drawable));
    }
    return this;
  }

  /**
   * Returns a new Divider properly configured.
   */
  public Divider build() {
    return new Divider(sublayers);
  }
}
