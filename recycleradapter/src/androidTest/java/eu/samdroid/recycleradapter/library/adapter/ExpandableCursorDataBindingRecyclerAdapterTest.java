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
public class ExpandableCursorDataBindingRecyclerAdapterTest {

    ExpandableCursorDataBindingRecyclerAdapter adapter;

    @Test
    public void testConstructor() {
        List<BindingInformation> bindingInformations = new ArrayList<>();

        bindingInformations.add(new BindingInformation(ViewTypeConstants.VIEW_TYPE_DEFAULT, 0, 0));

        adapter = new ExpandableCursorDataBindingRecyclerAdapter(bindingInformations);

        assertNotNull(adapter);
    }
}
