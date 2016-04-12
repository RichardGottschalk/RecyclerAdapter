package eu.samdroid.recycleradapter.ui.samples;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import eu.samdroid.recycleradapter.BR;
import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.adapter.ArrayDataBindingRecyclerAdapter;

/**
 * Created by Richard Gottschalk.
 */
public class ArrayDataBindingActivity extends AbstractActivity {

    private ArrayDataBindingRecyclerAdapter<String> recyclerAdapter;

    protected void initRecyclerView(RecyclerView recyclerView) {
        // create dummy data
        List<String> data = createList(100, "element: ");

        recyclerAdapter = new ArrayDataBindingRecyclerAdapter<>(
                R.layout.container_simple_databinding,
                BR.content,
                data
        );

        // add LayoutManager and adapter to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.setOnElementClickListener(this);
    }
}