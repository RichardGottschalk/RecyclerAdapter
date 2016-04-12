package eu.samdroid.recycleradapter.library.adapter;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.binding.SimpleDataBindingViewHandler;
import eu.samdroid.recycleradapter.library.source.TreeDataSource;

/**
 * Created by Richard Gottschalk.
 */
public class ExpandableArrayDataBindingRecyclerAdapter<DATA, GROUP extends DATA>
        extends TreeRecyclerAdapter {

    public ExpandableArrayDataBindingRecyclerAdapter(List<BindingInformation> bindingInformations,
                                                     Pair<GROUP, List<DATA>>... groups) {
        this(bindingInformations.toArray(new BindingInformation[bindingInformations.size()]),
                groups);
    }

    public ExpandableArrayDataBindingRecyclerAdapter(List<BindingInformation> bindingInformations,
                                                     List<GROUP> groupList,
                                                     List<List<DATA>> dataList) {
        this(bindingInformations.toArray(new BindingInformation[bindingInformations.size()]),
                groupList,
                dataList);
    }

    public ExpandableArrayDataBindingRecyclerAdapter(BindingInformation[] bindingInformations,
                                                     Pair<GROUP, List<DATA>>... groups) {
        List<GROUP> groupList = new ArrayList<>();
        List<List<DATA>> dataList = new ArrayList<>();
        for (Pair<GROUP, List<DATA>> group : groups) {
            groupList.add(group.first);
            dataList.add(group.second);
        }

        initialize(bindingInformations, groupList, dataList);
    }

    public ExpandableArrayDataBindingRecyclerAdapter(BindingInformation[] bindingInformations,
                                                     List<GROUP> groupList,
                                                     List<List<DATA>> dataList) {
        initialize(bindingInformations, groupList, dataList);
    }

    private void initialize(BindingInformation[] bindingInformations,
                            List<GROUP> groupList,
                            List<List<DATA>> dataList) {
        TreeDataSource<DATA, GROUP> dataSource = new TreeDataSource<>();
        dataSource.setExpandable(true);
        dataSource.setGroups(groupList);
        dataSource.addAllData(dataList);
        setRecyclerAdapterDataSource(dataSource);

        // create viewHandler
        SimpleDataBindingViewHandler<DATA> viewHandler
                = new SimpleDataBindingViewHandler<>(bindingInformations);
        setRecyclerAdapterViewHandler(viewHandler);
    }
}
