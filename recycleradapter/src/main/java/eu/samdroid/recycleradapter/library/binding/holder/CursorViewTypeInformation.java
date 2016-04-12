package eu.samdroid.recycleradapter.library.binding.holder;

import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

/**
 * Created by Richard Gottschalk.
 */
public class CursorViewTypeInformation {

    @LayoutRes
    private final int layout;
    private final String[] originalFrom;
    @IdRes
    private final int[] to;
    private final int viewTypeId;

    private int[] from;

    public CursorViewTypeInformation(@LayoutRes int layout, String[] originalFrom, int[] to) {
        this(ViewTypeConstants.VIEW_TYPE_DEFAULT, layout, originalFrom, to);
    }

    public CursorViewTypeInformation(int viewTypeId, @LayoutRes int layout, String[] originalFrom, int[] to) {
        this.viewTypeId = viewTypeId;
        this.layout = layout;
        this.originalFrom = originalFrom;
        this.to = to;
    }

    public int getViewTypeId() {
        return viewTypeId;
    }

    public String[] getOriginalFrom() {
        return originalFrom;
    }

    public int[] getTo() {
        return to;
    }

    @LayoutRes
    public int getLayout() {
        return layout;
    }

    public int[] getFrom(@NonNull Cursor cursor) {
        if (from != null) return from;

        findColumns(cursor);
        return from;
    }

    private void findColumns(@NonNull Cursor cursor) {
        int[] columns = new int[originalFrom.length];

        for (int i = 0; i < originalFrom.length; i++) {
            String columnName = originalFrom[i];
            columns[i] = cursor.getColumnIndex(columnName);
        }

        this.from = columns;
    }
}
