package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.MockedCursor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Richard Gottschalk
 */
public class CursorRecyclerAdapterTest {

    Cursor cursor = new MockedCursor(3) {

        @Override
        public String getString(int columnIndex) {
            return "cursor" + getPosition();
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