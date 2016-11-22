import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.EmptyStackException;
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
        return bracket;
    }

    static boolean checkBalanced(CharSequence input) {
        Stack<Character> charStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '[':
                case '(':
                case '{':
                    charStack.push(c);
                    break;
                case ']':
                case ')':
                case '}':
                    if (charStack.empty() || opening(c) != charStack.pop())
                        return false;
            }
        }
        return (charStack.empty());
    }
}
