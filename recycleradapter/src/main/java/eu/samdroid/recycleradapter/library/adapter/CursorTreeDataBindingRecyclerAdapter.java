package eu.samdroid.recycleradapter.library.adapter;

import java.util.List;

import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.binding.SimpleDataBindingViewHandler;
import eu.samdroid.recycleradapter.library.source.CursorTreeDataSource;

/**
 * @author Richard Gottschalk
 */
public class CursorTreeDataBindingRecyclerAdapter extends CursorTreeRecyclerAdapter {

    public CursorTreeDataBindingRecyclerAdapter(List<BindingInformation> bindingInformations) {
        this(bindingInformations.toArray(new BindingInformation[bindingInformations.size()]));
    }

    public CursorTreeDataBindingRecyclerAdapter(BindingInformation ... bindingInformations) {
        setRecyclerAdapterDataSource(new CursorTreeDataSource());

        setRecyclerAdapterViewHandler(new SimpleDataBindingViewHandler<>(bindingInformations));
    }
}
