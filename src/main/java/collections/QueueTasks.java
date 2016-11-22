package collections;

import java.util.Queue;
import java.util.Stack;

public class QueueTasks {
    static <T extends Comparable<T>> T min(Queue<T> queue) {
        T element = queue.poll();
        T min = element;
        while (element != null) {
            if (element.compareTo(min) < 0) min = element;
            element = queue.poll();
        }
        return min;
    }
    static <T extends Comparable<T>> T max(Queue<T> queue) {
        T element = queue.poll();
        T max = element;
        while (element != null) {
            if (element.compareTo(max) > 0) max = element;
            element = queue.poll();
        }
        return max;
    }

    static class TwoStackQueue<T> {
        final Stack<T> addStack = new Stack<>();
        final Stack<T> removeStack = new Stack<>();
        boolean addMode = true;

        private void moveStack(Stack<T> from, Stack<T> to) {
            while(! from.empty())
                to.push(from.pop());
        }

        void add(T element) {
            if (! addMode) {
                moveStack(removeStack, addStack);
                addMode = true;
            }
            addStack.push(element);
        }

        T remove() {
            if (addMode) {
                moveStack(addStack, removeStack);
                addMode = false;
            }
            return removeStack.pop();
        }

        boolean empty() {
            return addStack.empty() && removeStack.empty();
        }
    }

}
