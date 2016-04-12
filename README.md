RecyclerAdapter
===============

This library contains a set of RecyclerAdapter with different functionalities.

There is an adapter for each combination of following features:
 * Data Source:
  * Arrays
  * Cursor
 * List Type:
  * linear
  * tree
  * expandable
 * Data Mapping:
  * Array element to view ID
  * Cursor to view ID
  * DataBinding

Example
-------
In this repository is a sample app. You can see how you can use different RecyclerAdapter.

This is a simple example for a CursorRecyclerAdapter:
```Java
recyclerAdapter = new CursorRecyclerAdapter(
        R.layout.container_simple_element,
        new String[]{
                "text"
        },
        new int[] {
                R.id.textView1
        });

recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(recyclerAdapter);
```

And this is for expandable RecyclerAdapter based on Cursors and DataBinding
```Java
ExpandableCursorDataBindingRecyclerAdapter recyclerAdapter = new ExpandableCursorDataBindingRecyclerAdapter(
        new BindingInformation(
                ViewTypeConstants.VIEW_TYPE_DEFAULT,
                R.layout.container_expandable_databinding_cursor_child,
                BR.cursor
        ),
        new BindingInformation(
                ViewTypeConstants.VIEW_TYPE_GROUP,
                R.layout.container_expandable_databinding_cursor_group,
                BR.cursor
        )
);

recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(recyclerAdapter);
```

Please note:
For Cursor based RecyclerAdapter you need to do the queries yourself and set the resulting Cursors to the RecyclerAdapter.

Customizing
-----------
The library is based on one BasicRecyclerAdapter with a custom 
RecyclerAdapterDataSource as data source and a
RecyclerAdapterViewHandler for mapping your data to views.

You can easily create your custom RecyclerAdapter or expand one of the other RecyclerAdapters.

