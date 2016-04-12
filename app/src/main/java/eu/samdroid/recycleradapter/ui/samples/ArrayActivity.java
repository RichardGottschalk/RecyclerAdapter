package eu.samdroid.recycleradapter.ui.samples;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.adapter.ArrayRecyclerAdapter;

/**
 * Created by Richard Gottschalk.
 */
public class ArrayActivity extends AbstractActivity {

    private ArrayRecyclerAdapter<String> recyclerAdapter;

    protected void initRecyclerView(RecyclerView recyclerView) {
        // create dummy data
        List<String> data = createList(100, "element: ");

        recyclerAdapter
                = new ArrayRecyclerAdapter<>(R.layout.container_simple_element, R.id.textView1, data);

        // add LayoutManager and adapter to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.setOnElementClickListener(this);
    }
}
