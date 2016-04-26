package eu.samdroid.recycleradapter.library.adapter;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.MockedCursor;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Richard Gottschalk
 */
public class CursorDataBindingRecyclerAdapterTest {

    Cursor cursor = new MockedCursor(3) {

        @Override
        public String getString(int columnIndex) {
            return "cursor" + getPosition();
        }

    };

    CursorDataBindingRecyclerAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new CursorDataBindingRecyclerAdapter(
                new BindingInformation(ViewTypeConstants.VIEW_TYPE_DEFAULT, 0, 0)
        );
    }

    @Test
    public void testSwapCursor() throws Exception {
        assertNull(adapter.swapCursor(cursor));
        assertEquals(cursor, adapter.swapCursor(null));
    }
}
