package collections;

import java.util.ArrayList;
import java.util.List;

class SortTasks {

    private static <T extends Comparable<T>> List<T> merge(List<T> list1, List<T> list2) {
        final List<T> result = new ArrayList<>(list1.size() + list2.size());

        int i1 = 0;
        int i2 = 0;
        while (i1 < list1.size() && i2 < list2.size())
            if (list1.get(i1).compareTo(list2.get(i2)) > 0)
                result.add(list2.get(i2++));
            else
                result.add(list1.get(i1++));

        result.addAll(list1.subList(i1, list1.size()));
        result.addAll(list2.subList(i2, list2.size()));
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
