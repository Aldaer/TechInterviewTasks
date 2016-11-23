package trees;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    private final Map<Character, BinaryTreeNode<Character>> nodes = new HashMap<>();

    private final BinaryTree<Character> tree = new BinaryTree<>();

    @Before
    public void setUp() throws Exception {
        for (Character c = 'A'; c <= 'F'; c++) {
            nodes.put(c, new BinaryTreeNode<>(c));
        }

        nodes.get('A').left = nodes.get('B');
        nodes.get('B').left = nodes.get('C');
        nodes.get('B').right = nodes.get('D');
        nodes.get('A').right = nodes.get('E');
        nodes.get('E').right = nodes.get('F');

        tree.root = nodes.get('A');
    }

    @Test
    public void height() throws Exception {
        assertThat(tree.height(), is(3));
    }

    @Test
    public void isBalanced() throws Exception {
        assertTrue(tree.isBalanced());

        nodes.get('F').left = new BinaryTreeNode<>('G');
        assertFalse(tree.isBalanced());
    }


}