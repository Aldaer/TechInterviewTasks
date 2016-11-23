package trees;

import static java.lang.Math.abs;
import static java.lang.Math.max;

class BinaryTreeNode<T> {
    BinaryTreeNode<T> left = null;
    BinaryTreeNode<T> right = null;
    T value;

    BinaryTreeNode(T value) {
        this.value = value;
    }

    private int height() {
        return max(getSubtreeHeight(left), getSubtreeHeight(right)) + 1;
    }

    static int getSubtreeHeight(BinaryTreeNode n) {
        return n == null ? 0 : n.height();
    }

    static boolean isSubtreeBalanced(BinaryTreeNode n) {
        return n == null ||
                isSubtreeBalanced(n.left) && isSubtreeBalanced(n.right) &&
                        abs(getSubtreeHeight(n.left) - getSubtreeHeight(n.right)) <= 1;
    }
}

class BinaryTree<T> {
    BinaryTreeNode<T> root = null;

    int height() {
        return BinaryTreeNode.getSubtreeHeight(root);
    }

    boolean isBalanced() {
        return root == null || BinaryTreeNode.isSubtreeBalanced(root);
    }
}

