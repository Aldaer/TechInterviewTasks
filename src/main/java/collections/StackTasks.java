package collections;

import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.EmptyStackException;
import java.util.OptionalInt;
import java.util.Stack;
import java.util.stream.IntStream;

class StackTasks {
    static String stackReverse(CharSequence input) {
        IntStack cStack = new IntStack(input.length());
        input.chars()
                .forEachOrdered(cStack::push);

        StringBuilder result = new StringBuilder(input.length());
        IntStream.generate(cStack::pop)
                .limit(input.length())
                .forEachOrdered(c -> result.append((char) c));
        return result.toString();
    }

    private static char opening(char bracket) {
        if (bracket == ']') return '[';
        if (bracket == ')') return '(';
        if (bracket == '}') return '{';
        return 0;
    }

    static boolean checkBalanced(CharSequence input) {
        IntStack cStack = new IntStack(input.length());

        final OptionalInt firstNonMatching = input.chars().filter(c -> {
            switch (c) {
                case ']':
                case ')':
                case '}':
                    return (cStack.empty() || opening((char) c) != cStack.pop());
                case '[':
                case '(':
                case '{':
                    cStack.push(c);
                default:
                    return false;
            }
        }).findFirst();

        return ! firstNonMatching.isPresent() && cStack.empty();
    }
}
