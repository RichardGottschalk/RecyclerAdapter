package eu.samdroid.recycleradapter.library.source;

import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

/**
 * Created by Richard Gottschalk.
 */
public abstract class AbstractTreeDataSource<DATA, GROUP extends DATA>
        implements RecyclerAdapterDataSource<DATA> {

    private static final int DATA_COUNT_INVALID = -1;

    private SparseArrayCompat<Boolean> groupExpanded = new SparseArrayCompat<>();
    private boolean isGroupDefaultExpanded = true;
    private boolean expandable = false;

    private int visibleDataCount = DATA_COUNT_INVALID;

    public AbstractTreeDataSource() {
        this(false);
    }

    public AbstractTreeDataSource(boolean expandable) {
        this.expandable = expandable;
    }

    @Nullable
    @Override
    public DATA getVisibleData(int position) {
        int groupId = getGroupIdByVisiblePosition(position);
        if (isGroupPosition(position)) {
            return getGroup(groupId);
        } else {
            int childOffsetInGroup = getChildOffsetInGroup(position) - 1;
            return getChild(groupId, childOffsetInGroup);
        }
    }

    @Override
    public int getVisibleDataCount() {
        if (visibleDataCount == DATA_COUNT_INVALID) {
            visibleDataCount = calculateVisibleDataCount();
        }
        return visibleDataCount >= 0 ? visibleDataCount : 0;
    }

    protected void invalidateDataCount() {
        visibleDataCount = DATA_COUNT_INVALID;
    }

    private int calculateVisibleDataCount() {
        if (getGroupCount() == 0) return DATA_COUNT_INVALID;

        int visible = getGroupCount();

        for (int groupId = 0; groupId < getGroupCount(); groupId++) {
            if (isGroupExpanded(groupId)) {
                visible += getChildrenCount(groupId);
            }
        }

        return visible;
    }

    @Override
    public int getViewType(int position) {
        if (isGroupPosition(position)) {
            return ViewTypeConstants.VIEW_TYPE_GROUP;
        }
        return ViewTypeConstants.VIEW_TYPE_DEFAULT;
    }

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    public abstract int getGroupCount();

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupId the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    public abstract int getChildrenCount(int groupId);

    /**
     * Gets the data associated with the given group.
     *
     * @param groupId the position of the group
     * @return the data child for the specified group
     */
    public abstract GROUP getGroup(int groupId);

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */
    public abstract DATA getChild(int groupPosition, int childPosition);

    public boolean isEmpty() {
        return (getGroupCount() == 0);
    }

    /**
     * Called when a group is expanded.
     *
     * @param groupId The group being expanded.
     */
    public void onGroupExpanded(int groupId) {}

    /**
     * Called when a group is collapsed.
     *
     * @param groupId The group being collapsed.
     */
    public void onGroupCollapsed(int groupId) {}

    public boolean isGroupPosition(int visiblePosition) {
        return getChildOffsetInGroup(visiblePosition) == 0;
    }

    public int getGroupIdByVisiblePosition(int visiblePosition) {
        if (visiblePosition == 0) return 0;

        int currentPosition = 0;

        for (int groupId = 0; groupId < getGroupCount(); groupId++) {
            if (visiblePosition < currentPosition) return groupId - 1;

            currentPosition ++;
            if (isGroupExpanded(groupId)) currentPosition += getChildrenCount(groupId);
        }

        return getGroupCount() - 1;
    }

    public int getChildOffsetInGroup(int visiblePosition) {
        int groupPosition = 0;
        int lastGroupPosition = 0;

        for (int groupId = 0; groupId < getGroupCount(); groupId++) {
            if (groupPosition > visiblePosition) return visiblePosition - lastGroupPosition;

            lastGroupPosition = groupPosition;

            groupPosition ++;
            if (isGroupExpanded(groupId)) groupPosition += getChildrenCount(groupId);
        }

        return visiblePosition - lastGroupPosition;
    }

    public boolean expandGroup(int groupId) {
        if (expandable && !isGroupExpanded(groupId)) {
            groupExpanded.put(groupId, true);
            onGroupExpanded(groupId);
            invalidateDataCount();
            return true;
        }
        return false;
    }

    public boolean collapseGroup(int groupId) {
        if (expandable && isGroupExpanded(groupId)) {
            groupExpanded.put(groupId, false);
            onGroupCollapsed(groupId);
            invalidateDataCount();
            return true;
        }
        return false;
    }

    public boolean isGroupExpanded(int groupId) {
        Boolean isExpanded = groupExpanded.get(groupId);
        return isExpanded != null ? isExpanded : isGroupDefaultExpanded;
    }

    public int getGroupPosition(int groupId) {
        int position = 0;
        for (int g = 0; g < groupId; g++) {
            if (isGroupExpanded(g)) {
                position += getChildrenCount(g);
            }
            position ++;
        }
        return position;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isGroupDefaultExpanded() {
        return isGroupDefaultExpanded;
    }

    public void setGroupDefaultExpanded(boolean isGroupDefaultExpanded) {
        this.isGroupDefaultExpanded = isGroupDefaultExpanded;
    }
}
