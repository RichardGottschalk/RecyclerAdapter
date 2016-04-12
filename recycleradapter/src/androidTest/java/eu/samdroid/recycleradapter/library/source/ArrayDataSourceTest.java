package eu.samdroid.recycleradapter.library.source;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import eu.samdroid.recycleradapter.library.ViewTypeConstants;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
public class ArrayDataSourceTest {

    private ArrayDataSource<String> dataSource;

    private List<String> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        dataSource = new ArrayDataSource<>();

        for (int i = 0; i < 10; i++) {
            list.add("Element" + i);
        }
    }

    @Test
    public void testGetVisibleDataCount() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());

        dataSource.addAll(list);

        assertEquals(list.size(), dataSource.getVisibleDataCount());
    }

    @Test
    public void testGetVisibleData() throws Exception {
        assertNull(dataSource.getVisibleData(0));

        dataSource.addAll(list);

        assertEquals("Element0", dataSource.getVisibleData(0));
        assertEquals("Element1", dataSource.getVisibleData(1));
    }

    @Test
    public void testGetViewType() throws Exception {
        dataSource.addAll(list);

        assertEquals(ViewTypeConstants.VIEW_TYPE_DEFAULT, dataSource.getViewType(0));
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        dataSource.add("custom");
        assertEquals("custom", dataSource.getVisibleData(0));
    }

    @Test
    public void testAddToLocation() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        dataSource.addAll(list);
        dataSource.add(2, "custom");

        assertEquals(11, dataSource.getVisibleDataCount());
        assertEquals("Element0", dataSource.getVisibleData(0));
        assertEquals("Element1", dataSource.getVisibleData(1));
        assertEquals("custom", dataSource.getVisibleData(2));
        assertEquals("Element2", dataSource.getVisibleData(3));
    }

    @Test
    public void testAddAll() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        dataSource.addAll(list);
        assertEquals(list.size(), dataSource.getVisibleDataCount());
        assertEquals("Element0", dataSource.getVisibleData(0));
    }

    @Test
    public void testAddAllAfterLocation() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        dataSource.add("custom0");

        dataSource.addAll(1, list);
        assertEquals(11, dataSource.getVisibleDataCount());
        assertEquals("custom0", dataSource.getVisibleData(0));
        assertEquals("Element0", dataSource.getVisibleData(1));
    }

    @Test
    public void testClear() throws Exception {
        assertEquals(0, dataSource.getVisibleDataCount());
        dataSource.add("toBeDeleted");
        assertEquals(1, dataSource.getVisibleDataCount());
        dataSource.clear();
        assertEquals(0, dataSource.getVisibleDataCount());
    }

    @Test
    public void testContains() throws Exception {
        dataSource.add("findme");
        assertTrue(dataSource.contains("findme"));
    }

    @Test
    public void testContainsAll() throws Exception {
        dataSource.add("findme1");
        dataSource.add("findme2");

        Collection<String> notContains = new ArrayList<>();
        notContains.add("123");

        assertFalse(dataSource.containsAll(notContains));

        Collection<String> contains = new ArrayList<>();
        contains.add("findme1");
        contains.add("findme2");
        assertTrue(dataSource.containsAll(contains));
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(dataSource.equals(dataSource));

        assertFalse(dataSource.equals(list));
        assertFalse(dataSource.equals(null));
        assertFalse(dataSource.equals("test"));
    }

    @Test
    public void testGet() throws Exception {
        dataSource.addAll(list);

        assertEquals("Element0", dataSource.get(0));

        assertEquals("Element1", dataSource.get(1));
    }

    @Test
    public void testIndexOf() throws Exception {
        dataSource.addAll(list);

        assertEquals(0, dataSource.indexOf("Element0"));
        assertEquals(3, dataSource.indexOf("Element3"));
        assertEquals(5, dataSource.indexOf("Element5"));
        assertEquals(9, dataSource.indexOf("Element9"));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(dataSource.isEmpty());

        dataSource.addAll(list);

        assertFalse(dataSource.isEmpty());
    }

    @Test
    public void testIterator() throws Exception {
        dataSource.addAll(list);

        Iterator<String> iterator = dataSource.iterator();
        assertNotNull(iterator);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        dataSource.addAll(list);

        assertEquals(0, dataSource.lastIndexOf("Element0"));
        assertEquals(3, dataSource.lastIndexOf("Element3"));
        assertEquals(5, dataSource.lastIndexOf("Element5"));
        assertEquals(9, dataSource.lastIndexOf("Element9"));

        dataSource.add("Element0");
        assertEquals(10, dataSource.lastIndexOf("Element0"));
    }

    @Test
    public void testListIterator() throws Exception {
        dataSource.addAll(list);

        ListIterator<String> iterator = dataSource.listIterator();
        assertFalse(iterator.hasPrevious());
        assertNotNull(iterator);
    }

    @Test
    public void testListIteratorAtPosition() throws Exception {
        dataSource.addAll(list);

        ListIterator<String> iterator = dataSource.listIterator(1);
        assertNotNull(iterator);
        assertTrue(iterator.hasPrevious());
        iterator.previous();
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testRemove() throws Exception {
        dataSource.addAll(list);

        dataSource.remove("Element0");
        assertEquals("Element1", dataSource.getVisibleData(0));
        assertEquals(9, dataSource.getVisibleDataCount());
    }

    @Test
    public void testRemove1() throws Exception {
        dataSource.addAll(list);

        dataSource.remove(0);
        assertEquals("Element1", dataSource.getVisibleData(0));
        assertEquals(9, dataSource.getVisibleDataCount());
    }

    @Test
    public void testRemoveAll() throws Exception {
        dataSource.addAll(list);

        ArrayList<String> removeAll = new ArrayList<>();
        removeAll.add("notInList");
        dataSource.removeAll(removeAll);
        assertEquals(10, dataSource.getVisibleDataCount());

        dataSource.removeAll(list);
        assertEquals(0, dataSource.getVisibleDataCount());
    }

    @Test
    public void testRetainAll() throws Exception {
        dataSource.addAll(list);

        dataSource.retainAll(list);
        assertEquals(10, dataSource.getVisibleDataCount());

        ArrayList<String> retain = new ArrayList<>();
        retain.add("Element0");
        dataSource.retainAll(retain);
    }

    @Test
    public void testSet() throws Exception {
        dataSource.addAll(list);

        assertEquals(10, dataSource.getVisibleDataCount());
        dataSource.set(0, "custom0");
        assertEquals(10, dataSource.getVisibleDataCount());
        assertEquals("custom0", dataSource.getVisibleData(0));
    }

    @Test
    public void testSize() throws Exception {
        dataSource.addAll(list);

        assertEquals(10, dataSource.size());
    }

    @Test
    public void testSubList() throws Exception {
        dataSource.addAll(list);

        List<String> strings = dataSource.subList(2, 4);
        assertEquals(2, strings.size());
        assertEquals("Element2", strings.get(0));
        assertEquals("Element3", strings.get(1));
    }

    @Test
    public void testToArray() throws Exception {
        String[] original = list.toArray(new String[list.size()]);

        dataSource.addAll(list);

        Object[] strings = dataSource.toArray();

        assertEquals(original.length, strings.length);
        assertEquals(original[0], strings[0]);
        assertEquals(original[3], strings[3]);
    }

    @Test
    public void testToArrayParameterized() throws Exception {
        String[] original = list.toArray(new String[list.size()]);

        dataSource.addAll(list);

        Object[] strings = dataSource.toArray(new String[dataSource.getVisibleDataCount()]);

        assertEquals(original.length, strings.length);
        assertEquals(original[0], strings[0]);
        assertEquals(original[3], strings[3]);
    }
}
