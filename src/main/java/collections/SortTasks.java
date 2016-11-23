package collections;

import java.util.*;

class SortTasks {

    private static <T extends Comparable<T>> List<T> merge(List<T> l1, List<T> l2) {
        final List<T> result = new ArrayList<>(l1.size() + l2.size());

        int i1 = 0;
        int i2 = 0;
        while (i1 < l1.size() && i2 < l2.size())
            if (l1.get(i1).compareTo(l2.get(i2)) > 0)
                result.add(l2.get(i2++));
            else
                result.add(l1.get(i1++));
        result.addAll(l1.subList(i1, l1.size()));
        result.addAll(l2.subList(i2, l2.size()));
        return result;
    }

    static <T extends Comparable<T>> List<T> mergeSort(List<T> input) {
        if (input.size() <= 1) return input;
        int split = input.size() / 2;
        final List<T> left = mergeSort(input.subList(0, split));
        final List<T> right = mergeSort(input.subList(split, input.size()));
        return merge(left, right);
    }

    /**
     * Use with RandomAccess-implementing List for optimum performance
     */
    static <T extends Comparable<T>> List<T> bubbleSort(List<T> input) {
        if (input.size() <= 1) return input;
        T bubble = input.get(0);
        int lastChange = 0;
        for (int i = 1; i < input.size(); i++) {
            T ith = input.get(i);
            if (bubble.compareTo(ith) > 0) {
                input.set(i - 1, ith);
                input.set(i, bubble);
                lastChange = i;
            } else {
                bubble = ith;
            }
        }
        bubbleSort(input.subList(0, lastChange));
        return input;
    }
}
