package eu.samdroid.recycleradapter.ui.samples;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import eu.samdroid.recycleradapter.BR;
import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.adapter.CursorDataBindingRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.provider.SampleProvider;

/**
 * Created by Richard Gottschalk.
 */
public class EmptyCursorDataBindingActivity extends AbstractActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorDataBindingRecyclerAdapter recyclerAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerAdapter = new CursorDataBindingRecyclerAdapter(
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_DEFAULT,
                        R.layout.container_cursor_databinding,
                        BR.cursor),
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_NO_DATA, // add this to handle "no data" layouts
                        R.layout.container_cursor_databinding_nocontent, // the layout file - needs to have dataBinding capabilities
                        0) // since there is no data this can be 0
        );

        recyclerAdapter.showNoDataLayout(true); // enable "no data" layout behavior

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.setOnElementClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, SampleProvider.DATA_URI,
                null,
                "_id = ?",
                new String[]{"0"}, // make sure the resulting cursor is empty
                "_id ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        recyclerAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerAdapter.swapCursor(null);
    }
}
