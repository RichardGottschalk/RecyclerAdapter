package eu.samdroid.recycleradapter.library.adapter;

import java.util.List;

import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

/**
 * @author Richard Gottschalk
 */
public class ExpandableArrayTreeRecyclerAdapter<DATA, GROUP extends DATA> extends ArrayTreeRecyclerAdapter<DATA, GROUP> {

    public ExpandableArrayTreeRecyclerAdapter(List<GROUP> groups, List<List<DATA>> dataList, ArrayViewTypeInformation... arrayViewTypeInformations) {
        super(groups, dataList, arrayViewTypeInformations);

        getRecyclerAdapterDataSource().setExpandable(true);
    }
}
