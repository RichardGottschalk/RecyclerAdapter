package eu.samdroid.recycleradapter.ui.samples;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.adapter.ExpandableCursorTreeRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;
import eu.samdroid.recycleradapter.provider.SampleProvider;

/**
 * Created by Richard Gottschalk.
 */
public class ExpandableCursorTreeActivity extends AbstractActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_GROUP = -1;

    private ExpandableCursorTreeRecyclerAdapter recyclerAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerAdapter = new ExpandableCursorTreeRecyclerAdapter(
                new CursorViewTypeInformation(
                        ViewTypeConstants.VIEW_TYPE_DEFAULT,
                        R.layout.container_expandable_child,
                        new String[]{"text1", "text2"},
                        new int[]{R.id.child_text1, R.id.child_text2}
                ),
                new CursorViewTypeInformation(
                        ViewTypeConstants.VIEW_TYPE_GROUP,
                        R.layout.container_expandable_group,
                        new String[]{"text1", "text2"},
                        new int[]{R.id.group_text1, R.id.group_text2}
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
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_GROUP:
                return new CursorLoader(this, SampleProvider.GROUPS_URI, null, null, null, "_id ASC");

            default:
                return new CursorLoader(this,
                        SampleProvider.CHILDREN_URI,
                        null,
                        "data_group_id = ?",
                        new String[]{String.valueOf(id)},
                        "data_group_id ASC, _id ASC");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_GROUP:
                recyclerAdapter.swapGroupCursor(data);
                onGroupLoaded(data);
                return;

            default:
                int groupIndex = loader.getId() - 1;
                recyclerAdapter.setChildrenCursor(groupIndex, data);
                return;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case LOADER_GROUP:
                recyclerAdapter.swapGroupCursor(null);
                return;

            default:
                recyclerAdapter.setChildrenCursor(loader.getId(), null);
                return;
        }
    }

    private void onGroupLoaded(Cursor cursor) {
        int columnId = cursor.getColumnIndex("_id");

        if (cursor.moveToFirst()) {
            do {
                int groupId = cursor.getInt(columnId);
                getSupportLoaderManager().initLoader(groupId, null, this);
            } while (cursor.moveToNext());
        }
    }
}
