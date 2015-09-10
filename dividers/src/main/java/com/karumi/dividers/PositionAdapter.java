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

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Translates absolute indices into positions in the grid.
 */
class PositionAdapter {

  private final RecyclerView.LayoutManager layoutManager;
  private final int offset;

  public PositionAdapter(RecyclerView.LayoutManager layoutManager, int offset) {
    this.layoutManager = layoutManager;
    this.offset = offset;
  }

  /**
   * Returns a position given the absolute index.
   */
  public Position getPositionByIndex(int index) {
    int numberOfColumns = getNumberOfColumns();
    int numberOfRows = getNumberOfRows();

    return new Position(
        index,
        (index - offset) % numberOfColumns,
        (index - offset) / numberOfColumns,
        numberOfColumns - 1,
        numberOfRows - 1);
  }

  /**
   * Returns the position adjacent to the provided one in a specific direction.
   */
  public Position getAdjacentItemPosition(Position position, Direction direction) {
    Position adjacent;

    Coordinate displacement = getDisplacement(direction);
    int adjacentColumn = position.getColumn() + displacement.getX();
    int adjacentRow = position.getRow() + displacement.getY();

    int numberOfRows = getNumberOfRows();
    int numberOfColumns = getNumberOfColumns();
    int numberOfItems = getNumberOfItems();

    if (adjacentColumn < 0 || adjacentColumn >= numberOfColumns
        || adjacentRow < 0 || adjacentRow > numberOfRows
        || adjacentColumn + numberOfColumns * adjacentRow >= numberOfItems) {
      adjacent = Position.INVALID_POSITION;
    } else {
      adjacent = new Position(
          numberOfColumns * adjacentRow + adjacentColumn,
          adjacentColumn,
          adjacentRow,
          numberOfColumns - 1,
          numberOfRows - 1);
    }

    return adjacent;
  }

  private int getNumberOfColumns() {
    int numberOfColumns = 1;

    if (layoutManager instanceof GridLayoutManager) {
      GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
      numberOfColumns = gridLayoutManager.getSpanCount();
    }

    return numberOfColumns;
  }

  private int getNumberOfRows() {
    int offset = getNumberOfItems() % getNumberOfColumns() == 0 ? 0 : 1;
    return offset + getNumberOfItems() / getNumberOfColumns();
  }

  private int getNumberOfItems() {
    return layoutManager.getItemCount();
  }

  private Coordinate getDisplacement(Direction direction) {
    switch (direction) {
      case WEST:
        return new Coordinate(-1, 0);
      case NORTH_WEST:
        return new Coordinate(-1, -1);
      case NORTH:
        return new Coordinate(0, -1);
      case NORTH_EAST:
        return new Coordinate(1, -1);
      case EAST:
        return new Coordinate(1, 0);
      case SOUTH_EAST:
        return new Coordinate(1, 1);
      case SOUTH:
        return new Coordinate(0, 1);
      case SOUTH_WEST:
      default:
        return new Coordinate(-1, 1);
    }
  }

  /**
   * Inner class representing a coordinate in the space
   */
  private class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }
}
