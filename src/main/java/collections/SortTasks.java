package collections;

import java.util.ArrayList;
import java.util.Arrays;
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

class HeapSorter<T extends Comparable<T>>  {
    private final T[] a;
    private int heapSize;

    private HeapSorter(T[] a) {
        this.a = a;
        heapSize = a.length;
    }

    private static int parent(int pos) {
        return (pos + 1) / 2 - 1;
    }

    private static int left(int pos) {
        return (pos + 1) * 2 - 1;
    }

    private static int right(int pos) {
        return (pos + 1) * 2;
    }

    private void bubbleUp(int pos) {
        int parent = parent(pos);
        if (a[pos].compareTo(a[parent]) <= 0) return;
        swap(pos, parent);
        if (parent > 0) bubbleUp(parent);
    }

    private void sinkDown(int pos) {
        int left = left(pos);
        if (left >= heapSize) return;

        int right = left + 1;
        int maxIndex;
        if (right < heapSize && a[left].compareTo(a[right]) < 0)
            maxIndex = right;
        else
            maxIndex = left;

        if (a[pos].compareTo(a[maxIndex]) >= 0) return;

        swap(pos, maxIndex);
        sinkDown(maxIndex);
    }

    private void swap(int i, int j) {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    private void sort() {
        // Heapify: O(n ln n)
        for (int i = 1; i < heapSize; i++)
            bubbleUp(i);

        // Swap root, sink down new root to reinstate heap: O(n ln n) 
        while (heapSize > 1) {
            heapSize--;
            swap(0, heapSize);
            sinkDown(0);
        }
    }

    static <T extends Comparable<T>> List<T> sort(List<T> input) {
        @SuppressWarnings("unchecked")
        T[] sorter = (T[]) input.toArray();
        new HeapSorter<T>(sorter).sort();
        return Arrays.asList(sorter);
    }


}
