package eu.samdroid.recycleradapter.library.adapter;

import android.support.v4.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;

import static org.junit.Assert.*;

/**
 * @author Richard Gottschalk
 */
public class ExpandableArrayDataBindingRecyclerAdapterTest {

    List<BindingInformation> bindingInformations = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        bindingInformations = new ArrayList<>();

        bindingInformations.add(new BindingInformation(
                ViewTypeConstants.VIEW_TYPE_DEFAULT,
                0,
                0)
        );

        bindingInformations.add(new BindingInformation(
                ViewTypeConstants.VIEW_TYPE_GROUP,
                0,
                0)
        );
    }

    @Test
    public void testConstructor() {
        ExpandableArrayDataBindingRecyclerAdapter<String, String> adapter
                = new ExpandableArrayDataBindingRecyclerAdapter<>(bindingInformations);

        assertNotNull(adapter);
    }

    @Test
    public void testConstructor2() {
        List<List<String>> dataList = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        List<String> groupList = new ArrayList<>();
        groupList.add("Group1");
        groupList.add("Group1");
        groupList.add("Group1");

        for (int i = 0; i < 3; i++) list1.add("List1: " + i);
        for (int i = 0; i < 5; i++) list2.add("List2: " + i);
        for (int i = 0; i < 1; i++) list3.add("List3: " + i);
        dataList.add(list1);
        dataList.add(list2);
        dataList.add(list3);


        ExpandableArrayDataBindingRecyclerAdapter<String, String> adapter
                = new ExpandableArrayDataBindingRecyclerAdapter<>(bindingInformations, groupList, dataList);

        assertNotNull(adapter);
        assertEquals(12, adapter.getItemCount());
    }

    @Test
    public void testConstructor3() {
        List<Pair<String, List<String>>> pairs = new ArrayList<>();

        for (int groupId = 0; groupId < 3; groupId++) {
            List<String> dataList = new ArrayList<>();
            Pair<String, List<String>> pair = new Pair<>("group" + groupId, dataList);
            pairs.add(pair);

            for (int childId = 0; childId < 3; childId++) {
                dataList.add("child" + childId);
            }
        }

        ExpandableArrayDataBindingRecyclerAdapter<String, String> adapter
                = new ExpandableArrayDataBindingRecyclerAdapter<>(
                bindingInformations,
                pairs.toArray(new Pair[pairs.size()])
        );

        assertNotNull(adapter);
        assertEquals(12, adapter.getItemCount());
    }
}
