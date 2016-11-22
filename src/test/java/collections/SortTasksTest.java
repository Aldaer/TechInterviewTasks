package collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class SortTasksTest {
    List<Integer> unsorted = Arrays.asList(3, 1, 8, 21, 0, 10);
    List<Integer> sorted = Arrays.asList(0, 1, 3, 8, 10, 21);


    @Test
    public void mergeSort() throws Exception {
        assertThat(SortTasks.mergeSort(unsorted), is(sorted));
    }

    @Test
    public void bubbleSort() throws Exception {
        assertThat(SortTasks.bubbleSort(unsorted), is(sorted));
    }

}