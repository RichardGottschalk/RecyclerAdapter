package eu.samdroid.recycleradapter.binding;

import android.database.Cursor;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Richard Gottschalk.
 */
public class BindingUtilCursor {

    @BindingAdapter("cursorDataBindingId")
    public static void setId(TextView textView, @Nullable Cursor cursor) {
        if (cursor == null) return;

        int columnId = cursor.getColumnIndex("_id");
        textView.setText(String.valueOf(cursor.getInt(columnId)));
    }

    @BindingAdapter("cursorDataBindingText")
    public static void setText(TextView textView, @Nullable Cursor cursor) {
        if (cursor == null) return;

        int columnText = cursor.getColumnIndex("text");
        textView.setText(cursor.getString(columnText));
    }

    @BindingAdapter("cursorDataBindingText1")
    public static void setText1(TextView textView, @Nullable Cursor cursor) {
        if (cursor == null) return;

        int columnText = cursor.getColumnIndex("text1");
        textView.setText(cursor.getString(columnText));
    }

    @BindingAdapter("cursorDataBindingText2")
    public static void setText2(TextView textView, @Nullable Cursor cursor) {
        if (cursor == null) return;

        int columnText = cursor.getColumnIndex("text2");
        textView.setText(cursor.getString(columnText));
    }
}
