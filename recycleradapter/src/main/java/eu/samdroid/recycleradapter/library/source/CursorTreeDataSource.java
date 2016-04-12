package eu.samdroid.recycleradapter.library.source;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;

/**
 * Created by Richard Gottschalk.
 */
public class CursorTreeDataSource extends AbstractTreeDataSource<Cursor, Cursor> {

    @Nullable
    private Cursor groups;

    @NonNull
    private SparseArrayCompat<Cursor> data = new SparseArrayCompat<>();

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return (groups == null) ? 0 : groups.getCount();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupIndex the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupIndex) {
        Cursor childrenCursor = getChildrenCursor(groupIndex);
        return childrenCursor == null ? 0 : childrenCursor.getCount();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupIndex the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Cursor getGroup(int groupIndex) {
        if (groups == null) return null;

        groups.moveToPosition(groupIndex);
        return groups;
    }

    @Nullable
    private Cursor getChildrenCursor(int groupIndex) {
        return data.get(groupIndex);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */
    @Override
    public Cursor getChild(int groupPosition, int childPosition) {
        Cursor childrenCursor = getChildrenCursor(groupPosition);

        if (childrenCursor != null) {
            childrenCursor.moveToPosition(childPosition);
            return childrenCursor;
        }
        return null;
    }

    @Nullable
    public synchronized Cursor swapGroupCursor(@Nullable Cursor groups) {
        Cursor oldCursor = this.groups;
        this.groups = groups;
        invalidateDataCount();
        return oldCursor;
    }

    public void setChildrenCursor(int groupIndex, @Nullable Cursor cursor) {
        data.put(groupIndex, cursor);
        invalidateDataCount();
    }
}
