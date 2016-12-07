package collections;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NStacksTest {
    private final NStacks<String> stacks = new NStacks<>(9, 3);

    @Test
    public void freeSpace() throws Exception {
        assertThat(stacks.freeSpace(0), is(6));
        stacks.push("a", 0);
        assertThat(stacks.freeSpace(0), is(5));
        stacks.push("b", 1);
        assertThat(stacks.freeSpace(0), is(4));
        for (int i = 0; i < 3; i++)
            stacks.push("c", 2);
        assertThat(stacks.freeSpace(0), is(4));
        stacks.push("c", 2);
        assertThat(stacks.freeSpace(0), is(1));
    }

    @Test
    public void size() throws Exception {
        assertThat(stacks.size(1), is(0));
        stacks.push("b", 1);
        assertThat(stacks.size(1), is(1));
        stacks.pop(1);
        assertThat(stacks.size(1), is(0));
    }

    @Test
    public void pushpop() throws Exception {
        stacks.push("a1", 0);
        stacks.push("b1", 1);
        stacks.push("c1", 2);
        stacks.push("b2", 1);
        stacks.push("c2", 2);
        stacks.push("a2", 0);
        stacks.push("a3", 0);
        stacks.push("a4", 0);

        assertThat(stacks.pop(1), is("b2"));
        assertThat(stacks.pop(1), is("b1"));
        assertThat(stacks.pop(2), is("c2"));
        assertThat(stacks.pop(0), is("a4"));
        assertThat(stacks.pop(2), is("c1"));
    }
}