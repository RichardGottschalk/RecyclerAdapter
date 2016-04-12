package eu.samdroid.recycleradapter.library.binding;

import android.support.annotation.LayoutRes;

public class BindingInformation {
    public final int viewType;

    @LayoutRes
    public final int layoutId;

    public final int bindingId;

    public BindingInformation(int viewType, int layoutId, int bindingId) {
        this.viewType = viewType;
        this.layoutId = layoutId;
        this.bindingId = bindingId;
    }
}
