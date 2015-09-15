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

import java.util.EnumSet;

/**
 * All the possible directions to move through a grid.
 */
public enum Direction {
  WEST,
  NORTH_WEST,
  NORTH,
  NORTH_EAST,
  EAST,
  SOUTH_EAST,
  SOUTH,
  SOUTH_WEST;

  public static EnumSet<Direction> getAllWest() {
    return EnumSet.of(SOUTH_WEST, WEST, NORTH_WEST);
  }

  public static EnumSet<Direction> getAllNorth() {
    return EnumSet.of(NORTH_WEST, NORTH, NORTH_EAST);
  }

  public static EnumSet<Direction> getAllEast() {
    return EnumSet.of(NORTH_EAST, EAST, SOUTH_EAST);
  }

  public static EnumSet<Direction> getAllSouth() {
    return EnumSet.of(SOUTH_WEST, SOUTH, SOUTH_EAST);
  }

  public static EnumSet<Direction> getNorthWestCorner() {
    return EnumSet.of(WEST, NORTH_WEST, NORTH);
  }

  public static EnumSet<Direction> getNorthEastCorner() {
    return EnumSet.of(NORTH, NORTH_EAST, EAST);
  }

  public static EnumSet<Direction> getSouthEastCorner() {
    return EnumSet.of(EAST, SOUTH_EAST, SOUTH);
  }

  public static EnumSet<Direction> getSouthWestCorner() {
    return EnumSet.of(SOUTH, SOUTH_WEST, WEST);
  }

  public static EnumSet<Direction> getAllButWest() {
    return EnumSet.complementOf(EnumSet.of(WEST));
  }

  public static EnumSet<Direction> getAllButNorth() {
    return EnumSet.complementOf(EnumSet.of(NORTH));
  }

  public static EnumSet<Direction> getAllButEast() {
    return EnumSet.complementOf(EnumSet.of(EAST));
  }

  public static EnumSet<Direction> getAllButSouth() {
    return EnumSet.complementOf(EnumSet.of(SOUTH));
  }

  public static EnumSet<Direction> getHorizontal() {
    return EnumSet.complementOf(EnumSet.of(WEST, EAST));
  }

  public static EnumSet<Direction> getVertical() {
    return EnumSet.complementOf(EnumSet.of(NORTH, SOUTH));
  }


}
