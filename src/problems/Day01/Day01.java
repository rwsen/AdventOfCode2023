package problems.Day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day01 {
    public static void main(String[] args) throws IOException {
        System.out.println("AoC2023 - Day 01");

        long start = System.currentTimeMillis();
        part1();
        //part2();
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    // --- Solution 1 ---
    private static void part1() throws IOException {
        System.out.println("Part 1");
        // Store solution.
        int totalCalibration = 0;

        try (Stream<String> stream = Files.lines(Paths.get("./src/problems/Day01/input.txt"))) {
            totalCalibration = stream
                    .map(s -> s.replaceAll("[^\\d]", ""))
                    .map(s -> getFirstAndLastDigit(s))
                    .map(s -> Integer.parseInt(s))
                    .reduce(0, Integer::sum);
        } catch (IOException e) {
            System.err.println("Problem reading file " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("The result is: " + totalCalibration);
    }

    public static void part2() throws IOException {
        System.out.println("Part 2");
        // Store solution.
        int totalCalibration = 0;

        try (Stream<String> stream = Files.lines(Paths.get("./src/problems/Day01/input.txt"))) {
            totalCalibration = stream
                    .map(s -> replaceDigitNameWithDigit(s))
                    .map(s -> s.replaceAll("[^\\d]", ""))
                    .map(s -> getFirstAndLastDigit(s))
                    .map(s -> Integer.parseInt(s))
                    .reduce(0, Integer::sum);
        } catch (IOException e) {
            System.err.println("Problem reading file " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("The result is: " + totalCalibration);
    }

    // --- Helper functions ---
    private static String getFirstAndLastDigit(String input) {
        return "" + input.charAt(0) + input.charAt(input.length()-1);
    }

    private static String replaceDigitNameWithDigit(String input) {
        //System.out.print("Input string: " + input);
        input = input.replaceAll("one",   "o1e");
        input = input.replaceAll("two",   "t2o");
        input = input.replaceAll("three", "t3e");
        input = input.replaceAll("four",  "f4r");
        input = input.replaceAll("five",  "f5e");
        input = input.replaceAll("six",   "s6x");
        input = input.replaceAll("seven", "s7n");
        input = input.replaceAll("eight", "e8t");
        input = input.replaceAll("nine",  "n9e");
        //System.out.println(" -> " + input);
        return input;
    }

}