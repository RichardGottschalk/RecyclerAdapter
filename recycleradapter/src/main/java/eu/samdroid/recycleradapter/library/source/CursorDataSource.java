package eu.samdroid.recycleradapter.library.source;

import android.database.Cursor;
import android.support.annotation.Nullable;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

/**
 * Created by Richard Gottschalk.
 */
public class CursorDataSource implements RecyclerAdapterDataSource<Cursor> {

    private Cursor dataCursor;

    public CursorDataSource() {}

    public CursorDataSource(@Nullable Cursor dataCursor) {
        this.dataCursor = dataCursor;
    }

    @Override
    public int getVisibleDataCount() {
        return dataCursor == null ? 0 : dataCursor.getCount();
    }

    @Nullable
    @Override
    public Cursor getVisibleData(int position) {
        if (dataCursor == null) return null;
        dataCursor.moveToPosition(position);
        return dataCursor;
    }

    @Override
    public int getViewType(int position) {
        return ViewTypeConstants.VIEW_TYPE_DEFAULT;
    }

    @Nullable
    public synchronized Cursor swapCursor(@Nullable Cursor cursor) {
        if (cursor == dataCursor) return cursor;

        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        return oldCursor;
    }
}
