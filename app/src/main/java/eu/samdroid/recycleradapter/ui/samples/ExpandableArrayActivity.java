package eu.samdroid.recycleradapter.ui.samples;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.adapter.ExpandableArrayTreeRecyclerAdapter;
import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

/**
 * Created by Richard Gottschalk.
 */
public class ExpandableArrayActivity extends AbstractActivity {

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        // add some dummy data
        List<List<String>> dataLists = new ArrayList<>();
        List<String> groups = createList(10, "group: ");

        for (String group : groups) {
            dataLists.add(createList(10, group + " element: "));
        }

        ExpandableArrayTreeRecyclerAdapter<String, String> recyclerAdapter
                = new ExpandableArrayTreeRecyclerAdapter<>(groups, dataLists,
                new ArrayViewTypeInformation(ViewTypeConstants.VIEW_TYPE_DEFAULT, R.layout.container_simple_element, R.id.textView1),
                new ArrayViewTypeInformation(ViewTypeConstants.VIEW_TYPE_GROUP, R.layout.container_simple_element, R.id.textView1)
        );

        // add LayoutManager and adapter to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.addOnGroupClickListener(this);
        recyclerAdapter.addOnItemClickListener(this);
    }
}
