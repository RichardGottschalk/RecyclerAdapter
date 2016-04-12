package eu.samdroid.recycleradapter.library.binding.holder;

import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Richard Gottschalk.
 */
public class ClassicViewHolder extends RecyclerView.ViewHolder {

    public ClassicViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * BBinds all of the field names passed into the "to" parameter of the
     * constructor with their corresponding cursor columns as specified in the
     * "from" parameter.
     *
     * @param cursor    The {@link Cursor} to add to this ViewHolder instance
     * @param from      The columns from the <code>cursor</code> parameter
     * @param to        The view's IDs in the layout file.
     */
    public void bindView(@Nullable Cursor cursor, final int[] from, final int[] to) {
        if (cursor == null) return;
        final int count = to.length;

        for (int i = 0; i < count; i++) {
            final View v = itemView.findViewById(to[i]);
            if (v != null) {
                String text = cursor.getString(from[i]);
                if (text == null) {
                    text = "";
                }

                if (v instanceof TextView) {
                    setViewText((TextView) v, text);
                } else if (v instanceof ImageView) {
                    setViewImage((ImageView) v, text);
                } else {
                    throw new IllegalStateException(v.getClass().getName() + " is not a " +
                            " view that can be bounds by this SimpleCursorAdapter");
                }
            }
        }
    }

    /**
     * Called by bindView() to set the image for an ImageView.
     *
     * By default, the value will be treated as an image resource. If the
     * value cannot be used as an image resource, the value is used as an
     * image Uri.
     *
     * @param v ImageView to receive an image
     * @param value the value retrieved from the cursor
     */
    public void setViewImage(ImageView v, String value) {
        try {
            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }

    /**
     * Called by bindView() to set the text for a TextView.
     *
     * @param v TextView to receive text
     * @param text the text to be set for the TextView
     */
    public void setViewText(TextView v, String text) {
        v.setText(text);
    }
}
