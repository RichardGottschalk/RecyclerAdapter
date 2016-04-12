package eu.samdroid.recycleradapter.library.adapter;

import android.test.mock.MockCursor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Gottschalk
 */
public class CursorRecyclerAdapterTest {

    MockCursor cursor = new MockCursor() {
        int position = -1;

        @Override
        public String getString(int columnIndex) {
            return "cursor" + position;
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

    CursorRecyclerAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new CursorRecyclerAdapter(0, new String[]{""}, new int[]{0});
    }

    @Test
    public void testConstructor() throws Exception {
        adapter = new CursorRecyclerAdapter(cursor, 0, new String[]{""}, new int[]{0});
        assertNotNull(adapter);
    }

    @Test
    public void testSwapCursor() throws Exception {
        assertNull(adapter.swapCursor(cursor));
        assertEquals(cursor, adapter.swapCursor(null));
    }
}