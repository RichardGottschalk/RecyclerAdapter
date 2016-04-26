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
import eu.samdroid.recycleradapter.library.adapter.ExpandableCursorDataBindingRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.provider.SampleProvider;

/**
 * Created by Richard Gottschalk.
 */
public class ExpandableCursorDataBindingActivity extends AbstractActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_GROUP = -1;
    private static final int LOADER_DATA = 0;

    private ExpandableCursorDataBindingRecyclerAdapter recyclerAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerAdapter = new ExpandableCursorDataBindingRecyclerAdapter(
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_DEFAULT,
                        R.layout.container_expandable_databinding_cursor_child,
                        BR.cursor
                ),
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_GROUP,
                        R.layout.container_expandable_databinding_cursor_group,
                        BR.cursor
                )
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.addOnGroupClickListener(this);
        recyclerAdapter.addOnItemClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_GROUP, null, this);
        getSupportLoaderManager().initLoader(LOADER_DATA, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_GROUP:
                return new CursorLoader(this, SampleProvider.GROUPS_URI, null, null, null, "_id ASC");

            case LOADER_DATA:
                return new CursorLoader(this,
                        SampleProvider.CHILDREN_URI,
                        null,
                        null,
                        null,
                        "data_group_id ASC, _id ASC");
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_GROUP:
                recyclerAdapter.swapGroupCursor(data);
                break;

            case LOADER_DATA:
                recyclerAdapter.setChildrenCursor(data, getColumns(data));
                break;
        }
    }

    /**
     * Creates an array containing the columnIndex which vary from group to group.
     * In this case it is just the columnIndex from "data_group_id".
     *
     * @param cursor A data cursor to search the columnIndex in
     * @return the array
     */
    private int[] getColumns(Cursor cursor) {
        return new int[] {
                cursor.getColumnIndex("data_group_id")
        };
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case LOADER_GROUP:
                recyclerAdapter.swapGroupCursor(null);
                return;

            case LOADER_DATA:
                recyclerAdapter.setChildrenCursor(null);
                return;
        }
    }
}
