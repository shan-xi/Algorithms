package practice;

import java.util.Stack;
import java.util.function.BiFunction;

public class BinaryAdditionDemo {


    static void wrapper(BiFunction<String, String, String> ba, String a, String b) {
        double avg = 0;
        int count = 0;
        while (count < 10000000) {
            final long startTime = System.nanoTime();
            String r = ba.apply(a, b);
//            System.out.println(r);
            final long duration = System.nanoTime() - startTime;
//            System.out.println(duration);
            avg += duration;
            count++;
        }
        System.out.println(avg / count);
    }

    static class BinaryAddition implements BiFunction<String, String, String> {

        @Override
        public String apply(String a, String b) {
            if (a == null || a.equals("") || b == null || b.equals("")) {
                throw new IllegalArgumentException();
            }
            int intA = binaryStrToDecimal(a);
            int intB = binaryStrToDecimal(b);
            return decimalToBinaryString(intA + intB);
        }

        int binaryStrToDecimal(String s) {
            int sum = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                sum += (s.charAt(i) - 48) * Math.pow(2, s.length() - i - 1);
            }
            return sum;
        }

        String decimalToBinaryString(int i) {
            StringBuilder sb = new StringBuilder();
            Stack<String> stack = new Stack<>();
            while (i > 1) {
                stack.add(String.valueOf(i % 2));
                i /= 2;
            }
            stack.add(String.valueOf(i));
            while (!stack.empty()) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }
    }

    static class BinaryAddition2 implements BiFunction<String, String, String> {


        @Override
        public String apply(String b1, String b2) {
            int len1 = b1.length();
            int len2 = b2.length();
            int carry = 0;
            String res = "";
            // the final length of the result depends on the bigger length between b1 // and b, // (also the value of carry, if carry = 1, add "1" at the head of result, // otherwise)
            int maxLen = Math.max(len1, len2);
            for (int i = 0; i < maxLen; i++) { // start from last char of String b1 and b2 // notice that left side is an int and right side is char
                // so we need to minus the decimal value of '0'
                int p = i < len1 ? b1.charAt(len1 - 1 - i) - '0' : 0;
                int q = i < len2 ? b2.charAt(len2 - 1 - i) - '0' : 0;
                int tmp = p + q + carry;
                carry = tmp / 2;
                res = tmp % 2 + res;
            }
            return (carry == 0) ? res : "1" + res;

        }
    }

    static class BinaryAddition3 implements BiFunction<String, String, String> {

        @Override
        public String apply(String s1, String s2) {
            int b1 = Integer.parseInt(s1, 2);
            int b2 = Integer.parseInt(s2, 2);
            int sum = b1 + b2;
            return Integer.toBinaryString(sum);
        }
    }

    public static void main(String[] args) {
        String a = "1101";
        String b = "010";
        var b1 = new BinaryAddition();
        wrapper(b1, a, b);
        var b2 = new BinaryAddition2();
        wrapper(b2, a, b);
        var b3 = new BinaryAddition3();
        wrapper(b3, a, b);
        a = "00110101010010111010010101101";
        b = "11010010110101001110010101001";
        b1 = new BinaryAddition();
        wrapper(b1, a, b);
        b2 = new BinaryAddition2();
        wrapper(b2, a, b);
        b3 = new BinaryAddition3();
        wrapper(b3, a, b);
//        System.out.println(binaryAdd(a, b));
//        a = "1010";
//        b = "11";
//        System.out.println(binaryAdd(a, b));
    }
}
