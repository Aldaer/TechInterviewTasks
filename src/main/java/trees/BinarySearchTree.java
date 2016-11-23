package trees;

import java.util.Objects;
import java.util.function.BiFunction;

class BinarySearchTreeNode<T extends Comparable<T>>  {
    private final BiFunction<T, T, T> max = (t1, t2) -> t1.compareTo(t2) > 0? t1 : t2;
    private final BiFunction<T, T, T> min = (t1, t2) -> t1.compareTo(t2) < 0? t1 : t2;

    BinarySearchTreeNode<T> left;
    BinarySearchTreeNode<T> right;
    T value;

    public BinarySearchTreeNode(T value) {
        this.value = Objects.requireNonNull(value);
    }

    boolean isValidBST() {
        boolean leftValid = left == null ||
                left.isValidBST() && value.compareTo(left.max()) > 0;
        boolean rightValid = right == null ||
                right.isValidBST() && value.compareTo(right.min()) < 0;
        return leftValid && rightValid;
    }

    T max() {
        T maxT = value;
        if (left != null) maxT = max.apply(maxT, left.max());
        if (right != null) maxT = max.apply(maxT, right.max());
        return maxT;
    }

    T min() {
        T minT = value;
        if (left != null) minT = min.apply(minT, left.min());
        if (right != null) minT = min.apply(minT, right.min());
        return minT;
    }

}
