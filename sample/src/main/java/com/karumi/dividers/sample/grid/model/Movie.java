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

package com.karumi.dividers.sample.grid.model;

public class Movie {

  private final String title;
  private final String director;
  private final int rating;
  private final int imageResourceId;

  public Movie(String title, String director, int rating, int imageResourceId) {
    this.title = title;
    this.director = director;
    this.rating = rating;
    this.imageResourceId = imageResourceId;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public int getRating() {
    return rating;
  }

  public int getCoverResourceId() {
    return imageResourceId;
  }
}
