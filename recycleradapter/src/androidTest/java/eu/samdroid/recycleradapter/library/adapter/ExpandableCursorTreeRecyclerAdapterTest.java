package eu.samdroid.recycleradapter.library.adapter;

import org.junit.Test;

import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Richard Gottschalk
 */
public class ExpandableCursorTreeRecyclerAdapterTest {

    ExpandableCursorTreeRecyclerAdapter adapter;

    @Test
    public void testConstructorDefault() throws Exception {
        adapter = new ExpandableCursorTreeRecyclerAdapter();

        assertNotNull(adapter);
    }

    @Test
    public void testConstructorParameterized() throws Exception {
        CursorViewTypeInformation cursorViewTypeInformation
                = new CursorViewTypeInformation(0, new String[]{""}, new int[] {0});

        adapter = new ExpandableCursorTreeRecyclerAdapter(cursorViewTypeInformation);

        assertNotNull(adapter);
    }
}
