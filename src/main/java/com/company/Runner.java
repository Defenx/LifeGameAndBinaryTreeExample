package com.company;

public class Runner {
    public static void main(String[] args) {
        BinaryTr<Integer> integerBinaryTr = new BinaryTr<>();

        integerBinaryTr.add(1);
        integerBinaryTr.add(5);
        integerBinaryTr.add(8);
        integerBinaryTr.add(10);
        integerBinaryTr.add(2);

        integerBinaryTr.traverseLeverOrder();
    }
}
