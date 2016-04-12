package eu.samdroid.recycleradapter.library.adapter;

import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;

/**
 * @author Richard Gottschalk
 */
public class ExpandableCursorTreeRecyclerAdapter extends CursorTreeRecyclerAdapter {

    public ExpandableCursorTreeRecyclerAdapter(CursorViewTypeInformation... cursorViewTypeInformations) {
        super(cursorViewTypeInformations);

        getRecyclerAdapterDataSource().setExpandable(true);
    }
}
