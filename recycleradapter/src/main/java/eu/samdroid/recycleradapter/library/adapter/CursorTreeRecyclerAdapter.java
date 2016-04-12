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

    public void setChildrenCursor(int groupIndex, @Nullable Cursor data) {
        int originalChildrenCount = getRecyclerAdapterDataSource().getChildrenCount(groupIndex);
        int start = getRecyclerAdapterDataSource().getGroupPosition(groupIndex);
        boolean isExistingGroup = groupIndex < getRecyclerAdapterDataSource().getGroupCount();

        getRecyclerAdapterDataSource().setChildrenCursor(groupIndex, data);

        if (isExistingGroup) {
            if (data == null) {
                notifyItemRangeRemoved(start, originalChildrenCount);
            } else {
                if (originalChildrenCount > 0) notifyItemRangeRemoved(start, originalChildrenCount);
                notifyItemRangeInserted(start + 1, data.getCount());
            }
        } else {
            if (data == null) {
                notifyItemRangeRemoved(start, originalChildrenCount);
            } else {
                notifyItemRangeInserted(start, data.getCount());
            }
        }
    }

    @NonNull
    @Override
    protected CursorTreeDataSource getRecyclerAdapterDataSource() {
        return (CursorTreeDataSource) super.getRecyclerAdapterDataSource();
    }
}
