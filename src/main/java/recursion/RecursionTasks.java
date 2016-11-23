package recursion;

import java.util.List;

import static java.lang.Math.abs;

class RecursionTasks {
    private static Integer findMaxByRecursion(List<Integer> list) {
        final Integer firstInt = list.get(0);
        if (list.size() == 1) return firstInt;
        return Math.max(firstInt, findMaxByRecursion(list.subList(1, list.size())));
    }

    static Integer findMax(List<Integer> list) {
        if (list == null || list.size() < 1) return null;

        return findMaxByRecursion(list);
    }

    static int numDecimals(int n) {
        return (abs(n) < 10) ? 1 : 1 + numDecimals(n / 10);
    }
}
