package eu.samdroid.recycleradapter.ui.samples;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.adapter.CursorRecyclerAdapter;
import eu.samdroid.recycleradapter.provider.SampleProvider;

/**
 * Created by Richard Gottschalk.
 */
public class CursorActivity extends AbstractActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorRecyclerAdapter recyclerAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerAdapter = new CursorRecyclerAdapter(
                R.layout.container_simple_element,
                new String[]{
                        "text"
                },
                new int[] {
                        R.id.textView1
                });

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
        return new CursorLoader(this, SampleProvider.DATA_URI, null, null, null, "_id ASC");
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
