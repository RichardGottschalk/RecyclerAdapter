package eu.samdroid.recycleradapter.library.adapter;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import eu.samdroid.recycleradapter.library.BasicRecyclerAdapter;
import eu.samdroid.recycleradapter.library.source.ArrayDataSource;

/**
 * A {@link android.support.v7.widget.RecyclerView.Adapter} designed
 * to deliver {@link List} features for a {@link android.support.v7.widget.RecyclerView}.
 *
 * Known Direct Subclasses:
 * {@link ArrayRecyclerAdapter}
 * {@link ArrayDataBindingRecyclerAdapter}
 *
 * Created by Richard Gottschalk.
 */
abstract class AbstractArrayRecyclerAdapter<DATA>
        extends BasicRecyclerAdapter
        implements List<DATA> {

    private ArrayDataSource<DATA> dataSource;

    public AbstractArrayRecyclerAdapter() {
        // create dataSource
        dataSource = new ArrayDataSource<>();
        setRecyclerAdapterDataSource(dataSource);
    }

    /**
     * Inserts the specified object into this {@code List} at the specified location.
     * The object is inserted before the current element at the specified
     * location. If the location is equal to the size of this {@code List}, the object
     * is added at the end. If the location is smaller than the size of this
     * {@code List}, then all elements beyond the specified location are moved by one
     * position towards the end of the {@code List}.
     *
     * @param location
     *            the index at which to insert.
     * @param object
     *            the object to add.
     * @throws UnsupportedOperationException
     *                if adding to this {@code List} is not supported.
     * @throws ClassCastException
     *                if the class of the object is inappropriate for this
     *                {@code List}.
     * @throws IllegalArgumentException
     *                if the object cannot be added to this {@code List}.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location > size()}
     */
    @Override
    public void add(int location, DATA object) {
        dataSource.add(location, object);
        notifyItemInserted(location);
    }

    /**
     * Adds the specified object at the end of this {@code List}.
     *
     * @param object
     *            the object to add.
     * @return always true.
     * @throws UnsupportedOperationException
     *                if adding to this {@code List} is not supported.
     * @throws ClassCastException
     *                if the class of the object is inappropriate for this
     *                {@code List}.
     * @throws IllegalArgumentException
     *                if the object cannot be added to this {@code List}.
     */
    @Override
    public boolean add(DATA object) {
        dataSource.add(object);
        notifyItemInserted(dataSource.getVisibleDataCount() - 1);
        return true;
    }

    /**
     * Inserts the objects in the specified collection at the specified location
     * in this {@code List}. The objects are added in the order they are returned from
     * the collection's iterator.
     *
     * @param location
     *            the index at which to insert.
     * @param collection
     *            the collection of objects to be inserted.
     * @return true if this {@code List} has been modified through the insertion, false
     *         otherwise (i.e. if the passed collection was empty).
     * @throws UnsupportedOperationException
     *                if adding to this {@code List} is not supported.
     * @throws ClassCastException
     *                if the class of an object is inappropriate for this
     *                {@code List}.
     * @throws IllegalArgumentException
     *                if an object cannot be added to this {@code List}.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location > size()}.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    @Override
    public boolean addAll(int location, Collection<? extends DATA> collection) {
        boolean addAll = dataSource.addAll(location, collection);
        if (addAll) notifyItemRangeChanged(location, collection.size());
        return addAll;
    }

    /**
     * Adds the objects in the specified collection to the end of this {@code List}. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param collection
     *            the collection of objects.
     * @return {@code true} if this {@code List} is modified, {@code false} otherwise
     *         (i.e. if the passed collection was empty).
     * @throws UnsupportedOperationException
     *                if adding to this {@code List} is not supported.
     * @throws ClassCastException
     *                if the class of an object is inappropriate for this
     *                {@code List}.
     * @throws IllegalArgumentException
     *                if an object cannot be added to this {@code List}.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    @Override
    public boolean addAll(Collection<? extends DATA> collection) {
        boolean addAll = dataSource.addAll(collection);
        if (addAll) notifyItemRangeInserted(dataSource.getVisibleDataCount() - collection.size(), collection.size());
        return addAll;
    }

    /**
     * Removes all elements from this {@code List}, leaving it empty.
     *
     * @throws UnsupportedOperationException
     *                if removing from this {@code List} is not supported.
     * @see #isEmpty
     * @see #size
     */
    @Override
    public void clear() {
        dataSource.clear();
        notifyDataSetChanged();
    }

    /**
     * Tests whether this {@code List} contains the specified object.
     *
     * @param object
     *            the object to search for.
     * @return {@code true} if object is an element of this {@code List}, {@code false}
     *         otherwise
     */
    @Override
    public boolean contains(Object object) {
        return dataSource.contains(object);
    }

    /**
     * Tests whether this {@code List} contains all objects contained in the
     * specified collection.
     *
     * @param collection
     *            the collection of objects
     * @return {@code true} if all objects in the specified collection are
     *         elements of this {@code List}, {@code false} otherwise.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        return dataSource.containsAll(collection);
    }

    /**
     * Returns the element at the specified location in this {@code List}.
     *
     * @param location
     *            the index of the element to return.
     * @return the element at the specified location.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location >= size()}
     */
    @Override
    public DATA get(int location) {
        return dataSource.get(location);
    }

    /**
     * Returns whether this {@code List} contains no elements.
     *
     * @return {@code true} if this {@code List} has no elements, {@code false}
     *         otherwise.
     * @see #size
     */
    @Override
    public boolean isEmpty() {
        return dataSource.isEmpty();
    }

    /**
     * Returns an iterator on the elements of this {@code List}. The elements are
     * iterated in the same order as they occur in the {@code List}.
     *
     * @return an iterator on the elements of this {@code List}.
     * @see Iterator
     */
    @NonNull
    @Override
    public Iterator<DATA> iterator() {
        return dataSource.iterator();
    }

    /**
     * Searches this {@code List} for the specified object and returns the index of the
     * last occurrence.
     *
     * @param object
     *            the object to search for.
     * @return the index of the last occurrence of the object, or -1 if the
     *         object was not found.
     */
    @Override
    public int lastIndexOf(Object object) {
        return dataSource.lastIndexOf(object);
    }

    /**
     * Returns a {@code List} iterator on the elements of this {@code List}. The elements are
     * iterated in the same order that they occur in the {@code List}.
     *
     * @return a {@code List} iterator on the elements of this {@code List}
     *
     * @see ListIterator
     */
    @Override
    public ListIterator<DATA> listIterator() {
        return dataSource.listIterator();
    }

    /**
     * Returns a list iterator on the elements of this {@code List}. The elements are
     * iterated in the same order as they occur in the {@code List}. The iteration
     * starts at the specified location.
     *
     * @param location
     *            the index at which to start the iteration.
     * @return a list iterator on the elements of this {@code List}.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location > size()}
     * @see ListIterator
     */
    @NonNull
    @Override
    public ListIterator<DATA> listIterator(int location) {
        return dataSource.listIterator(location);
    }

    /**
     * Removes the object at the specified location from this {@code List}.
     *
     * @param location
     *            the index of the object to remove.
     * @return the removed object.
     * @throws UnsupportedOperationException
     *                if removing from this {@code List} is not supported.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location >= size()}
     */
    @Override
    public DATA remove(int location) {
        DATA remove = dataSource.remove(location);
        if (remove != null) notifyItemRemoved(location);
        return remove;
    }

    /**
     * Removes the first occurrence of the specified object from this {@code List}.
     *
     * @param object
     *            the object to remove.
     * @return true if this {@code List} was modified by this operation, false
     *         otherwise.
     * @throws UnsupportedOperationException
     *                if removing from this {@code List} is not supported.
     */
    @Override
    public boolean remove(Object object) {
        int location = dataSource.indexOf(object);
        boolean remove = dataSource.remove(object);
        if (remove) notifyItemRemoved(location);
        return remove;
    }

    /**
     * Removes all occurrences in this {@code List} of each object in the specified
     * collection.
     *
     * @param collection
     *            the collection of objects to remove.
     * @return {@code true} if this {@code List} is modified, {@code false} otherwise.
     * @throws UnsupportedOperationException
     *                if removing from this {@code List} is not supported.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean removeAll = dataSource.removeAll(collection);
        if (removeAll) notifyDataSetChanged();
        return removeAll;
    }

    /**
     * Removes all objects from this {@code List} that are not contained in the
     * specified collection.
     *
     * @param collection
     *            the collection of objects to retain.
     * @return {@code true} if this {@code List} is modified, {@code false} otherwise.
     * @throws UnsupportedOperationException
     *                if removing from this {@code List} is not supported.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = dataSource.retainAll(collection);
        if (retainAll) notifyDataSetChanged();
        return retainAll;
    }

    /**
     * Replaces the element at the specified location in this {@code List} with the
     * specified object. This operation does not change the size of the {@code List}.
     *
     * @param location
     *            the index at which to put the specified object.
     * @param object
     *            the object to insert.
     * @return the previous element at the index.
     * @throws UnsupportedOperationException
     *                if replacing elements in this {@code List} is not supported.
     * @throws ClassCastException
     *                if the class of an object is inappropriate for this
     *                {@code List}.
     * @throws IllegalArgumentException
     *                if an object cannot be added to this {@code List}.
     * @throws IndexOutOfBoundsException
     *                if {@code location < 0 || location >= size()}
     */
    @Override
    public DATA set(int location, DATA object) {
        DATA set = dataSource.set(location, object);
        notifyItemChanged(location);
        return set;
    }

    /**
     * Returns the number of elements in this {@code List}.
     *
     * @return the number of elements in this {@code List}.
     */
    @Override
    public int size() {
        return dataSource.size();
    }

    /**
     * Returns a {@code List} of the specified portion of this {@code List} from the given start
     * index to the end index minus one. The returned {@code List} is backed by this
     * {@code List} so changes to it are reflected by the other.
     *
     * @param start
     *            the index at which to start the sublist.
     * @param end
     *            the index one past the end of the sublist.
     * @return a list of a portion of this {@code List}.
     * @throws IndexOutOfBoundsException
     *                if {@code start < 0, start > end} or {@code end >
     *                size()}
     */
    @NonNull
    @Override
    public List<DATA> subList(int start, int end) {
        return dataSource.subList(start, end);
    }

    /**
     * Returns an array containing all elements contained in this {@code List}.
     *
     * @return an array of the elements from this {@code List}.
     */
    @NonNull
    @Override
    public Object[] toArray() {
        return dataSource.toArray();
    }

    /**
     * Returns an array containing all elements contained in this {@code List}. If the
     * specified array is large enough to hold the elements, the specified array
     * is used, otherwise an array of the same type is created. If the specified
     * array is used and is larger than this {@code List}, the array element following
     * the collection elements is set to null.
     *
     * @param array
     *            the array.
     * @return an array of the elements from this {@code List}.
     * @throws ArrayStoreException
     *                if the type of an element in this {@code List} cannot be stored
     *                in the type of the specified array.
     */
    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return dataSource.toArray(array);
    }

    /**
     * Searches this {@code List} for the specified object and returns the index of the
     * first occurrence.
     *
     * @param object
     *            the object to search for.
     * @return the index of the first occurrence of the object or -1 if the
     *         object was not found.
     */
    @Override
    public int indexOf(Object object) {
        return dataSource.indexOf(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
