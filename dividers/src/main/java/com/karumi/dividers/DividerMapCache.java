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

import java.util.HashMap;
import java.util.Map;

/**
 * Cache for the dividers map. Avoids having to recalculate all the overrides between
 * layers over and over again.
 */
class DividerMapCache {

  private final Map<Position, Divider> dividersCache;

  public DividerMapCache() {
    dividersCache = new HashMap<>();
  }

  public void put(Position key, Divider value) {
    dividersCache.put(key, value);
  }

  public boolean containsPosition(Position position) {
    return dividersCache.containsKey(position);
  }

  public Divider get(Position position) {
    return dividersCache.get(position);
  }
}
