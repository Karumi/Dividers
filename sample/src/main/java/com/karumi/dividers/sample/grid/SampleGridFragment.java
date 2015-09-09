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

package com.karumi.dividers.sample.grid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.karumi.dividers.DividerBuilder;
import com.karumi.dividers.DividerItemDecoration;
import com.karumi.dividers.Layer;
import com.karumi.dividers.LayersBuilder;
import com.karumi.dividers.sample.R;
import com.karumi.dividers.sample.grid.model.Movie;
import com.karumi.dividers.sample.grid.selector.HighRatingMovieSelector;
import com.karumi.dividers.selector.AllGroupSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SampleGridFragment extends Fragment {

  private static final int NUMBER_OF_COLUMNS = 2;
  private static final int HIGH_RATING_THRESHOLD = 90;

  @Bind(R.id.movies_view) RecyclerView gridView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.sample_grid_fragment, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeGridView();
  }

  private void initializeGridView() {
    final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), NUMBER_OF_COLUMNS);
    gridView.setHasFixedSize(true);
    gridView.setLayoutManager(layoutManager);

    List<Movie> movies = getItems();
    RecyclerView.ItemDecoration itemDecoration = getItemDecoration(movies);

    gridView.addItemDecoration(itemDecoration);
    gridView.setAdapter(new MoviesAdapter(movies, getContext()));
  }

  private List<Movie> getItems() {
    List<Movie> items = new ArrayList<>();

    items.add(new Movie("The Shawhank Rempetion", "Frank Darabont", 93, R.drawable.shawshank));
    items.add(new Movie("Pulp Fiction", "Quentin Tarantino", 89, R.drawable.pulp_fiction));
    items.add(new Movie("Fight Club", "David Fincher", 89, R.drawable.fight_club));
    items.add(new Movie("12 Angry Men", "Sidney Lumet", 89, R.drawable.angry_men));
    items.add(new Movie("The Godfather", "Francis Ford Coppola", 92, R.drawable.godfather));
    items.add(new Movie("The Godfather: Part II", "Francis Ford Coppola", 90, R.drawable.godfather2));
    items.add(new Movie("The Dark Knight", "Christopher Nolan", 89, R.drawable.dark_knight));
    items.add(new Movie("The Good, the Bad and the Ugly", "Sergio Leone", 89,
        R.drawable.good_bad_ugly));
    items.add(new Movie("The Lord of the Rings: The Return of the King", "Peter Jackson", 89,
        R.drawable.lord_of_the_rings));
    items.add(new Movie("Schindler's List", "Steven Spielberg", 89, R.drawable.schindlers));

    return items;
  }

  private RecyclerView.ItemDecoration getItemDecoration(List<Movie> movies) {
    Drawable darkDrawable = getResources().getDrawable(R.drawable.movies_dark_divider);
    Drawable lightDrawable = getResources().getDrawable(R.drawable.movies_light_divider);
    Drawable highlightedDrawable = getResources().getDrawable(R.drawable.movies_highlight_divider);

    Layer defaultLayer = new Layer(DividerBuilder.from(lightDrawable).build());
    Layer externalLayer = new Layer(
        new AllGroupSelector(),
        DividerBuilder.from(darkDrawable).build());
    Layer highRatedLayer = new Layer(
        new HighRatingMovieSelector(movies, HIGH_RATING_THRESHOLD),
        DividerBuilder.from(highlightedDrawable).build());
    Collection<Layer> layers =
        LayersBuilder.with(defaultLayer, externalLayer, highRatedLayer).build();

    return new DividerItemDecoration(layers);
  }
}
