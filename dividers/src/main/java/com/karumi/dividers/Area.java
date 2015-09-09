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
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Class to represent a single area in an item.
 * It assumes that the item has been split in a 3x3 grid:
 * +----+----+----+ TOP
 * |    |    |    |
 * +--------------+ INNER TOP
 * |    |    |    |
 * +--------------+ INNER BOTTOM
 * |    |    |    |
 * +----+----+----+ BOTTOM
 * LEFT |    |    |
 * INNER LEFT|    |
 *     INNER RIGHT|
 *              RIGHT
 */
class Area {

  private final HorizontalMarker left;
  private final HorizontalMarker right;
  private final VerticalMarker top;
  private final VerticalMarker bottom;

  public enum HorizontalMarker {
    LEFT, INNER_LEFT, INNER_RIGHT, RIGHT
  }

  public enum VerticalMarker {
    TOP, INNER_TOP, INNER_BOTTOM, BOTTOM
  }

  public Area(HorizontalMarker left, HorizontalMarker right, VerticalMarker top,
      VerticalMarker bottom) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }

  public int getLeft(View view, Drawable drawable) {
    return getHorizontalPosition(left, view, drawable);
  }

  public int getRight(View view, Drawable drawable) {
    return getHorizontalPosition(right, view, drawable);
  }

  public int getTop(View view, Drawable drawable) {
    return getVerticalPosition(top, view, drawable);
  }

  public int getBottom(View view, Drawable drawable) {
    return getVerticalPosition(bottom, view, drawable);
  }

  private int getHorizontalPosition(Area.HorizontalMarker marker, View view, Drawable drawable) {
    switch (marker) {
      case LEFT:
        return view.getLeft();
      case INNER_LEFT:
        return view.getLeft() + drawable.getIntrinsicWidth();
      case INNER_RIGHT:
        return view.getRight() - drawable.getIntrinsicWidth();
      case RIGHT:
      default:
        return view.getRight();
    }
  }

  private int getVerticalPosition(Area.VerticalMarker marker, View view, Drawable drawable) {
    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
    switch (marker) {
      case TOP:
        return view.getTop() + params.topMargin;
      case INNER_TOP:
        return view.getTop() + params.topMargin + drawable.getIntrinsicHeight();
      case INNER_BOTTOM:
        return view.getBottom() + params.bottomMargin;
      case BOTTOM:
      default:
        return view.getBottom() + params.bottomMargin + drawable.getIntrinsicHeight();
    }
  }
}
