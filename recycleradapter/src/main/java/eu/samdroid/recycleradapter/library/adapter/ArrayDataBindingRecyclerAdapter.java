package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.samdroid.recycleradapter.library.binding.BindingInformation;
import eu.samdroid.recycleradapter.library.binding.SimpleDataBindingViewHandler;

/**
 * Created by Richard Gottschalk.
 */
public class ArrayDataBindingRecyclerAdapter<DATA> extends AbstractArrayRecyclerAdapter<DATA> {

    /**
     * The simplest way to create a DataBinding RecyclerAdapter.
     * This supports only one layout with one binding.
     *
     * @param layout a dataBinding supporting layout
     * @param bindingId the bindingId in <code>layout</code> where any data should be bound to
     */
    public ArrayDataBindingRecyclerAdapter(@LayoutRes int layout,
                                           @IdRes int bindingId) {
        this(layout, bindingId, new ArrayList<DATA>());
    }

    /**
     * Creates a simple DataBinding Adapter with an initial set of data.
     * In case {@code data} is <code>null</code> no data will be added.
     *
     * @param layout a dataBinding supporting layout
     * @param bindingId the bindingId in <code>layout</code> where <code>data</code> should be bound to
     * @param data a list of initial data or <code>null</code> to add no data
     */
    public ArrayDataBindingRecyclerAdapter(@LayoutRes int layout,
                                           @IdRes int bindingId,
                                           @Nullable DATA[] data) {
        this(Arrays.asList(data), new BindingInformation(0, layout, bindingId));
    }

    /**
     * Creates a simple DataBinding Adapter with an initial set of data.
     * In case {@code data} is <code>null</code> no data will be added.
     *
     * @param layout a dataBinding supporting layout
     * @param bindingId the bindingId in <code>layout</code> where <code>data</code> should be bound to
     * @param data a list of initial data or <code>null</code> to add no data
     */
    public ArrayDataBindingRecyclerAdapter(@LayoutRes int layout,
                                           @IdRes int bindingId,
                                           @Nullable List<DATA> data) {
        this(data, new BindingInformation(0, layout, bindingId));
    }

    /**
     * Creates a more complex DataBinding Adapter with an initial set of data.
     *
     * Each BindingInformation represents one TYPE of view and it's data.
     *
     * @param data a list of initial data or <code>null</code> to add no data
     * @param bindingInformations some {@link BindingInformation}s. Do not keep it empty!
     */
    public ArrayDataBindingRecyclerAdapter(@Nullable List<DATA> data,
                                           BindingInformation ... bindingInformations) {
        super();
        if (data != null) addAll(data);

        // create viewHandler
        SimpleDataBindingViewHandler<DATA> viewHandler
                = new SimpleDataBindingViewHandler<>(bindingInformations);
        setRecyclerAdapterViewHandler(viewHandler);
    }
}
