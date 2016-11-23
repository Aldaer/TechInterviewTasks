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

    int height() {
        return max(getSubtreeHeight(left), getSubtreeHeight(right)) + 1;
    }

    boolean isBalanced() {
        return isSubtreeBalanced(this);
    }

    private static int getSubtreeHeight(BinaryTreeNode n) {
        return n == null ? 0 : n.height();
    }

    private static boolean isSubtreeBalanced(BinaryTreeNode n) {
        return n == null ||
                isSubtreeBalanced(n.left) && isSubtreeBalanced(n.right) &&
                        abs(getSubtreeHeight(n.left) - getSubtreeHeight(n.right)) <= 1;
    }

}
