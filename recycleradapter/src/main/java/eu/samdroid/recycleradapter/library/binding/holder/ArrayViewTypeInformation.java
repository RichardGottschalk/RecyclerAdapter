package eu.samdroid.recycleradapter.library.binding.holder;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

/**
 * @author Richard Gottschalk
 */
public class ArrayViewTypeInformation {

    private final int viewTypeId;

    @LayoutRes
    private final int layout;
    @IdRes
    private final int textViewRes;

    /**
     * Creates a simple ArrayViewTypeInformation with {@link ViewTypeConstants#VIEW_TYPE_DEFAULT} as viewType ID.
     *
     * !! Note: only viewTypeIds from {@link ViewTypeConstants} work with this library.
     *
     * @param layout         a layout file representing this viewType
     * @param textViewRes    a textView ID inside the layout file to write data in
     */
    public ArrayViewTypeInformation(@LayoutRes int layout, @IdRes int textViewRes) {
        this(ViewTypeConstants.VIEW_TYPE_DEFAULT, layout, textViewRes);
    }

    /**
     * Creates a ViewTypeInformation with given viewTypeId, layoutId and textViewId.
     *
     * !! Note: only viewTypeIds from {@link ViewTypeConstants} work with this library.
     *
     * @param viewTypeId     an ID from {@link ViewTypeConstants}
     * @param layout         a layout file representing this viewType
     * @param textViewRes    a textView ID inside the layout file to write data in
     */
    public ArrayViewTypeInformation(int viewTypeId, @LayoutRes int layout, @IdRes int textViewRes) {
        this.viewTypeId = viewTypeId;
        this.layout = layout;
        this.textViewRes = textViewRes;
    }

    public int getViewTypeId() {
        return viewTypeId;
    }

    public int getLayout() {
        return layout;
    }

    public int getTextViewRes() {
        return textViewRes;
    }
}
