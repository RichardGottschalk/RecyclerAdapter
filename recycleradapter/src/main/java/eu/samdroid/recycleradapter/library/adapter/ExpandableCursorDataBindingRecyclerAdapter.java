package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;

import java.util.List;

import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.binding.SimpleDataBindingViewHandler;
import eu.samdroid.recycleradapter.library.source.CursorTreeDataSource;

/**
 * @author Richard Gottschalk
 */
public class ExpandableCursorDataBindingRecyclerAdapter extends CursorTreeRecyclerAdapter {

    private CursorTreeDataSource dataSource;

    public ExpandableCursorDataBindingRecyclerAdapter(List<BindingInformation> bindingInformations) {
        this(bindingInformations.toArray(new BindingInformation[bindingInformations.size()]));
    }

    public ExpandableCursorDataBindingRecyclerAdapter(BindingInformation ... bindingInformations) {
        dataSource = new CursorTreeDataSource();
        setRecyclerAdapterDataSource(dataSource);

        SimpleDataBindingViewHandler<Cursor> viewHandler = new SimpleDataBindingViewHandler<>(bindingInformations);
        setRecyclerAdapterViewHandler(viewHandler);

        dataSource.setExpandable(true);
    }
}
