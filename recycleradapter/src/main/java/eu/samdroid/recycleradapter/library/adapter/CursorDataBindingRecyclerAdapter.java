package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;
import android.support.annotation.Nullable;

import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.binding.SimpleDataBindingViewHandler;
import eu.samdroid.recycleradapter.library.source.CursorDataSource;

/**
 * Created by Richard Gottschalk.
 */
public class CursorDataBindingRecyclerAdapter extends BasicRecyclerAdapter<SimpleDataBindingViewHandler.DataBindingViewHolder, Cursor> {

    private CursorDataSource dataSource;

    public CursorDataBindingRecyclerAdapter(BindingInformation... bindingInformations) {
        // create dataSource
        dataSource = new CursorDataSource();
        setRecyclerAdapterDataSource(dataSource);

        // create viewHandler
        SimpleDataBindingViewHandler<Cursor> viewHandler
                = new SimpleDataBindingViewHandler<>(bindingInformations);
        setRecyclerAdapterViewHandler(viewHandler);
    }

    @Nullable
    public Cursor swapCursor(@Nullable Cursor cursor) {
        Cursor oldCursor = dataSource.swapCursor(cursor);
        notifyDataSetChanged();
        return oldCursor;
    }
}
