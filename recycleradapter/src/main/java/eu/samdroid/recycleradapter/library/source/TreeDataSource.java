package eu.samdroid.recycleradapter.library.source;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Richard Gottschalk.
 */
public class TreeDataSource<DATA, GROUP extends DATA>
        extends AbstractTreeDataSource<DATA, GROUP> {

    @NonNull
    private List<List<DATA>> data = new ArrayList<>();

    @NonNull
    private List<GROUP> groups = new ArrayList<>();

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupId the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupId) {
        if (this.data.size() <= groupId) return 0;
        List<DATA> data = this.data.get(groupId);
        return data == null ? 0 : data.size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupId the position of the group
     * @return the data child for the specified group
     */
    @Override
    public GROUP getGroup(int groupId) {
        return groups.get(groupId);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child or null
     */
    @Override
    public DATA getChild(int groupPosition, int childPosition) {
        if (0 <= groupPosition && groupPosition < getGroupCount()) {
            List<DATA> group = this.data.get(groupPosition);
            if (group == null) return null;
            if (childPosition < 0) return null;
            if (getChildrenCount(groupPosition) == 0) return null;
            if (childPosition >= getChildrenCount(groupPosition)) return null;
            return group.get(childPosition);
        }
        return null;
    }

    public void setGroups(@NonNull List<GROUP> groups) {
        this.groups = groups;
        invalidateDataCount();
    }

    public void addGroup(@NonNull GROUP group) {
        this.groups.add(group);
        invalidateDataCount();
    }

    public void addData(List<DATA> data) {
        this.data.add(data);
        invalidateDataCount();
    }

    public boolean addAllData(Collection<? extends List<DATA>> collection) {
        boolean addAll = data.addAll(collection);
        if (addAll) invalidateDataCount();
        return addAll;
    }

    public List<DATA> setData(int groupId, List<DATA> object) {
        List<DATA> set = data.set(groupId, object);
        invalidateDataCount();
        return set;
    }

    public void removeData(int groupId) {
        data.remove(groupId);
        invalidateDataCount();
    }

    public void clearData() {
        data.clear();
        invalidateDataCount();
    }

    public void clearGroup() {
        groups.clear();
        invalidateDataCount();
    }

    public void clear() {
        clearData();
        clearGroup();
    }

    public GROUP setGroup(int groupId, GROUP object) {
        return groups.set(groupId, object);
    }
}
