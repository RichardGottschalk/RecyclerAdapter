package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;
import android.view.View;

public interface OnItemClickListener {
    void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex);
}
