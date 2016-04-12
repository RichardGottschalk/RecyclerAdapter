package eu.samdroid.recycleradapter.library.adapter;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Richard Gottschalk
 */
@RunWith(AndroidJUnit4.class)
public class ArrayRecyclerAdapterTest extends AndroidTestCase {

    private ArrayRecyclerAdapter<String> adapter;

    private ArrayList<String> list;

    @Before
    public void setUp() throws Exception {
        adapter = new ArrayRecyclerAdapter<>(0, 0);

        list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("Element" + i);
        }
    }

    @Test
    public void testConstructor() {
        String[] data = list.toArray(new String[list.size()]);

        adapter = new ArrayRecyclerAdapter<>(0, 0, data);
        assertNotNull(adapter);
        assertEquals(data.length, adapter.getItemCount());
    }

    @Test
    public void testConstructor2() {
        List<String> list = null;

        adapter = new ArrayRecyclerAdapter<>(0, 0, list);
        assertNotNull(adapter);
        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testAdd() throws Exception {
        assertTrue(adapter.add("custom"));
        assertEquals("custom", adapter.get(0));
        assertEquals(1, adapter.size());
    }

    @Test
    public void testAdd1() throws Exception {
        adapter.add("custom0");
        adapter.add(0, "custom1");

        assertEquals("custom1", adapter.get(0));
        assertEquals("custom0", adapter.get(1));
    }

    @Test
    public void testAddAll() throws Exception {
        adapter.addAll(list);

        assertEquals(10, adapter.getItemCount());
    }

    @Test
    public void testAddAll1() throws Exception {
        adapter.add("custom");

        adapter.addAll(0, list);

        assertEquals(11, adapter.size());

        assertFalse(adapter.addAll(0, new ArrayList<String>()));
        assertEquals(11, adapter.size());
    }

    @Test
    public void testClear() throws Exception {
        adapter.add("custom");
        assertEquals(1, adapter.size());

        adapter.clear();
        assertEquals(0, adapter.size());
    }

    @Test
    public void testContains() throws Exception {
        adapter.addAll(list);
        assertTrue(adapter.contains("Element1"));

        adapter.clear();
        assertFalse(adapter.contains("Element1"));
    }

    @Test
    public void testContainsAll() throws Exception {
        adapter.addAll(list);

        ArrayList<String> copy = new ArrayList<>();
        copy.addAll(list);

        assertTrue(adapter.containsAll(copy));

        adapter.remove(0);
        assertFalse(adapter.containsAll(copy));
    }

    @Test
    public void testGet() throws Exception {
        adapter.add("custom");
        assertEquals("custom", adapter.get(0));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(adapter.isEmpty());
        adapter.addAll(list);
        assertFalse(adapter.isEmpty());
        adapter.clear();
        assertTrue(adapter.isEmpty());
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<String> iterator = adapter.iterator();
        assertNotNull(iterator);
        assertFalse(iterator.hasNext());

        adapter.addAll(list);
        iterator = adapter.iterator();
        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testLastIndexOf() throws Exception {
        adapter.addAll(list);
        assertEquals(0, adapter.lastIndexOf("Element0"));
        assertEquals(1, adapter.lastIndexOf("Element1"));

        adapter.add("Element0");
        assertEquals(10, adapter.lastIndexOf("Element0"));
        assertEquals(1, adapter.lastIndexOf("Element1"));
    }

    @Test
    public void testListIterator() throws Exception {
        adapter.addAll(list);

        ListIterator<String> iterator = adapter.listIterator();
        assertNotNull(iterator);

        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testListIterator1() throws Exception {
        adapter.addAll(list);

        ListIterator<String> iterator = adapter.listIterator(1);
        assertNotNull(iterator);

        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
    }

    @Test
    public void testRemove() throws Exception {
        adapter.addAll(list);

        assertEquals("Element0", adapter.remove(0));
        assertEquals("Element1", adapter.remove(0));
        assertEquals(8, adapter.size());

        adapter.add(null);
        assertNull(adapter.remove(8));
    }

    @Test
    public void testRemove1() throws Exception {
        adapter.addAll(list);

        assertTrue(adapter.remove("Element1"));
        assertFalse(adapter.remove("Element1"));
    }

    @Test
    public void testRemoveAll() throws Exception {
        adapter.addAll(list);

        assertEquals(10, adapter.size());
        assertFalse(adapter.removeAll(new ArrayList<String>()));
        assertEquals(10, adapter.size());
        assertTrue(adapter.removeAll(list));
        assertEquals(0, adapter.size());
    }

    @Test
    public void testRetainAll() throws Exception {
        adapter.addAll(list);

        List<String> retain = new ArrayList<>();
        retain.add("Element1");

        assertTrue(adapter.retainAll(retain));
        assertEquals(1, adapter.size());
        assertFalse(adapter.retainAll(retain));
        assertTrue(adapter.retainAll(new ArrayList<>()));
        assertFalse(adapter.retainAll(new ArrayList<>()));
    }

    @Test
    public void testSet() throws Exception {
        adapter.addAll(list);

        assertEquals("Element0", adapter.set(0, "custom"));
        assertEquals(10, adapter.size());

        adapter.set(0, null);
        assertEquals(10, adapter.size());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, adapter.size());
        assertEquals(0, adapter.getItemCount());

        adapter.addAll(list);
        assertEquals(10, adapter.size());
        assertEquals(10, adapter.getItemCount());

        adapter.remove(0);
        assertEquals(9, adapter.size());
        assertEquals(9, adapter.getItemCount());
    }

    @Test
    public void testSubList() throws Exception {
        adapter.addAll(list);

        List<String> sublist = adapter.subList(2, 4);

        assertEquals(2, sublist.size());
        assertEquals("Element2", sublist.get(0));
        assertEquals("Element3", sublist.get(1));
    }

    @Test
    public void testToArray() throws Exception {
        String[] array = adapter.toArray(new String[adapter.size()]);
        assertEquals(0, array.length);

        adapter.addAll(list);
        array = adapter.toArray(new String[adapter.size()]);
        assertEquals(10, array.length);
    }

    @Test
    public void testToArray1() throws Exception {
        Object[] objects = adapter.toArray();
        assertEquals(0, objects.length);

        adapter.addAll(list);
        objects = adapter.toArray();
        assertEquals(10, objects.length);
    }

    @Test
    public void testIndexOf() throws Exception {
        assertEquals(-1, adapter.indexOf("custom"));

        adapter.addAll(list);
        assertEquals(-1, adapter.indexOf("custom"));
        assertEquals(1, adapter.indexOf("Element1"));
    }
}
