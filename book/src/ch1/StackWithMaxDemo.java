package ch1;

import edu.princeton.cs.algs4.Stack;

public class StackWithMaxDemo {
    static Stack<Integer> mainStack = new Stack<>();
    static Stack<Integer> trackStack = new Stack<>();

    static void push(int x) {
        mainStack.push(x);
        if (mainStack.size() == 1) {
            trackStack.push(x);
            return;
        }
        if (x > trackStack.peek()) {
            trackStack.push(x);
        } else {
            trackStack.push(trackStack.peek());
        }
    }

    static int getMax() {
        return trackStack.peek();
    }

    static void pop() {
        mainStack.pop();
        trackStack.pop();
    }

    public static void main(String[] args) {
        push(4);
        push(19);
        push(7);
        push(14);
        push(20);
        push(5);
        System.out.println(getMax());
    }
}
