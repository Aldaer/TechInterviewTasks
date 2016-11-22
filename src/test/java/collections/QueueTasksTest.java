package collections;

import collections.QueueTasks.TwoStackQueue;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static collections.QueueTasks.max;
import static collections.QueueTasks.min;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueTasksTest {
    @Test
    public void minAndMax() throws Exception {
        final List<Integer> intList = Arrays.asList(1, 12, 7, -3, 8);
        Queue<Integer> testQ = new ArrayDeque<>(intList);
        assertThat(min(testQ), is(-3));
        assertNull(min(testQ));

        testQ = new ArrayDeque<>(intList);
        assertThat(max(testQ), is(12));
    }

    @Test
    public void twoStackQueue() throws Exception {
        final TwoStackQueue<Integer> q = new TwoStackQueue<>();
        assertTrue(q.empty());

        q.add(10);
        q.add(5);
        assertEquals((int) q.remove(), 10);
        assertFalse(q.empty());

        q.add(12);
        q.add(17);
        assertEquals((int) q.remove(), 5);
        assertEquals((int) q.remove(), 12);
        assertEquals((int) q.remove(), 17);
        assertTrue(q.empty());
    }
}