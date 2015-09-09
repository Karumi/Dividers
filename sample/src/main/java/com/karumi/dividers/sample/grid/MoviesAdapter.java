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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.karumi.dividers.sample.R;
import com.karumi.dividers.sample.grid.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

  private final List<Movie> items;
  private final Context context;

  public MoviesAdapter(List<Movie> items, Context context) {
    this.items = items;
    this.context = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Movie item = items.get(position);

    holder.titleView.setText(item.getTitle());
    holder.directorView.setText(item.getDirector());
    holder.ratingView.setText(
        item.getRating() + context.getResources().getString(R.string.rating_by_100));
    Picasso.with(context)
        .load(item.getCoverResourceId())
        .placeholder(R.drawable.placeholder)
        .into(holder.coverView);

    holder.itemView.setTag(item);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.title_tv) TextView titleView;
    @Bind(R.id.director_tv) TextView directorView;
    @Bind(R.id.rating_tv) TextView ratingView;
    @Bind(R.id.cover_iv) ImageView coverView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
