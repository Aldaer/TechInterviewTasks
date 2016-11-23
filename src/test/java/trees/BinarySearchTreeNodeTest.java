package trees;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class BinarySearchTreeNodeTest {
    private final Map<Character, BinarySearchTreeNode<Integer>> nodes = new HashMap<>();
    private BinarySearchTreeNode<Integer> root;

    @Before
    public void setUp() throws Exception {
        for (Character c = 'A'; c <= 'F'; c++) {
            nodes.put(c, new BinarySearchTreeNode<>(0));
        }

        root = nodes.get('A');
        nodes.get('A').left = nodes.get('B');
        nodes.get('B').left = nodes.get('C');
        nodes.get('B').right = nodes.get('D');
        nodes.get('A').right = nodes.get('E');
        nodes.get('E').right = nodes.get('F');

        nodes.get('A').value = 7;
        nodes.get('B').value = 3;
        nodes.get('C').value = 2;
        nodes.get('D').value = 5;
        nodes.get('E').value = 9;
        nodes.get('F').value = 11;
    }

    @Test
    public void isValidBST() throws Exception {
        assertTrue(root.isValidBST());

        nodes.get('E').left = new BinarySearchTreeNode<>(6);
        assertFalse(root.isValidBST());

        nodes.get('E').left.value = 8;
        assertTrue(root.isValidBST());
    }

}