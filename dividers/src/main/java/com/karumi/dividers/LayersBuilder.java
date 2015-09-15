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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Utility builder for creating collections of layers.
 */
public class LayersBuilder {

  private Collection<Layer> layers;

  private LayersBuilder() {
    layers = new ArrayList<>();
  }

  private LayersBuilder(Collection<Layer> layers) {
    this();
    this.layers.addAll(layers);
  }

  /**
   * Returns a new LayersBuilder with all the provided layers included.
   */
  public static LayersBuilder with(Layer... layers) {
    return new LayersBuilder(Arrays.asList(layers));
  }

  /**
   * Returns a new LayersBuilder with all the provided layers included.
   */
  public static LayersBuilder from(Collection<Layer> layers) {
    return new LayersBuilder(layers);
  }

  /**
   * Returns the LayersBuilder with all the provided layers included.
   */
  public LayersBuilder add(Layer... layers) {
    this.layers.addAll(Arrays.asList(layers));
    return this;
  }

  /**
   * Returns a new collection of layers properly configured.
   */
  public Collection<Layer> build() {
    return layers;
  }
}
