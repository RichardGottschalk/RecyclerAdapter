package eu.samdroid.recycleradapter.library.source;

import android.support.annotation.Nullable;

/**
 * Created by Richard Gottschalk.
 */
public interface RecyclerAdapterDataSource<DATA> {

    int getVisibleDataCount();

    @Nullable
    DATA getVisibleData(int position);

    int getViewType(int position);
}
