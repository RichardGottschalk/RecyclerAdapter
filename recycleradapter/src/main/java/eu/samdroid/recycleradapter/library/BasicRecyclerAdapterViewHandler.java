package eu.samdroid.recycleradapter.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import eu.samdroid.recycleradapter.library.binding.RecyclerAdapterViewHandler;

/**
 * Created by Richard Gottschalk.
 */
final class BasicRecyclerAdapterViewHandler<VH extends RecyclerView.ViewHolder, DATA>
        implements RecyclerAdapterViewHandler<VH, DATA> {


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, @Nullable DATA data, int viewType) {
        // do nothing
    }
}
