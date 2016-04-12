package eu.samdroid.recycleradapter.library.adapter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
public class ArrayTreeRecyclerAdapterTest {

    // custom viewType IDs
    private static final int VIEW_TYPE_DATA = ViewTypeConstants.VIEW_TYPE_DEFAULT;
    private static final int VIEW_TYPE_GROUP = ViewTypeConstants.VIEW_TYPE_GROUP;

    private static final int LAYOUT_ID_DATA = R.layout.test_recycleradapter_container_data;
    private static final int LAYOUT_ID_GROUP = R.layout.test_recycleradapter_container_group;

    private static final int TEXT_VIEW_ID_DATA = R.id.test_textview_data;
    private static final int TEXT_VIEW_ID_GROUP = R.id.test_textview_group;

    ArrayTreeRecyclerAdapter adapter;

    String group1 = "Group1";
    String group2 = "Group2";
    String group3 = "Group3";
    List<String> groupList;

    List<String> list1;
    List<String> list2;
    List<String> list3;
    List<List<String>> dataList;

    @Before
    public void setUp() throws Exception {
        groupList = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        dataList = new ArrayList<>();

        groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);

        for (int i = 0; i < 3; i++) list1.add("List1: " + i);
        for (int i = 0; i < 5; i++) list2.add("List2: " + i);
        for (int i = 0; i < 1; i++) list3.add("List3: " + i);
        dataList.add(list1);
        dataList.add(list2);
        dataList.add(list3);

        ArrayViewTypeInformation[] viewTypeInformations = new ArrayViewTypeInformation[2];

        viewTypeInformations[0] = new ArrayViewTypeInformation(VIEW_TYPE_DATA, LAYOUT_ID_DATA, TEXT_VIEW_ID_DATA);
        viewTypeInformations[1] = new ArrayViewTypeInformation(VIEW_TYPE_GROUP, LAYOUT_ID_GROUP, TEXT_VIEW_ID_GROUP);

        adapter = new ArrayTreeRecyclerAdapter<>(groupList, dataList, viewTypeInformations);
    }

    @Test
    public void testGetViewType() {
        assertEquals(VIEW_TYPE_GROUP, adapter.getItemViewType(0));
        assertEquals(VIEW_TYPE_DATA, adapter.getItemViewType(1));
        assertEquals(VIEW_TYPE_DATA, adapter.getItemViewType(2));
        assertEquals(VIEW_TYPE_DATA, adapter.getItemViewType(3));
        assertEquals(VIEW_TYPE_GROUP, adapter.getItemViewType(4));
        assertEquals(VIEW_TYPE_DATA, adapter.getItemViewType(5));
    }

    @Test
    public void testConstructor() throws Exception {
        assertNotNull(adapter);
    }

    @Test
    public void testAddData() throws Exception {
        assertEquals(12, adapter.getItemCount());
        adapter.addData(dataList);
        assertEquals("item count should still be 12 since new data set does not have a group", 12, adapter.getItemCount());

        List<String> newGroupList = new ArrayList<>();
        newGroupList.addAll(groupList);
        newGroupList.add("group4");

        adapter.setGroups(newGroupList);
        assertEquals("item count should now be ", 16, adapter.getItemCount());
    }

    @Test
    public void testSetData() throws Exception {
        assertEquals(12, adapter.getItemCount());
        adapter.setData(0, list2);
        assertEquals(14, adapter.getItemCount());

        adapter.setData(0, new ArrayList());
        assertEquals(9, adapter.getItemCount());

        adapter.setData(0, null);
        assertEquals(9, adapter.getItemCount());

        adapter.setData(0, list1);
        assertEquals(12, adapter.getItemCount());
    }

    @Test
    public void testSetGroup() throws Exception {
        assertEquals("Group1", adapter.setGroup(0, "customGroup1"));
        assertEquals("customGroup1", adapter.setGroup(0, null));
        assertEquals(null, adapter.setGroup(0, "otherCustomGroup1"));

        assertEquals(12, adapter.getItemCount());
    }

    @Test
    public void testSetGroups() throws Exception {
        adapter.setGroups(new ArrayList());
        assertEquals(0, adapter.getItemCount());

        adapter.setGroups(groupList);
        assertEquals(12, adapter.getItemCount());
    }

    @Test
    public void testGetRecyclerAdapterDataSource() throws Exception {
        assertNotNull(adapter.getRecyclerAdapterDataSource());
    }
}
