package eu.samdroid.recycleradapter.library.adapter;

import android.test.mock.MockCursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Richard Gottschalk
 */
public class CursorDataBindingRecyclerAdapterTest {

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
