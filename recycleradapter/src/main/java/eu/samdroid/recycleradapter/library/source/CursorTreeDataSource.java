package eu.samdroid.recycleradapter.library.source;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Richard Gottschalk.
 */
public class CursorTreeDataSource extends AbstractTreeDataSource<Cursor, Cursor> {

    @Nullable
    private Cursor groups;

    @Nullable
    private Cursor data;

    @Nullable
    private List<Integer> groupFirstItemPositions;

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
        if (groupFirstItemPositions != null && groupIndex < groupFirstItemPositions.size()) {
            int firstItemIndex = groupFirstItemPositions.get(groupIndex);
            if (groupFirstItemPositions.size() > groupIndex + 1) {
                int lastItemIndex = groupFirstItemPositions.get(groupIndex + 1);
                return lastItemIndex - firstItemIndex;
            } else {
                if (data == null) return 0;
                return data.getCount() - firstItemIndex;
            }
        }
        return 0;
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

    public void setChildrenCursor(@Nullable Cursor cursor, int ... columns) {
        List<Integer> groupFirstItemPositions = new ArrayList<>();
        if (cursor != null) {
            groupFirstItemPositions = getFirstChildrenPositions(cursor, columns);
        }

        synchronized (this) {
            this.data = cursor;
            this.groupFirstItemPositions = groupFirstItemPositions;
        }
        invalidateDataCount();
    }

    private List<Integer> getFirstChildrenPositions(@NonNull Cursor cursor, int[] columns) {
        List<Integer> firstChildren = new ArrayList<>();

        if (cursor.moveToFirst()) {
            Map<Integer, Object> lastColumn = new HashMap<>();
            boolean initialized = false;

            do {
                boolean equals = true;
                for (int column : columns) {
                    Object value = cursor.getString(column);

                    if (!initialized) {
                        lastColumn.put(column, value);
                        equals = false;

                    } else {
                        Object last = lastColumn.get(column);
                        if ((value != null && !value.equals(last))
                                || (last != null && !last.equals(value))) {
                            // not equals
                            lastColumn.put(column, value);

                            equals = false;
                        }
                    }
                }
                initialized = true;

                if (!equals) {
                    firstChildren.add(cursor.getPosition());
                }
            } while (cursor.moveToNext());
        }

        return firstChildren;
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
        int groupFirstItemPosition = getGroupFirstChildPosition(groupPosition);
        if (data != null) {
            data.moveToPosition(groupFirstItemPosition + childPosition);
            return data;
        }
        return null;
    }

    private int getGroupFirstChildPosition(int groupPosition) {
        if (groupFirstItemPositions == null) return 0;
        if (groupFirstItemPositions.size() <= groupPosition) return 0;
        return groupFirstItemPositions.get(groupPosition);
    }

    @Nullable
    public synchronized Cursor swapGroupCursor(@Nullable Cursor groups) {
        Cursor oldCursor = this.groups;
        this.groups = groups;
        invalidateDataCount();
        return oldCursor;
    }
}
