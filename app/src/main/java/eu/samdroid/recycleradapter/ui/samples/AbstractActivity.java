package eu.samdroid.recycleradapter.ui.samples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.adapter.OnGroupClickListener;
import eu.samdroid.recycleradapter.library.adapter.OnItemClickListener;
import eu.samdroid.recycleradapter.library.adapter.TreeRecyclerAdapter;

/**
 * Created by Richard Gottschalk.
 */
abstract class AbstractActivity extends AppCompatActivity
        implements BasicRecyclerAdapter.OnElementClickListener,
        OnItemClickListener, OnGroupClickListener {

    protected abstract void initRecyclerView(RecyclerView recyclerView);

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        initRecyclerView(recyclerView);
    }

    public List<String> createList(int count, String prefix) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(prefix + i);
        }

        return list;
    }

    @Override
    public void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex) {
        showToast("onItemClicked group " + groupIndex + " child " + childIndex);
    }

    @Override
    public void onGroupClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int groupVisiblePosition) {
        showToast("onItemClicked group " + groupIndex);
    }

    @Override
    public void onClick(View view, int position, int viewType) {
        showToast("onClick position " + position);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
