package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.samdroid.recycleradapter.library.binding.ArrayViewHandler;
import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;

/**
 * A simple {@link android.support.v7.widget.RecyclerView.Adapter} with {@link List} features.
 *
 * This adapter supports only one layout for all elements and one {@link android.widget.TextView}.
 *
 * @see android.widget.ArrayAdapter
 * Created by Richard Gottschalk.
 */
public class ArrayRecyclerAdapter<DATA> extends AbstractArrayRecyclerAdapter<DATA> {

    public ArrayRecyclerAdapter(@LayoutRes int layout,
                                @IdRes int textViewRes) {
        this(layout, textViewRes, new ArrayList<DATA>());
    }
    public ArrayRecyclerAdapter(@LayoutRes int layout,
                                @IdRes int textViewRes,
                                DATA[] data) {
        this(layout, textViewRes, Arrays.asList(data));
    }

    public ArrayRecyclerAdapter(@LayoutRes int layout,
                                @IdRes int textViewRes,
                                @Nullable List<DATA> data) {
        super();
        if (data != null) addAll(data);

        // create viewHandler
        ArrayViewHandler viewHandler = new ArrayViewHandler(
                new ArrayViewTypeInformation(layout, textViewRes)
        );
        setRecyclerAdapterViewHandler(viewHandler);
    }
}
