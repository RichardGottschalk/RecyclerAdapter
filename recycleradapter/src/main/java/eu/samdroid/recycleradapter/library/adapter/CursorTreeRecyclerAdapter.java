package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.samdroid.recycleradapter.library.binding.CursorViewHandler;
import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;
import eu.samdroid.recycleradapter.library.source.CursorTreeDataSource;

/**
 * @author Richard Gottschalk
 */
public class CursorTreeRecyclerAdapter extends TreeRecyclerAdapter {

    public CursorTreeRecyclerAdapter(CursorViewTypeInformation... cursorViewTypeInformations) {
        // create dataSource
        CursorTreeDataSource dataSource = new CursorTreeDataSource();
        dataSource.setExpandable(false);
        setRecyclerAdapterDataSource(dataSource);

        // create viewHandler
        CursorViewHandler viewHandler = new CursorViewHandler(cursorViewTypeInformations);
        setRecyclerAdapterViewHandler(viewHandler);
    }

    @Nullable
    public Cursor swapGroupCursor(@Nullable Cursor groups) {
        Cursor oldCursor = getRecyclerAdapterDataSource().swapGroupCursor(groups);
        notifyDataSetChanged();
        return oldCursor;
    }

    public void setChildrenCursor(@Nullable Cursor data, int ... columns) {
        if (columns == null) columns = new int[] {0};
        getRecyclerAdapterDataSource().setChildrenCursor(data, columns);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    protected CursorTreeDataSource getRecyclerAdapterDataSource() {
        return (CursorTreeDataSource) super.getRecyclerAdapterDataSource();
    }
}
