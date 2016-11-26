package collections;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MyHashMapTest {
    private Map<String, Integer> test = new MapsTasks.MyHashMap<>(20);

    private Map<String, Integer> expected = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        test.put("one", 1);
        test.put("two", 2);
        test.put("three", 3);

        expected.put("one", 1);
        expected.put("two", 2);
        expected.put("three", 3);
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

    @Test
    public void entrySet() throws Exception {
        final Set<Map.Entry<String, Integer>> entries = test.entrySet();
        assertThat(entries.size(), is(3));
        Map<String, Integer> receptacle = new HashMap<>();
        entries.forEach(e -> receptacle.put(e.getKey(), e.getValue()));
        assertThat(receptacle, is(expected));
    }

    @Test
    public void keySet() throws Exception {
        for (String next : test.keySet()) {
            assertThat(expected.remove(next), is(test.get(next)));
        }
        assertThat(expected.size(), is(0));
    }

    @Test
    public void values() throws Exception {
        final Collection<Integer> values = test.values();
        assertTrue(values.contains(1));
        assertFalse(values.contains(4));
        test.put("four", 4);
        assertTrue(values.contains(4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeNotSupported() throws Exception {
        test.keySet().iterator().remove();
    }
}