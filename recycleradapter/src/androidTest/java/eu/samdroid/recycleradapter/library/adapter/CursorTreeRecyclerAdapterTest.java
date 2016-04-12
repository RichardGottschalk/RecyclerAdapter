package eu.samdroid.recycleradapter.library.adapter;

import android.test.mock.MockCursor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Gottschalk
 */
public class CursorTreeRecyclerAdapterTest {

    MockCursor childCursor = new MockCursor() {
        int position = -1;

        @Override
        public String getString(int columnIndex) {
            return "childCursor" + position;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean moveToPosition(int position) {
            this.position = position;
            return true;
        }
    };

    MockCursor groupCursor = new MockCursor() {
        int position = -1;

        @Override
        public String getString(int columnIndex) {
            return "groupCursor" + position;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean moveToPosition(int position) {
            this.position = position;
            return true;
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
    public void testSetChildrenCursorWithGroup() throws Exception {
        adapter.swapGroupCursor(groupCursor);
        adapter.setChildrenCursor(0, childCursor);

        assertEquals(5, adapter.getItemCount());

        adapter.setChildrenCursor(0, childCursor);
    }

    @Test
    public void testSetChildrenCursorWithoutGroup() throws Exception {
        adapter.setChildrenCursor(0, childCursor);

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testSetChildrenCursorNullWithGroup() throws Exception {
        adapter.swapGroupCursor(groupCursor);
        adapter.setChildrenCursor(0, null);

        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testSetChildrenCursorNullWithoutGroup() throws Exception {
        adapter.setChildrenCursor(0, null);

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testGetRecyclerAdapterDataSource() throws Exception {
        assertNotNull(adapter.getRecyclerAdapterDataSource());
    }
}