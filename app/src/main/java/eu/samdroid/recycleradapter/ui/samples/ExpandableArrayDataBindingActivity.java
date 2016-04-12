package eu.samdroid.recycleradapter.ui.samples;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.BR;
import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.adapter.ExpandableArrayDataBindingRecyclerAdapter;

/**
 * Created by Richard Gottschalk.
 */
public class ExpandableArrayDataBindingActivity extends AbstractActivity {

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        // add some dummy data
        List<List<String>> dataLists = new ArrayList<>();
        List<String> groups = createList(10, "group: ");

        for (String group : groups) {
            dataLists.add(createList(10, group + " element: "));
        }

        List<BindingInformation> bindingInformations = new ArrayList<>();
        bindingInformations.add(
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_DEFAULT,
                        R.layout.container_expandable_databinding_array_child,
                        BR.content)
        );
        bindingInformations.add(
                new BindingInformation(
                        ViewTypeConstants.VIEW_TYPE_GROUP,
                        R.layout.container_expandable_databinding_array_group,
                        BR.content)
        );

        ExpandableArrayDataBindingRecyclerAdapter<String, String> recyclerAdapter
                = new ExpandableArrayDataBindingRecyclerAdapter<>(
                bindingInformations, groups, dataLists);

        // add LayoutManager and adapter to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // add listener
        recyclerAdapter.addOnGroupClickListener(this);
        recyclerAdapter.addOnItemClickListener(this);
    }
}
