package eu.samdroid.recycleradapter.library.source;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.MockedCursor;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Richard Gottschalk.
 */
public class CursorTreeDataSourceTest {

    CursorTreeDataSource dataSource;

    Cursor dataMock;
    Cursor groupMock;

    @Before
    public void setUp() throws Exception {
        dataSource = new CursorTreeDataSource();
        dataSource.setExpandable(true);

        dataMock = new MockedCursor(9) {

            @Override
            public int getCount() {
                return 9;
            }

            @Override
            public String getString(int columnIndex) {
                if (columnIndex == 1) {
                    if (getPosition() <= 1) return "1";
                    if (getPosition() <= 4) return "2";
                    if (getPosition() <= 8) return "3";
                }
                return "Item" + getPosition();
            }
        };

        groupMock = new MockedCursor(3) {
            int position;

            @Override
            public boolean moveToFirst() {
                return true;
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
    }

    @Test
    public void testGetGroupCount() {
        assertEquals(0, dataSource.getGroupCount());

        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        assertEquals(3, dataSource.getGroupCount());
    }

    @Test
    public void testGetChildrenCount() {
        assertEquals(0, dataSource.getChildrenCount(5));

        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        assertEquals(2, dataSource.getChildrenCount(0));
        assertEquals(3, dataSource.getChildrenCount(1));
        assertEquals(4, dataSource.getChildrenCount(2));
    }

    @Test
    public void testGroupIdByVisiblePosition() {
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

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
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

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
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        dataSource.swapGroupCursor(null);
        assertEquals(0, dataSource.getGroupCount());
    }

    @Test
    public void testGetChildrenCountWithoutCursor() throws Exception {
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        assertEquals(2, dataSource.getChildrenCount(0));

        dataSource.setChildrenCursor(null);
        assertEquals(0, dataSource.getChildrenCount(0));
    }

    @Test
    public void testGetGroup() throws Exception {
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        assertEquals("Group2", dataSource.getGroup(2).getString(0));
    }

    @Test
    public void testGetGroupWithoutGroups() throws Exception {
        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        dataSource.swapGroupCursor(null);
        assertNull(dataSource.getGroup(2));
    }

    @Test
    public void testGetChild() throws Exception {
        assertNull(dataSource.getChild(0, 1));

        dataSource.setChildrenCursor(dataMock, 1);
        dataSource.swapGroupCursor(groupMock);

        assertNotNull(dataSource.getChild(0, 1));

        dataSource.setChildrenCursor(null);
        assertNull(dataSource.getChild(0, 1));

        assertNull(dataSource.getChild(0, 1));
    }
}
