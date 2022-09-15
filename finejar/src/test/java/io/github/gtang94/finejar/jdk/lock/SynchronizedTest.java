package io.github.gtang94.finejar.jdk.lock;

public class SynchronizedTest {

    public static void main(String[] args) {
        short s = 1;
        s += 1;
        System.err.println(s);
    }
}
