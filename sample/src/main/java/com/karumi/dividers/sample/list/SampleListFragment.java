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

package com.karumi.dividers.sample.list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.karumi.dividers.sample.list.model.BookCover;
import com.karumi.dividers.sample.list.model.BookInformation;
import com.karumi.dividers.sample.list.model.Media;
import com.karumi.dividers.selector.AllItemsSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SampleListFragment extends Fragment {

  @Bind(R.id.books_view) RecyclerView recyclerView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.sample_list_fragment, container, false);
    ButterKnife.bind(this, view);
    initRecyclerView();
    return view;
  }

  private void initRecyclerView() {
    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(getItemDecoration());
    recyclerView.setAdapter(new MediaAdapter(getContext(), getItems()));
  }

  @NonNull private List<Media> getItems() {
    List<Media> items = new ArrayList<>();

    items.add(new BookInformation("Ender's Game", "Orson Scott Card"));
    items.add(new BookCover(R.drawable.cover_enders_game));
    items.add(new BookInformation("Foundation", "Isaac Asimov"));
    items.add(new BookCover(R.drawable.cover_foundation));
    items.add(new BookInformation("Hyperion", "Dan Simmons"));
    items.add(new BookCover(R.drawable.cover_hyperion));
    items.add(new BookInformation("Red Mars", "Kim Stanley Robinson"));
    items.add(new BookCover(R.drawable.cover_red_mars));
    items.add(new BookInformation("The Name of the Wind", "Patrick Rothfuss"));
    items.add(new BookCover(R.drawable.cover_the_name_of_the_wind));

    return items;
  }

  private RecyclerView.ItemDecoration getItemDecoration() {
    Drawable blackDrawable = getResources().getDrawable(R.drawable.black_divider);
    Drawable greyDrawable = getResources().getDrawable(R.drawable.grey_divider);

    Collection<Layer> layers = LayersBuilder.with(
        new Layer(new AllItemsSelector(), DividerBuilder.get().with(greyDrawable).build()),
        new Layer(new EvenCellRowSelector(), DividerBuilder.get().with(blackDrawable).build()))
        .build();

    return new DividerItemDecoration(layers);
  }
}
