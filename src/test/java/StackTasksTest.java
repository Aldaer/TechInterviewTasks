import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StackTasksTest {
    @Test
    public void testStackReverse() throws Exception {
        assertThat(StackTasks.stackReverse("test"), is("tset"));
    }

    @Test
    public void testMatchingBrackets() throws Exception {
        assertTrue(StackTasks.checkBalanced("a{dwfwef[wer(wf)]wf}wefw"));
        assertFalse(StackTasks.checkBalanced("a{dwfwef[wer(wf)]wf"));
        assertFalse(StackTasks.checkBalanced("a}b{c"));
        assertFalse(StackTasks.checkBalanced("a(b[c)d]"));
    }
}