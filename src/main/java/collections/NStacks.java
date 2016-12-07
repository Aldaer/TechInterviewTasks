package collections;

import java.util.EmptyStackException;

/**
 * Implementation of N stacks using single array.
 * No data relocation, constant-time push() and pop() operations.
 * Guaranteed worst-case capacity before overflowing is (size/N) * 2.
 * Maximum capacity of each stack is (size/N) * 2.
 */
public class NStacks<T> {
    private final int N;
    private final T[] a;
    private final int[] index;
    private final int slice;

    /**
     * Creates a new NStacks instance. IllegalArgumentException is thrown on {@code N <= 0} or {@code arraySize < N}.
     * @param arraySize size of the array to create. Will be rounded down to nearest multiple of N.
     * @param N         number of stacks to implement
     */
    public NStacks(int arraySize, int N) {
        if (N <= 0 || arraySize < N) throw new IllegalArgumentException();
        this.N = N;
        slice = arraySize / N;
        //noinspection unchecked
        a = (T[]) new Object[slice * N];
        index = new int[N];
    }

    private int globalIndex(int i, int stackNo) {
        assert i < slice * 2;
        if (i < slice) return i + stackNo * slice;
        return ((stackNo + 1) * slice) % a.length + 2 * slice - i - 1;
    }

    /**
     * @param k stack number to normalize
     * @return stack number normalized to within [0, N)
     */
    private int stackNo(int k) {
        return (k + N) % N;
    }

    private int roomInSlice(int stackNo) {
        int room = slice - index[stackNo];
        return room > 0 ? room : 0;
    }

    /**
     * @param stackNo Number of stack to query
     * @return Number of elements that can be stored in the stack assuming that the other stacks do not change
     */
    public int freeSpace(int stackNo) {
        final int size = index[stackNo];
        final int prevStack = stackNo(stackNo - 1);
        final int nextStack = stackNo(stackNo + 1);
        if (index[prevStack] > slice) return 2 * slice - index[prevStack] - size;

        return roomInSlice(stackNo) + roomInSlice(nextStack);
    }

    public int size(int stackNo) {
        return index[stackNo];
    }

    /**
     * Pushes item into a stack
     * @param item    Item to push
     * @param stackNo Number of stack to use
     * @return true is successful, false if no space in stack
     */
    public boolean push(T item, int stackNo) {
        if (freeSpace(stackNo) == 0) return false;
        int current = index[stackNo]++;
        a[globalIndex(current, stackNo)] = item;
        return true;
    }

    /**
     * Pops item from a stack. Throws EmptyStackException on empty stack.
     * @param stackNo Number of stack to use
     * @return popped item.
     */
    public T pop(int stackNo) {
        if (size(stackNo) < 1) throw new EmptyStackException();

        final int current = --index[stackNo];
        final int globalIndex = globalIndex(current, stackNo);
        T popped = a[globalIndex];
        a[globalIndex] = null;
        return popped;
    }
}
