package collections;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MyHashMapTest {
    private Map<String, Integer> test = new MapsTasks.MyHashMap<>(20);

    @Before
    public void setUp() throws Exception {
        test.put("one", 1);
        test.put("two", 2);
        test.put("three", 3);
    }

    @Test
    public void size() throws Exception {
        assertThat(test.size(), is(3));

    }

    @Test
    public void containsKey() throws Exception {
        assertTrue(test.containsKey("one"));
        assertFalse(test.containsKey("four"));
    }

    @Test
    public void containsValue() throws Exception {
        assertTrue(test.containsValue(2));
        assertFalse(test.containsValue(4));
    }

    @Test
    public void get() throws Exception {
        assertThat(test.get("three"), is(3));
    }

    @Test
    public void put() throws Exception {
        assertFalse(test.containsKey("four"));
        test.put("four", 4);
        assertThat(test.get("four"), is(4));
        assertThat(test.put("two", 22), is(2));
        assertThat(test.get("two"), is(22));
    }

    @Test
    public void remove() throws Exception {
        assertTrue(test.containsKey("two"));
        Integer two = test.remove("two");
        assertTrue(two == 2);
        assertFalse(test.containsKey("two"));
    }
}