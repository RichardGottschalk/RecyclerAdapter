package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.R;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
public class TreeRecyclerAdapterTest {

    // custom viewType IDs
    private static final int VIEW_TYPE_DATA = 10;
    private static final int VIEW_TYPE_GROUP = 11;

    private static final int LAYOUT_ID_DATA = R.layout.test_recycleradapter_container_data;
    private static final int LAYOUT_ID_GROUP = R.layout.test_recycleradapter_container_group;

    private static final int TEXT_VIEW_ID_DATA = R.id.test_textview_data;
    private static final int TEXT_VIEW_ID_GROUP = R.id.test_textview_group;

    TreeRecyclerAdapter adapter;

    // helper variable for callback tests
    boolean called = false;

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
    public void testGetRecyclerAdapterDataSource() throws Exception {
        assertNotNull(adapter.getRecyclerAdapterDataSource());
    }

    @Test
    public void testOnGroupClickAction() throws Exception {
        // try to collapse
        adapter.setExpandable(false);
        assertEquals(12, adapter.getItemCount());
        adapter.onGroupClickAction(null, 0, 0);
        assertEquals(12, adapter.getItemCount());

        // collapse
        adapter.setExpandable(true);
        assertEquals(12, adapter.getItemCount());
        adapter.onGroupClickAction(null, 0, 0);
        assertEquals(9, adapter.getItemCount());

        // try to expand
        adapter.setExpandable(false);
        assertEquals(9, adapter.getItemCount());
        adapter.onGroupClickAction(null, 0, 0);
        assertEquals(9, adapter.getItemCount());

        // collapse
        adapter.setExpandable(true);
        assertEquals(9, adapter.getItemCount());
        adapter.onGroupClickAction(null, 0, 0);
        assertEquals(12, adapter.getItemCount());
    }

    @Test
    public void testOnItemClickAction() throws Exception {
        adapter.addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex) {
                called = true;
            }
        });

        called = false;
        adapter.onItemClickAction(null, 0, 0);
        assertTrue(called);
    }

    @Test
    public void testAddOnGroupClickListener() throws Exception {
        adapter.addOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public void onGroupClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int groupVisiblePosition) {
                called = true;
            }
        });

        called = false;
        adapter.onGroupClickAction(null, 0, 0);
        Assert.assertTrue(called);

        adapter.addOnGroupClickListener(null);
        assertTrue(true);
    }

    @Test
    public void testRemoveOnGroupClickListener() throws Exception {
        OnGroupClickListener onGroupClickListener = new OnGroupClickListener() {
            @Override
            public void onGroupClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int groupVisiblePosition) {
                called = true;
            }
        };
        adapter.addOnGroupClickListener(onGroupClickListener);

        called = false;
        adapter.removeOnGroupClickListener(onGroupClickListener);
        adapter.onGroupClickAction(null, 0, 0);
        assertFalse(called);
    }

    @Test
    public void testAddOnItemClickListener() throws Exception {
        adapter.addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex) {
                called = true;
            }
        });

        called = false;
        adapter.onItemClickAction(null, 0, 0);
        assertTrue(called);

        adapter.addOnItemClickListener(null);
        assertTrue(true);
    }

    @Test
    public void testRemoveOnItemClickListener() throws Exception {
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex) {
                called = true;
            }
        };
        adapter.addOnItemClickListener(onItemClickListener);

        called = false;
        adapter.removeOnItemClickListener(onItemClickListener);
        adapter.onItemClickAction(null, 0, 0);
        assertFalse(called);
    }

    @Test
    public void testOnElementClick() throws Exception {
        BasicRecyclerAdapter.OnElementClickListener onElementClickListener = adapter.getOnElementClickListener();
        assertNotNull(onElementClickListener);


        adapter.addOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public void onGroupClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int groupVisiblePosition) {
                called = true;
            }
        });

        adapter.addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(@NonNull TreeRecyclerAdapter parent, @NonNull View view, int groupIndex, int childIndex) {
                called = true;
            }
        });

        called = false;
        onElementClickListener.onClick(null, 0, ViewTypeConstants.VIEW_TYPE_GROUP);
        assertTrue(called);

        called = false;
        onElementClickListener.onClick(null, 1, ViewTypeConstants.VIEW_TYPE_DEFAULT);
        assertTrue(called);
    }
}
