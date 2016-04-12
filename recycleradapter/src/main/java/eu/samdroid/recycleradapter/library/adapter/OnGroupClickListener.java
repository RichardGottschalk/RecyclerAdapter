package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;
import android.view.View;

public interface OnGroupClickListener {
    void onGroupClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int groupVisiblePosition);
}
