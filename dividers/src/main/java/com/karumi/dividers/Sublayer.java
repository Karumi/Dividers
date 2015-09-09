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

/**
 * Representation of a drawable inside a single item.
 * It holds a depth value to make layer overriding possible.
 */
class Sublayer {
  private final Drawable drawable;
  private final int depth;

  public Sublayer(Drawable drawable) {
    this(drawable, 0);
  }

  public Sublayer(Drawable drawable, int depth) {
    this.drawable = drawable;
    this.depth = depth;
  }

  public boolean isCloserThan(Sublayer sublayer) {
    return getDepth() > sublayer.getDepth();
  }

  public Drawable getDrawable() {
    return drawable;
  }

  public int getDepth() {
    return depth;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Sublayer sublayer = (Sublayer) o;

    return depth == sublayer.depth && !(drawable != null ? !drawable.equals(sublayer.drawable)
        : sublayer.drawable != null);
  }

  @Override public int hashCode() {
    int result = drawable != null ? drawable.hashCode() : 0;
    result = 31 * result + depth;
    return result;
  }

  @Override public String toString() {
    return "Sublayer{" + "drawable=" + drawable + ", depth=" + depth + '}';
  }
}
