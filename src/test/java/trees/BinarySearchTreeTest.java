package trees;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BinarySearchTreeTest {

    private final Map<Character, BinarySearchTreeNode<Integer>> nodes = new HashMap<>();
    private BinarySearchTreeNode<Integer> root;

    @Before
    public void setUp() throws Exception {
        for (Character c = 'A'; c <= 'G'; c++) {
            nodes.put(c, new BinarySearchTreeNode<>(0));
        }

        root = nodes.get('A');
        nodes.get('A').left = nodes.get('B');
        nodes.get('B').left = nodes.get('C');
        nodes.get('B').right = nodes.get('D');
        nodes.get('A').right = nodes.get('E');
        nodes.get('E').right = nodes.get('F');
        nodes.get('F').left = nodes.get('G');

        nodes.get('A').value = 7;
        nodes.get('B').value = 3;
        nodes.get('C').value = 2;
        nodes.get('D').value = 5;
        nodes.get('E').value = 9;
        nodes.get('F').value = 11;
        nodes.get('G').value = 10;
    }

    @Test
    public void isValidBST() throws Exception {
        assertTrue(root.isThisAValidBST());

        nodes.get('E').left = new BinarySearchTreeNode<>(6);
        assertFalse(root.isThisAValidBST());

        nodes.get('E').left.value = 8;
        assertTrue(root.isThisAValidBST());
    }

    @Test
    public void traverseNodesLessThan() throws Exception {
        Set<Integer> foundNodes = new HashSet<>();
        root.traverseNodesLessThan(6, foundNodes::add);

        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 5));
        assertThat(foundNodes, is(expected));

        foundNodes.clear();
        root.traverseNodesLessThan(11, foundNodes::add);
        expected.addAll(Arrays.asList(7, 9, 10));
        assertThat(foundNodes, is(expected));

        root.traverseNodesLessThan(11, System.out::println);
    }
}