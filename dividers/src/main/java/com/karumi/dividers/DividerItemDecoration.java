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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

/**
 * Implementation of RecyclerView.ItemDecoration to display dividers between items.
 * To use it just create a new instance of this class properly initialized and set it as the
 * ItemDecoration of your RecyclerView.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

  private final DividerMap dividerMap;
  private PositionAdapter positionAdapter;
  private int offset;

  public DividerItemDecoration(Layer layer, int offset) {
    this(layer);
    this.offset = offset;
  }

  public DividerItemDecoration(Collection<Layer> layers, int offset) {
    this(layers);
    this.offset = offset;
  }

  public DividerItemDecoration(Layer layer) {
    this(Collections.singletonList(layer));
  }

  public DividerItemDecoration(Collection<Layer> layers) {
    dividerMap = new DividerMap(layers);
  }

  @Override public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    if (positionAdapter == null) {
      positionAdapter = new PositionAdapter(parent.getLayoutManager(), offset);
    }

    int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = parent.getChildAt(i);
      int absolutePosition = parent.getChildAdapterPosition(child);
      Position position = positionAdapter.getPositionByIndex(absolutePosition);
      Divider divider = dividerMap.getDivider(position, positionAdapter);
      drawDivider(canvas, child, divider, position);
    }
  }

  /**
   * Draw the provided divider.
   * Each divider is responsible for drawing some of his surroundings.
   * Drawing priorities follows a top-to-bottom and left-to-right rule, this means
   * that the north-west item will draw all his dividers all the other north cells will draw
   * all but west separators, and west cells will draw all but north separators. The rest
   * of the cells will draw only east, south-east and south dividers:
   *  ___  ___  ___
   * |   |    |    |
   * |___| ___| ___|
   *
   * |   |    |    |
   * |___| ___| ___|
   *
   * |   |    |    |
   * |___| ___| ___|
   */
  private void drawDivider(Canvas canvas, View view, Divider divider, Position position) {
    EnumSet<Direction> directions =
        EnumSet.of(Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH);

    if (position.getColumn() == 0) {
      directions.add(Direction.SOUTH_WEST);
      directions.add(Direction.WEST);
    }

    if (position.getRow() == 0) {
      directions.add(Direction.NORTH);
      directions.add(Direction.NORTH_EAST);
    }

    if (position.getColumn() == 0 && position.getRow() == 0) {
      directions.add(Direction.NORTH_WEST);
    }

    divider.draw(canvas, view, directions);
  }
}
