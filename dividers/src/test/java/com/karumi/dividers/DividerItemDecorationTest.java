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
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.karumi.dividers.selector.AllItemsSelector;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DividerItemDecorationTest {

  @Mock Canvas canvasMock;
  @Mock RecyclerView recyclerViewMock;
  @Mock RecyclerView.State recyclerViewStateMock;
  @Mock PositionAdapter positionAdapterMock;

  private DividerItemDecoration dividerItemDecoration;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test public void shouldDrawAllEightAreasForADrawable() {
    givenThatThereIsOneCellToRender();
    Drawable drawableMock = mock(Drawable.class);
    List<Layer> layers = getSingleDrawableLayer(drawableMock);
    dividerItemDecoration = new DividerItemDecoration(layers);

    dividerItemDecoration.onDrawOver(canvasMock, recyclerViewMock, recyclerViewStateMock);

    verify(drawableMock, times(8)).draw(Matchers.eq(canvasMock));
  }

  @Test public void shouldDrawOneCorner() {
    givenThatThereIsOneCellToRender();
    Drawable drawableMock = mock(Drawable.class);
    List<Layer> layers = getSingleCornerDrawableLayer(drawableMock);
    dividerItemDecoration = new DividerItemDecoration(layers);

    dividerItemDecoration.onDrawOver(canvasMock, recyclerViewMock, recyclerViewStateMock);

    verify(drawableMock).draw(Matchers.eq(canvasMock));
  }

  @Test public void shouldOverrideCornerWhenTwoLayersCollide() {
    givenThatThereIsOneCellToRender();
    Drawable drawableMock = mock(Drawable.class);
    Drawable overrideDrawableMock = mock(Drawable.class);
    List<Layer> layers = getSingleCornerDrawableLayer(drawableMock);
    layers.addAll(getSingleCornerDrawableLayer(overrideDrawableMock));
    dividerItemDecoration = new DividerItemDecoration(layers);

    dividerItemDecoration.onDrawOver(canvasMock, recyclerViewMock, recyclerViewStateMock);

    verify(overrideDrawableMock).draw(Matchers.eq(canvasMock));
  }

  private List<Layer> getSingleDrawableLayer(Drawable drawable) {
    List<Layer> layers = new ArrayList<>();
    layers.add(new Layer(new AllItemsSelector(), DividerBuilder.get().with(drawable).build()));
    return layers;
  }

  private List<Layer> getSingleCornerDrawableLayer(Drawable drawable) {
    List<Layer> layers = new ArrayList<>();
    layers.add(new Layer(new AllItemsSelector(),
        DividerBuilder.get().with(drawable, Direction.NORTH_EAST).build()));
    return layers;
  }

  private void givenThatThereIsOneCellToRender() {
    RecyclerView.LayoutManager layoutManagerMock = getLayoutManagerMock(1);
    when(recyclerViewMock.getLayoutManager()).thenReturn(layoutManagerMock);
    when(recyclerViewMock.getChildCount()).thenReturn(1);
    View childMock = getViewMock();
    when(recyclerViewMock.getChildAt(0)).thenReturn(childMock);
  }

  @NonNull private RecyclerView.LayoutManager getLayoutManagerMock(int childCount) {
    RecyclerView.LayoutManager layoutManagerMock = mock(RecyclerView.LayoutManager.class);
    when(layoutManagerMock.getChildCount()).thenReturn(childCount);
    return layoutManagerMock;
  }

  @NonNull private View getViewMock() {
    View child = mock(View.class);
    RecyclerView.LayoutParams layoutParams = mock(GridLayoutManager.LayoutParams.class);
    when(child.getLayoutParams()).thenReturn(layoutParams);
    return child;
  }
}
