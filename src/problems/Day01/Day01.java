package problems.Day01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day01 {
    public static void main(String[] args) throws IOException {
        System.out.println("AoC2023 - Day 01");

        attempt1();
    }

    // --- Solution 1 ---
    private static void attempt1() throws IOException {
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

    // --- Helper functions ---
    private static String getFirstAndLastDigit(String input) {
        return "" + input.charAt(0) + input.charAt(input.length()-1);
    }

}