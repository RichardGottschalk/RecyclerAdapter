package eu.samdroid.recycleradapter.library.adapter;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

/**
 * @author Richard Gottschalk
 */
@RunWith(AndroidJUnit4.class)
public class ExpandableArrayTreeRecyclerAdapterTest extends AndroidTestCase {

    ExpandableArrayTreeRecyclerAdapter<String, String> adapter;

    String group1 = "Group1";
    String group2 = "Group2";
    String group3 = "Group3";
    List<String> groupList;

    List<String> list1;
    List<String> list2;
    List<String> list3;

    @Before
    public void setUp() throws Exception {
        groupList = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);

        for (int i = 0; i < 3; i++) list1.add("List1: " + i);
        for (int i = 0; i < 5; i++) list2.add("List2: " + i);
        for (int i = 0; i < 1; i++) list3.add("List3: " + i);
    }

    @Test
    public void testConstructorParameterized() throws Exception {
        List<List<String>> dataList = new ArrayList<>();
        dataList.add(list1);
        dataList.add(list2);
        dataList.add(list3);

        ArrayViewTypeInformation arrayViewTypeInformation
                = new ArrayViewTypeInformation(0, 0);

        adapter = new ExpandableArrayTreeRecyclerAdapter<>(groupList, dataList, arrayViewTypeInformation);

        assertNotNull(adapter);
    }
}
