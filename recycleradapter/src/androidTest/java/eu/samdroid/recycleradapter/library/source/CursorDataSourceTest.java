package eu.samdroid.recycleradapter.library.source;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;

import eu.samdroid.recycleradapter.library.MockedCursor;
import eu.samdroid.recycleradapter.library.ViewTypeConstants;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class CursorDataSourceTest {

    CursorDataSource dataSource;

    Cursor cursor;

    @Before
    public void setUp() throws Exception {
        dataSource = new CursorDataSource();

        cursor = new MockedCursor(4) {

            @Override
            public String getString(int columnIndex) {
                return "Element" + getPosition();
            }
        };
    }

    @Test
    public void testConstructorParameterized() {
        dataSource = new CursorDataSource(cursor);

        assertEquals(4, dataSource.getVisibleDataCount());
    }

    @Test
    public void testGetVisibleDataCount() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());

        dataSource.swapCursor(cursor);

        assertEquals(4, dataSource.getVisibleDataCount());
    }

    @Test
    public void testGetVisibleData() throws Exception {
        assertNull(dataSource.getVisibleData(0));

        dataSource.swapCursor(cursor);

        assertEquals("Element0", dataSource.getVisibleData(0).getString(0));
        assertEquals("Element1", dataSource.getVisibleData(1).getString(0));
    }

    @Test
    public void testGetViewType() throws Exception {
        dataSource.swapCursor(cursor);
        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(0));
    }

    @Test
    public void testSwapCursor() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        assertNull(dataSource.swapCursor(cursor));
        assertEquals(cursor, dataSource.swapCursor(cursor));
    }
}
