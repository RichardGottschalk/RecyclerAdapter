package eu.samdroid.recycleradapter.library.source;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Richard Gottschalk.
 */
public class TreeDataSourceTest {

    TreeDataSource<String, String> dataSource;

    String group1 = "Group1";
    String group2 = "Group2";
    String group3 = "Group3";
    List<String> groupList;

    List<String> list1;
    List<String> list2;
    List<String> list3;


    @Before
    public void setUp() throws Exception {
        dataSource = new TreeDataSource<>();
        dataSource.setExpandable(true);

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

        dataSource.addData(list1);
        dataSource.addData(list2);
        dataSource.addData(list3);
        dataSource.setGroups(groupList);
    }

    @Test
    public void testViewType() {
        assertEquals(ViewTypeConstants.VIEW_TYPE_GROUP, dataSource.getViewType(0));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(1));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(2));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(3));
        assertEquals(ViewTypeConstants.VIEW_TYPE_GROUP, dataSource.getViewType(4));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(5));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(6));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(7));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(8));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(9));
        assertEquals(ViewTypeConstants.VIEW_TYPE_GROUP, dataSource.getViewType(10));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(11));
    }

    @Test
    public void testViewTypeCollapsed() {
        dataSource.collapseGroup(0);

        assertEquals(ViewTypeConstants.VIEW_TYPE_GROUP, dataSource.getViewType(0));
        assertEquals(ViewTypeConstants.VIEW_TYPE_GROUP, dataSource.getViewType(1));
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(2));
    }

    @Test
    public void testItemCount() {
        assertEquals(12, dataSource.getVisibleDataCount());
    }

    @Test
    public void testChildOffsetInGroup() {
        assertEquals(0, dataSource.getChildOffsetInGroup(0));
        assertEquals(1, dataSource.getChildOffsetInGroup(1));
        assertEquals(2, dataSource.getChildOffsetInGroup(2));
        assertEquals(3, dataSource.getChildOffsetInGroup(3));

        assertEquals(0, dataSource.getChildOffsetInGroup(4));
        assertEquals(1, dataSource.getChildOffsetInGroup(5));
        assertEquals(2, dataSource.getChildOffsetInGroup(6));
        assertEquals(3, dataSource.getChildOffsetInGroup(7));
        assertEquals(4, dataSource.getChildOffsetInGroup(8));
        assertEquals(5, dataSource.getChildOffsetInGroup(9));

        assertEquals(0, dataSource.getChildOffsetInGroup(10));
        assertEquals(1, dataSource.getChildOffsetInGroup(11));
    }

    @Test
    public void testChildOffsetInGroupCollapsed() {
        dataSource.collapseGroup(0);

        assertEquals(0, dataSource.getChildOffsetInGroup(0));

        assertEquals(0, dataSource.getChildOffsetInGroup(1));
        assertEquals(1, dataSource.getChildOffsetInGroup(2));
    }

    @Test
    public void testGroupIdByVisiblePosition() {
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(0));
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(1));
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(2));
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(3));

        assertEquals(1, dataSource.getGroupIdByVisiblePosition(4));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(5));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(6));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(7));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(8));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(9));

        assertEquals(2, dataSource.getGroupIdByVisiblePosition(10));
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(11));
    }

    @Test
    public void testGroupIdByVisiblePositionCollapsed() {
        dataSource.collapseGroup(0);

        assertEquals(0, dataSource.getGroupIdByVisiblePosition(0));

        assertEquals(1, dataSource.getGroupIdByVisiblePosition(1));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(2));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(3));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(4));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(5));
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(6));

        assertEquals(2, dataSource.getGroupIdByVisiblePosition(7));
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(8));
    }

    @Test
    public void testChildrenCount() {
        assertEquals(list1.size(), dataSource.getChildrenCount(0));
        assertEquals(list2.size(), dataSource.getChildrenCount(1));
        assertEquals(list3.size(), dataSource.getChildrenCount(2));

        dataSource.clearData();
        assertEquals(0, dataSource.getChildrenCount(0));

        ArrayList<String> data = new ArrayList<>();
        data.add(null);
        data.add("element1");

        dataSource.addData(data);
        assertNull(dataSource.getChild(0, 0));

        data.set(0, "element0");
        dataSource.setData(0, data);
        assertEquals("element0", dataSource.getChild(0, 0));
        assertEquals("element1", dataSource.getChild(0, 1));
    }

    @Test
    public void testChildrenCountWithoutData() {
        dataSource.setData(0, new ArrayList<String>());
        assertEquals(0, dataSource.getChildrenCount(0));
    }

    @Test
    public void testCollapseGroup() {
        assertTrue("Group should be expanded", dataSource.isGroupExpanded(0));
        assertTrue(dataSource.collapseGroup(0));
        assertFalse("Group should be collapsed", dataSource.isGroupExpanded(0));
    }

    @Test
    public void testGetData() {
        assertEquals("Group1", dataSource.getVisibleData(0));
        assertEquals("List1: 0", dataSource.getVisibleData(1));
        assertEquals("List1: 1", dataSource.getVisibleData(2));
        assertEquals("List1: 2", dataSource.getVisibleData(3));
        assertEquals("Group2", dataSource.getVisibleData(4));
        assertEquals("List2: 0", dataSource.getVisibleData(5));
        assertEquals("List2: 1", dataSource.getVisibleData(6));
    }

    @Test
    public void testGetChild() {
        assertNull(dataSource.getChild(-1, 0));

        assertEquals("List1: 0", dataSource.getChild(0, 0));
        assertEquals("List1: 1", dataSource.getChild(0, 1));
        assertEquals("List1: 2", dataSource.getChild(0, 2));
        assertEquals("List2: 0", dataSource.getChild(1, 0));
        assertEquals("List2: 1", dataSource.getChild(1, 1));
        assertEquals("List2: 2", dataSource.getChild(1, 2));

        dataSource.setData(0, null);
        assertNull(dataSource.getChild(0, 0));
        assertNull(dataSource.getChild(0, 1));
        assertNull(dataSource.getChild(0, 2));

        dataSource.setData(0, new ArrayList<String>());
        assertNull(dataSource.getChild(0, 0));
    }

    @Test
    public void testGetChildOutOfRange() {
        assertNull(dataSource.getChild(-1, 0));
        assertNull(dataSource.getChild(10, 0));

        assertNull(dataSource.getChild(0, -1));
        assertNull(dataSource.getChild(0, 10));
    }

    @Test
    public void testGetChildOutsideRange() {
        assertNull("child should be null", dataSource.getChild(12, 0));
    }

    @Test
    public void testAddAllData() {
        Collection<List<String>> data = new ArrayList<>();

        List<String> child1 = new ArrayList<>();
        child1.add("Element1");
        child1.add("Element2");

        List<String> child2 = new ArrayList<>();
        child2.add("Element1");
        child2.add("Element2");

        data.add(child1);
        data.add(child2);

        assertEquals(3, dataSource.getGroupCount());
        dataSource.addAllData(new ArrayList<List<String>>());
        assertEquals(3, dataSource.getGroupCount());

        dataSource.addAllData(data);
        assertEquals(2, dataSource.getChildrenCount(3));
        assertEquals(2, dataSource.getChildrenCount(4));
        assertEquals(3, dataSource.getGroupCount());

        dataSource.addGroup("newGroup1");
        dataSource.addGroup("newGroup2");
        assertEquals(5, dataSource.getGroupCount());
    }

    @Test
    public void testSetData() {
        List<String> child = new ArrayList<>();
        child.add("Element1");

        dataSource.setData(0, child);
        assertEquals("children count should change", 1, dataSource.getChildrenCount(0));
        assertEquals("group count should not change", 3, dataSource.getGroupCount());
    }

    @Test
    public void testSetGroup() {
        String group = "NewGroup";

        String oldGroup = dataSource.getGroup(1);
        dataSource.setGroup(1, group);
        assertNotSame(oldGroup, dataSource.getGroup(1));
        assertEquals("group count should not change", 3, dataSource.getGroupCount());
    }

    @Test
    public void testRemoveData() throws Exception {
        dataSource.removeData(0);
        assertEquals(list2.size(), dataSource.getChildrenCount(0));
    }

    @Test
    public void testClear() throws Exception {
        dataSource.clear();
        assertEquals(0, dataSource.getGroupCount());
        assertEquals(0, dataSource.getVisibleDataCount());
    }

    @Test
    public void testClearData() throws Exception {
        dataSource.clearData();
        assertEquals(0, dataSource.getChildrenCount(0));
        assertEquals(3, dataSource.getVisibleDataCount());
    }

    @Test
    public void testClearGroup() throws Exception {
        dataSource.clearGroup();
        assertEquals(0, dataSource.getGroupCount());
        assertEquals(0, dataSource.getVisibleDataCount());
    }

    @Test
    public void testGetVisibleDataCount() throws Exception {
        assertEquals(dataSource.getVisibleDataCount(), dataSource.getVisibleDataCount());

        dataSource.removeData(0);
        assertEquals(9, dataSource.getVisibleDataCount());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertFalse(dataSource.isEmpty());
        dataSource.clearGroup();
        assertTrue(dataSource.isEmpty());
    }

    @Test
    public void testSetExpandable() throws Exception {
        // try collapse when disabled
        dataSource.setExpandable(false);
        int expanded = dataSource.getVisibleDataCount();
        assertFalse(dataSource.collapseGroup(0));
        assertEquals(expanded, dataSource.getVisibleDataCount());

        // collapse when enabled
        dataSource.setExpandable(true);
        assertTrue(dataSource.collapseGroup(0));
        assertFalse("should return false when trying to collapse an collapsed group", dataSource.collapseGroup(0));
        int collapsed = dataSource.getVisibleDataCount();
        assertNotEquals(expanded, collapsed);

        // try expand when disabled
        dataSource.setExpandable(false);
        assertFalse(dataSource.expandGroup(0));
        assertEquals(collapsed, dataSource.getVisibleDataCount());

        // expand when enabled
        dataSource.setExpandable(true);
        assertTrue(dataSource.expandGroup(0));
        assertFalse("should return false when trying to expand an expanded group", dataSource.expandGroup(0));
        assertEquals(expanded, dataSource.getVisibleDataCount());
    }

    @Test
    public void testDefaultExpanded() {
        assertTrue(dataSource.isGroupDefaultExpanded());
        dataSource.setGroupDefaultExpanded(false);
        assertFalse(dataSource.isGroupDefaultExpanded());
    }

    @Test
    public void testGetGroupPosition() {
        assertEquals(0, dataSource.getGroupPosition(0));
        assertEquals(4, dataSource.getGroupPosition(1));
        assertEquals(10, dataSource.getGroupPosition(2));

        dataSource.collapseGroup(0);
        assertEquals(0, dataSource.getGroupPosition(0));
        assertEquals(1, dataSource.getGroupPosition(1));
        assertEquals(7, dataSource.getGroupPosition(2));
    }

    @Test
    public void testGetChildrenCount() {
        assertEquals(3, dataSource.getChildrenCount(0));
        dataSource.setData(0, null);
        assertEquals(0, dataSource.getChildrenCount(0));
    }
}
