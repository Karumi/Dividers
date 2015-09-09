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

package com.karumi.dividers.sample.grid.selector;

import com.karumi.dividers.Direction;
import com.karumi.dividers.Position;
import com.karumi.dividers.sample.grid.model.Movie;
import com.karumi.dividers.selector.Selector;
import java.util.EnumSet;
import java.util.List;

/**
 * Selector for movies with a high rating.
 */
public class HighRatingMovieSelector implements Selector {

  private final List<Movie> movies;
  private final int highRatingThreshold;

  public HighRatingMovieSelector(List<Movie> movies, int highRatingThreshold) {
    this.movies = movies;
    this.highRatingThreshold = highRatingThreshold;
  }

  @Override public boolean isPositionSelected(Position position) {
    return movies.get(position.getAbsoluteIndex()).getRating() >= highRatingThreshold;
  }

  @Override public EnumSet<Direction> getDirectionsByPosition(Position position) {
    return EnumSet.allOf(Direction.class);
  }
}
