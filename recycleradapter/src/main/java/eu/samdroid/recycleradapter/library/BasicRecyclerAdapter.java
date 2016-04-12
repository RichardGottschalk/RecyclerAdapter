package eu.samdroid.recycleradapter.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import eu.samdroid.recycleradapter.library.binding.RecyclerAdapterViewHandler;
import eu.samdroid.recycleradapter.library.source.RecyclerAdapterDataSource;

/**
 * Created by Richard Gottschalk.
 */
public class BasicRecyclerAdapter<VH extends RecyclerView.ViewHolder, DATA>
        extends RecyclerView.Adapter<VH> {

    @Nullable
    private OnElementClickListener onElementClickListener;

    @NonNull
    private RecyclerAdapterDataSource<DATA> recyclerAdapterDataSource = new BasicRecyclerAdapterDataSource<>();

    @NonNull
    private RecyclerAdapterViewHandler<VH, DATA> recyclerAdapterViewHandler = new BasicRecyclerAdapterViewHandler<>();

    public void onViewHolderCreated(final VH viewHolder, final int viewType) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnElementClickListener onElementClickListener = getOnElementClickListener();
                if (onElementClickListener != null) {
                    onElementClickListener.onClick(v, viewHolder.getAdapterPosition(), viewType);
                }
            }
        });
    }

    public void onViewHolderBound(VH viewHolder, DATA data, int position, int viewType) {}

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = getRecyclerAdapterViewHandler().onCreateViewHolder(parent, viewType);
        onViewHolderCreated(vh, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        DATA data = getRecyclerAdapterDataSource().getVisibleData(position);
        int viewType = getRecyclerAdapterDataSource().getViewType(position);
        getRecyclerAdapterViewHandler().onBindViewHolder(holder, data, viewType);
        onViewHolderBound(holder, data, position, viewType);
    }

    @Override
    public final int getItemViewType(int position) {
        return getRecyclerAdapterDataSource().getViewType(position);
    }

    @Override
    public int getItemCount() {
        return getRecyclerAdapterDataSource().getVisibleDataCount();
    }

    public void setRecyclerAdapterDataSource(@NonNull RecyclerAdapterDataSource<DATA> recyclerAdapterDataSource) {
        this.recyclerAdapterDataSource = recyclerAdapterDataSource;
    }

    public void setRecyclerAdapterViewHandler(@NonNull RecyclerAdapterViewHandler<VH, DATA> recyclerAdapterViewHandler) {
        this.recyclerAdapterViewHandler = recyclerAdapterViewHandler;
    }

    @NonNull
    protected RecyclerAdapterDataSource<DATA> getRecyclerAdapterDataSource() {
        return recyclerAdapterDataSource;
    }

    @NonNull
    protected RecyclerAdapterViewHandler<VH, DATA> getRecyclerAdapterViewHandler() {
        return recyclerAdapterViewHandler;
    }

    @Nullable
    public OnElementClickListener getOnElementClickListener() {
        return onElementClickListener;
    }

    public void setOnElementClickListener(@Nullable OnElementClickListener onElementClickListener) {
        this.onElementClickListener = onElementClickListener;
    }

    public interface OnElementClickListener {
        void onClick(View view, int position, int viewType);
    }
}
