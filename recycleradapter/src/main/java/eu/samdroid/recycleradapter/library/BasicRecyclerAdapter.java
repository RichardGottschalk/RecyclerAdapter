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

    private boolean showNoDataLayout = false;

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
        int viewType = getRecyclerAdapterDataSource().getViewType(position);
        if (getRecyclerAdapterDataSource().getVisibleDataCount() == 0 && showNoDataLayout) {
            return ViewTypeConstants.VIEW_TYPE_NO_DATA;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        int dataCount = getRecyclerAdapterDataSource().getVisibleDataCount();
        if (dataCount <= 0 && showNoDataLayout) {
            return 1;
        }
        return dataCount;
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

    public boolean hasNoDataLayout() {
        return showNoDataLayout;
    }

    /**
     * If set to true the RecyclerAdapter will always have at least one element.
     * You need to provide a {@link ViewTypeConstants#VIEW_TYPE_NO_DATA} viewType information
     * and provide a binding behavior for {@link ViewTypeConstants#VIEW_TYPE_NO_DATA} in your
     * {@link RecyclerAdapterViewHandler#onBindViewHolder}
     *
     * @param showNoDataLayout  true if you want to show that you do not have data for this RecyclerView
     */
    public void showNoDataLayout(boolean showNoDataLayout) {
        this.showNoDataLayout = showNoDataLayout;
    }

    public interface OnElementClickListener {
        void onClick(View view, int position, int viewType);
    }
}
