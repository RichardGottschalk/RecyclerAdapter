package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.source.AbstractTreeDataSource;

/**
 * @author Richard Gottschalk
 */
public abstract class TreeRecyclerAdapter<VH extends RecyclerView.ViewHolder, DATA>
        extends BasicRecyclerAdapter<VH, DATA> {

    private List<OnGroupClickListener> onGroupClickListeners = new LinkedList<>();
    private List<OnItemClickListener> onItemClickListeners = new LinkedList<>();

    protected TreeRecyclerAdapter() {
        setOnElementClickListener(new OnElementClickListener() {
            @Override
            public void onClick(View view, int position, int viewType) {
                int groupIndex = getRecyclerAdapterDataSource().getGroupIdByVisiblePosition(position);
                int childIndex = getRecyclerAdapterDataSource().getChildOffsetInGroup(position) - 1;
                if (viewType == ViewTypeConstants.VIEW_TYPE_GROUP) {
                    onGroupClickAction(view, groupIndex, position);
                } else {
                    onItemClickAction(view, groupIndex, childIndex);
                }
            }
        });
    }

    @NonNull
    @Override
    protected AbstractTreeDataSource<DATA, DATA> getRecyclerAdapterDataSource() {
        return (AbstractTreeDataSource<DATA, DATA>) super.getRecyclerAdapterDataSource();
    }

    public void onGroupClickAction(@NonNull View view, int groupIndex, int visiblePosition) {
        if (getRecyclerAdapterDataSource().isGroupExpanded(groupIndex)) {
            int startPosition = visiblePosition + 1;
            int elements = getRecyclerAdapterDataSource().getChildrenCount(groupIndex);

            if (getRecyclerAdapterDataSource().collapseGroup(groupIndex)) {
                notifyItemRangeRemoved(startPosition, elements);
            }
        } else {
            int startPosition = visiblePosition + 1;
            int elements = getRecyclerAdapterDataSource().getChildrenCount(groupIndex);

            if (getRecyclerAdapterDataSource().expandGroup(groupIndex)) {
                notifyItemRangeInserted(startPosition, elements);
            }
        }
        notifyGroupListeners(view, groupIndex, visiblePosition);
    }

    public void onItemClickAction(@NonNull View view, int groupIndex, int childIndex) {
        notifyItemListeners(view, groupIndex, childIndex);
    }

    private void notifyGroupListeners(@NonNull View view, int groupIndex, int visiblePosition) {
        for (OnGroupClickListener listener : onGroupClickListeners) {
            listener.onGroupClicked(this, view, groupIndex, visiblePosition);
        }
    }

    private void notifyItemListeners(@NonNull View view, int groupIndex, int childIndex) {
        for (OnItemClickListener listener : onItemClickListeners) {
            listener.onItemClicked(this, view, groupIndex, childIndex);
        }
    }

    public void addOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        if (onGroupClickListener != null) {
            this.onGroupClickListeners.add(onGroupClickListener);
        }
    }

    public void removeOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListeners.remove(onGroupClickListener);
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            this.onItemClickListeners.add(onItemClickListener);
        }
    }

    public void removeOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListeners.remove(onItemClickListener);
    }

    public void setExpandable(boolean expandable) {
        this.getRecyclerAdapterDataSource().setExpandable(expandable);
    }
}
