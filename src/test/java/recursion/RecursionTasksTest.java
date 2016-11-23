package recursion;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RecursionTasksTest {
    @Test
    public void findMax() throws Exception {
        final List<Integer> list = Arrays.asList(7, 12, 3, 5, 2);
        assertThat(RecursionTasks.findMax(list), is(12));

    }

    @Test
    public void numDecimals() throws Exception {
        assertThat(RecursionTasks.numDecimals(0), is(1));
        assertThat(RecursionTasks.numDecimals(7), is(1));
        assertThat(RecursionTasks.numDecimals(-73), is(2));
    }
}