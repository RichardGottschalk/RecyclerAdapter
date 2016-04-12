package eu.samdroid.recycleradapter.library.adapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;
import eu.samdroid.recycleradapter.library.binding.BindingInformation;

import static org.junit.Assert.assertNotNull;

/**
 * @author Richard Gottschalk
 */
public class ArrayDataBindingRecyclerAdapterTest {

    ArrayDataBindingRecyclerAdapter<String> adapter;

    @Test
    public void testConstructor() {
        adapter = new ArrayDataBindingRecyclerAdapter<>(0, 0);

        assertNotNull(adapter);
    }

    @Test
    public void testConstructor2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) list.add("data" + i);

        adapter = new ArrayDataBindingRecyclerAdapter<>(0, 0, list.toArray(new String[list.size()]));

        assertNotNull(adapter);
    }

    @Test
    public void testConstructor3() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) list.add("data" + i);

        adapter = new ArrayDataBindingRecyclerAdapter<>(
                list,
                new BindingInformation(ViewTypeConstants.VIEW_TYPE_DEFAULT, 0, 0)
        );

        assertNotNull(adapter);
    }

    @Test
    public void testConstructor4() {
        adapter = new ArrayDataBindingRecyclerAdapter<>(
                null,
                new BindingInformation(ViewTypeConstants.VIEW_TYPE_DEFAULT, 0, 0)
        );

        assertNotNull(adapter);
    }
}
