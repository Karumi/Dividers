package com.karumi.dividers;

import org.junit.Test;

import android.graphics.drawable.Drawable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SublayerTest {

    @Test
    public void getDrawableReturnsMutatedDrawable() {
        final Drawable mockDrawable = mock(Drawable.class);
        Sublayer sublayer = new Sublayer(mockDrawable);

        sublayer.getDrawable();

        verify(mockDrawable).mutate();
    }
}