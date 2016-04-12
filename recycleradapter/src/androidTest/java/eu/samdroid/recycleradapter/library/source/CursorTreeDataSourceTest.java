package eu.samdroid.recycleradapter.library.source;

import android.test.mock.MockCursor;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Richard Gottschalk.
 */
public class CursorTreeDataSourceTest {

    CursorTreeDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new CursorTreeDataSource();
        dataSource.setExpandable(true);

        MockCursor dataMock2 = new MockCursor() {
            int position;

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean moveToPosition(int position) {
                this.position = position;
                return true;
            }

            @Override
            public String getString(int columnIndex) {
                return "Item" + position;
            }
        };

        MockCursor dataMock3 = new MockCursor() {
            int position;

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean moveToPosition(int position) {
                this.position = position;
                return true;
            }

            @Override
            public String getString(int columnIndex) {
                return "Item" + position;
            }
        };

        MockCursor dataMock4 = new MockCursor() {
            int position;

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean moveToPosition(int position) {
                this.position = position;
                return true;
            }

            @Override
            public String getString(int columnIndex) {
                return "Item" + position;
            }
        };

        MockCursor groupMock = new MockCursor() {
            int position;

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean moveToPosition(int position) {
                this.position = position;
                return true;
            }

            @Override
            public String getString(int columnIndex) {
                return "Group" + position;
            }
        };

        dataSource.setChildrenCursor(0, dataMock2);
        dataSource.setChildrenCursor(1, dataMock3);
        dataSource.setChildrenCursor(2, dataMock4);
        dataSource.swapGroupCursor(groupMock);
    }

    @Test
    public void testGetGroupCount() {
        assertEquals(3, dataSource.getGroupCount());
    }

    @Test
    public void testGetChildrenCount() {
        assertEquals(2, dataSource.getChildrenCount(0));
        assertEquals(3, dataSource.getChildrenCount(1));
        assertEquals(4, dataSource.getChildrenCount(2));
    }

    @Test
    public void testGroupIdByVisiblePosition() {
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(0)); // group
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(1)); // item
        assertEquals(0, dataSource.getGroupIdByVisiblePosition(2)); // item

        assertEquals(1, dataSource.getGroupIdByVisiblePosition(3)); // group
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(4)); // item
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(5)); // item
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(6)); // item

        assertEquals(2, dataSource.getGroupIdByVisiblePosition(7)); // group
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(8)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(9)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(10)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(11)); // item
    }

    @Test
    public void testGroupIdByVisiblePositionCollapsed() {
        dataSource.collapseGroup(0);

        assertEquals(0, dataSource.getGroupIdByVisiblePosition(0)); // group

        assertEquals(1, dataSource.getGroupIdByVisiblePosition(1)); // group
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(2)); // item
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(3)); // item
        assertEquals(1, dataSource.getGroupIdByVisiblePosition(4)); // item

        assertEquals(2, dataSource.getGroupIdByVisiblePosition(5)); // group
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(6)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(7)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(8)); // item
        assertEquals(2, dataSource.getGroupIdByVisiblePosition(9)); // item
    }

    @Test
    public void testGetGroupCountWithoutGroupCursor() throws Exception {
        dataSource.swapGroupCursor(null);
        assertEquals(0, dataSource.getGroupCount());
    }

    @Test
    public void testGetChildrenCountWithoutCursor() throws Exception {
        assertEquals(2, dataSource.getChildrenCount(0));

        dataSource.setChildrenCursor(0, null);
        assertEquals(0, dataSource.getChildrenCount(0));
    }

    @Test
    public void testGetGroup() throws Exception {
        assertEquals("Group2", dataSource.getGroup(2).getString(0));
    }

    @Test
    public void testGetGroupWithoutGroups() throws Exception {
        dataSource.swapGroupCursor(null);
        assertNull(dataSource.getGroup(2));
    }

    @Test
    public void testGetChild() throws Exception {
        assertNotNull(dataSource.getChild(0, 1));

        dataSource.setChildrenCursor(0, null);
        assertNull(dataSource.getChild(0, 1));
    }
}
