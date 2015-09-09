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

/**
 * Representation of a point in the grid.
 */
public class Position {

  public static final Position INVALID_POSITION = new Position(-1, -1, -1, -1, -1);

  private final int absoluteIndex;
  private final int column;
  private final int row;
  private final int lastColumn;
  private final int lastRow;

  public Position(int absoluteIndex, int column, int row, int lastColumn, int lastRow) {
    this.absoluteIndex = absoluteIndex;
    this.column = column;
    this.row = row;
    this.lastColumn = lastColumn;
    this.lastRow = lastRow;
  }

  public boolean isFirstRow() {
    return getRow() == 0;
  }

  public boolean isLastRow() {
    return getRow() == lastRow;
  }

  public boolean isFirstColumn() {
    return getColumn() == 0;
  }

  public boolean isLastColumn() {
    return getColumn() == lastColumn;
  }

  public int getAbsoluteIndex() {
    return absoluteIndex;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public boolean isValid() {
    return !equals(INVALID_POSITION);
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position that = (Position) o;
    return column == that.column && row == that.row;
  }

  @Override public int hashCode() {
    int result = column;
    result = 31 * result + row;
    return result;
  }

  @Override public String toString() {
    return "Position{" + "column=" + column + ", row=" + row + '}';
  }
}
