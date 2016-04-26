package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.MockedCursor;
import eu.samdroid.recycleradapter.library.source.CursorTreeDataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Richard Gottschalk
 */
public class CursorTreeRecyclerAdapterTest {

    Cursor childCursor = new MockedCursor(3) {

        @Override
        public String getString(int columnIndex) {
            if (columnIndex == 1) {
                if (getPosition() <= 1) {
                    return "1";
                } else {
                    return "2";
                }
            }
            return "childCursor" + getPosition();
        }
    };

    Cursor groupCursor = new MockedCursor(2) {

        @Override
        public String getString(int columnIndex) {
            return "groupCursor" + getPosition();
        }
    };

    CursorTreeRecyclerAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new CursorTreeRecyclerAdapter();
    }

    @Test
    public void testSwapGroupCursor() throws Exception {
        assertNull(adapter.swapGroupCursor(groupCursor));
        assertEquals(groupCursor, adapter.swapGroupCursor(null));
    }

    @Test
    public void testSetChildrenCursor() throws Exception {
        adapter.setChildrenCursor(childCursor, null);

        assertNotNull(adapter.getItemCount());
    }

    @Test
    public void testSetChildrenCursorWithGroup() throws Exception {
        adapter.swapGroupCursor(groupCursor);
        adapter.setChildrenCursor(childCursor, 1);

        assertEquals(5, adapter.getItemCount());

        adapter.setChildrenCursor(childCursor);
    }

    @Test
    public void testGetChildrenCount() throws Exception {
        adapter.swapGroupCursor(groupCursor);
        adapter.setChildrenCursor(childCursor, 1);

        CursorTreeDataSource dataSource = adapter.getRecyclerAdapterDataSource();
        assertEquals(2, dataSource.getChildrenCount(0));
        assertEquals(1, dataSource.getChildrenCount(1));
    }

    @Test
    public void testSetChildrenCursorWithoutGroup() throws Exception {
        adapter.setChildrenCursor(childCursor, 1);

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testSetChildrenCursorNullWithGroup() throws Exception {
        adapter.swapGroupCursor(groupCursor);
        adapter.setChildrenCursor(null);

        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testSetChildrenCursorNullWithoutGroup() throws Exception {
        adapter.setChildrenCursor(null);

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testGetRecyclerAdapterDataSource() throws Exception {
        assertNotNull(adapter.getRecyclerAdapterDataSource());
    }
}