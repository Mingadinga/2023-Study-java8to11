package me.whiteship.practice.parallelSort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {

        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); // 457375 ns
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 314375 ns
        System.out.println("parallel sorting took " + (System.nanoTime() - start));

    }
}
