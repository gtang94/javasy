package io.github.gtang94.finejar.algorithm;

import java.time.LocalDate;

public class SM2Test {

    public static void main(String[] args) {

        SM2 nr = SM2.first(2, LocalDate.of(2024, 1, 18));
        System.out.println(nr.toString());

        nr.next(2, null);
        System.out.println(nr.toString());

        nr.next(2, null);
        System.out.println(nr.toString());

        nr = nr.next(3, LocalDate.of(2024, 1, 19));
        System.out.println(nr.toString());

        nr = nr.next(3, LocalDate.of(2024, 1, 21));
        System.out.println(nr.toString());

        nr = nr.next(2, LocalDate.of(2024, 1, 21));
        System.out.println(nr.toString());

        nr = nr.next(3, LocalDate.of(2024, 1, 21));
        System.out.println(nr.toString());

        nr = nr.next(3, LocalDate.of(2024, 1, 19));
        System.out.println(nr.toString());

        nr = nr.next(3, LocalDate.of(2024, 1, 27));
        System.out.println(nr.toString());

        nr = nr.next(2, LocalDate.of(2024, 2, 4));
        System.out.println(nr.toString());

        nr = nr.next(5, LocalDate.of(2024, 2, 5));
        System.out.println(nr.toString());

        nr = nr.next(5, LocalDate.of(2024, 2, 6));
        System.out.println(nr.toString());

        nr = nr.next(5, LocalDate.of(2024, 2, 12));
        System.out.println(nr.toString());
    }
}
