package eu.samdroid.recycleradapter.library.binding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Richard Gottschalk.
 */
public interface RecyclerAdapterViewHandler<VH extends RecyclerView.ViewHolder, DATA> {

    VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull VH holder, @Nullable DATA data, int viewType);
}
