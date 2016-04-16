package eu.samdroid.recycleradapter.library.adapter;

import java.util.List;

import eu.samdroid.recycleradapter.library.binding.BindingInformation;

/**
 * @author Richard Gottschalk
 */
public class ExpandableCursorDataBindingRecyclerAdapter extends CursorTreeDataBindingRecyclerAdapter {

    public ExpandableCursorDataBindingRecyclerAdapter(List<BindingInformation> bindingInformations) {
        this(bindingInformations.toArray(new BindingInformation[bindingInformations.size()]));
    }

    public ExpandableCursorDataBindingRecyclerAdapter(BindingInformation ... bindingInformations) {
        super(bindingInformations);

        getRecyclerAdapterDataSource().setExpandable(true);
    }
}
