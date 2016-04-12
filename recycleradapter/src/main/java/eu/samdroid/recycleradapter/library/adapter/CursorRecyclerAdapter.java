package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.CursorViewHandler;
import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;
import eu.samdroid.recycleradapter.library.source.CursorDataSource;

/**
 * An easy adapter to map columns from a cursor to TextViews or ImageViews
 * defined in an XML file. You can specify which columns you want, which
 * views you want to display the columns, and the XML file that defines
 * the appearance of these views.
 *
 * @see android.widget.SimpleCursorAdapter
 *
 * Created by Richard Gottschalk.
 */
public class CursorRecyclerAdapter extends BasicRecyclerAdapter {

    private CursorDataSource dataSource;

    public CursorRecyclerAdapter(@LayoutRes int layout,
                                 String[] from, int[] to) {
        this(null, layout, from, to);
    }

    public CursorRecyclerAdapter(@Nullable Cursor cursor,
                                 @LayoutRes int layout,
                                 String[] from, int[] to) {
        // create dataSource
        dataSource = new CursorDataSource(cursor);
        setRecyclerAdapterDataSource(dataSource);

        // create viewHandler
        CursorViewHandler viewHandler = new CursorViewHandler(
                new CursorViewTypeInformation(layout, from, to));
        setRecyclerAdapterViewHandler(viewHandler);
    }

    @Nullable
    public Cursor swapCursor(@Nullable Cursor cursor) {
        Cursor oldCursor = dataSource.swapCursor(cursor);
        if (oldCursor != null) notifyItemRangeRemoved(0, oldCursor.getCount());
        if (cursor != null) notifyItemRangeInserted(0, cursor.getCount());
        return oldCursor;
    }
}
