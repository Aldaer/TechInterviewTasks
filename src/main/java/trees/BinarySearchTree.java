package trees;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

class BinarySearchTreeNode<T extends Comparable<T>> {
    private final BiFunction<T, T, T> max = (t1, t2) -> t1.compareTo(t2) > 0 ? t1 : t2;
    private final BiFunction<T, T, T> min = (t1, t2) -> t1.compareTo(t2) < 0 ? t1 : t2;

    BinarySearchTreeNode<T> left = null;
    BinarySearchTreeNode<T> right = null;
    T value;

    BinarySearchTreeNode(T value) {
        this.value = Objects.requireNonNull(value);
    }


    /**
     * Can be used to check tree constructed by directly assigning nodes
     */
    boolean isThisAValidBST() {
        boolean leftValid = left == null ||
                left.isThisAValidBST() && value.compareTo(left.max()) > 0;
        boolean rightValid = right == null ||
                right.isThisAValidBST() && value.compareTo(right.min()) < 0;
        return leftValid && rightValid;
    }

    void traverseNodesLessThan(T limit, Consumer<T> traverse) {
        if (left != null) left.traverseNodesLessThan(limit, traverse);
        if (value.compareTo(limit) < 0) {
            traverse.accept(value);
            if (right != null) right.traverseNodesLessThan(limit, traverse);
        }
    }

    private T max() {
        return (right != null) ? right.max() : value;
    }

    private T min() {
        return (left != null) ? left.min() : value;
    }

}
