package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import eu.samdroid.recycleradapter.library.binding.ArrayViewHandler;
import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;
import eu.samdroid.recycleradapter.library.source.TreeDataSource;

/**
 * Created by Richard Gottschalk.
 */
public class ArrayTreeRecyclerAdapter<DATA, GROUP extends DATA> extends TreeRecyclerAdapter {

    public ArrayTreeRecyclerAdapter(List<GROUP> groupList,
                                    List<List<DATA>> dataList,
                                    ArrayViewTypeInformation... arrayViewTypeInformations) {
        // setup data source
        TreeDataSource dataSource = new TreeDataSource<>();
        setRecyclerAdapterDataSource(dataSource);
        setGroups(groupList);
        addData(dataList);

        // setup view handler
        ArrayViewHandler viewHandler = new ArrayViewHandler(arrayViewTypeInformations);
        setRecyclerAdapterViewHandler(viewHandler);
    }

    public void addData(List<List<DATA>> dataList) {
        for (List<DATA> data : dataList) {
            getRecyclerAdapterDataSource().addData(data);
        }
        notifyDataSetChanged();
    }

    public List<DATA> setData(int groupId, List<DATA> object) {
        List<DATA> datas = getRecyclerAdapterDataSource().setData(groupId, object);
        int start = getRecyclerAdapterDataSource().getGroupPosition(groupId);
        if (datas != null) {
            notifyItemRangeRemoved(start + 1, datas.size());
        }
        if (object != null) {
            notifyItemRangeInserted(start + 1, object.size());
        }
        return datas;
    }

    public GROUP setGroup(int groupId, GROUP group) {
        GROUP old = getRecyclerAdapterDataSource().setGroup(groupId, group);
        int position = getRecyclerAdapterDataSource().getGroupPosition(groupId);

        if (old != null) {
            notifyItemChanged(position);
        } else {
            notifyItemInserted(position);
        }
        return old;
    }

    public void setGroups(@NonNull List<GROUP> groups) {
        getRecyclerAdapterDataSource().setGroups(groups);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    protected TreeDataSource<DATA, GROUP> getRecyclerAdapterDataSource() {
        return (TreeDataSource) super.getRecyclerAdapterDataSource();
    }
}
