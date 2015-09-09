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

package com.karumi.dividers.sample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.karumi.dividers.sample.grid.SampleGridFragment;
import com.karumi.dividers.sample.list.SampleListFragment;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

  private final Context context;

  private final FragmentPage[] pages = {
      new FragmentPage(new SampleGridFragment(), R.string.movies_title),
      new FragmentPage(new SampleListFragment(), R.string.books_title),
  };

  public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override public android.support.v4.app.Fragment getItem(int position) {
    return pages[position].getFragment();
  }

  @Override public CharSequence getPageTitle(int position) {
    return context.getString(pages[position].getNameId());
  }

  @Override public int getCount() {
    return pages.length;
  }

  private class FragmentPage {
    private final Fragment fragment;
    private final int nameId;

    public FragmentPage(Fragment fragment, int nameId) {
      this.fragment = fragment;
      this.nameId = nameId;
    }

    public Fragment getFragment() {
      return fragment;
    }

    public int getNameId() {
      return nameId;
    }
  }

}
