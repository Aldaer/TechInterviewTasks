package collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SubsetSolverTest {
    @Test
    public void containsSet() throws Exception {
        Set<Integer> bigSet = new HashSet<>(Arrays.asList(-5, -73, 12, 8, 2, 62, 94, 17));  // 62 and 94: hash collision
        Set<Integer> contained = new HashSet<>(Arrays.asList(-73, 8, 2, 62, 17));
        Set<Integer> notContained = new HashSet<>(Arrays.asList(-73, 8, 21, 62, 17));

        final MapsTasks.SubsetSolver solver = new MapsTasks.SubsetSolver(bigSet);

        assertTrue(solver.containsSet(contained));
        assertFalse(solver.containsSet(notContained));
    }

}