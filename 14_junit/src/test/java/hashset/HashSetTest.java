package hashset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashSetTest {

    private Set<String> stringSet;

    @Before
    public void setUp() {
        stringSet = new HashSet<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {
        stringSet = new HashSet<>(-1);
    }

    @Test(expected = NullPointerException.class)
    public void testPassingNullCollection() {
        stringSet = new HashSet<>(null);
    }

    @Test
    public void testAdd() {
        Assert.assertTrue("Expected to add Maks", stringSet.add("Maks"));
        Assert.assertTrue("Expected to add Dima", stringSet.add("Dima"));
        Assert.assertTrue("Expected to add Tanya", stringSet.add("Tanya"));
        Assert.assertFalse("Expected to get false by adding dublicate element", stringSet.add("Tanya"));
        Assert.assertEquals("Expected that size of the set equals 3", 3, stringSet.size());
    }

    @Test
    public void testClear() {
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        stringSet.clear();
        Assert.assertEquals("Expected that size of the set equals 0", 0, stringSet.size());
    }

    @Test
    public void testContains() {
        Assert.assertFalse("Expected to get false with contains() method", stringSet.contains("Maks"));
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        Assert.assertTrue("Expected to get true with contains() method", stringSet.contains("Maks"));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue("Expected to get true with isEmpty() method", stringSet.isEmpty());
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        Assert.assertFalse("Expected to get false with isEmpty() method", stringSet.isEmpty());
    }

    @Test
    public void testIterator() {
        Assert.assertFalse("Expected to get false with hasNext() method in the null set", stringSet.iterator().hasNext());
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        Assert.assertTrue("Expected to get false with hasNext() method in the null set", stringSet.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorException() {
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");

        Iterator<String> it = stringSet.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void testRemove() {
        Assert.assertFalse("Expected to get false with remove() method in the null set", stringSet.remove("Maks"));
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        Assert.assertFalse("Expected to get false with remove() with the non-existing object", stringSet.remove("John"));
        Assert.assertTrue("Expected to get true with remove() with the existing object", stringSet.remove("Maks"));
        Assert.assertEquals("Expected to size equals 2 after removing", 2, stringSet.size());
    }

    @Test
    public void testSize() {
        Assert.assertEquals("Expected to size equals 0 of the empty set", 0, stringSet.size());
        stringSet.add("Maks");
        stringSet.add("Dima");
        stringSet.add("Tanya");
        Assert.assertEquals("Expected to size equals 3 after adding 3 elements", 3, stringSet.size());
        stringSet.remove("Maks");
        Assert.assertEquals("Expected to size equals 2 after removing 1 element", 2, stringSet.size());
    }
}