package eu.samdroid.recycleradapter.library;

import android.support.annotation.Nullable;

import eu.samdroid.recycleradapter.library.source.RecyclerAdapterDataSource;

/**
 * Created by Richard Gottschalk.
 */
final class BasicRecyclerAdapterDataSource<DATA> implements RecyclerAdapterDataSource<DATA> {

    @Override
    public int getVisibleDataCount() {
        return 0;
    }

    @Nullable
    @Override
    public DATA getVisibleData(int position) {
        return null;
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
