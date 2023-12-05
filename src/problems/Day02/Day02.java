package problems.Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02 {
    public static void main(String[] args) throws IOException {
        System.out.println("AoC2023 - Day 02");

        long start = System.currentTimeMillis();
        /*12 red cubes, 13 green cubes, and 14 blue cubes*/
        //part1();
        part2();
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    // --- Solution 1 ---
    private static void part1() throws IOException {
        System.out.println("Part 1");
        List<Game> games;

        Set actual = new Set(12, 13, 14);

        try (Stream<String> stream = Files.lines(Paths.get("./src/problems/Day02/input.txt"))) {
            int value = stream
                    .map(s -> new Game(s))
                    .filter(game -> isGamePossible(game, actual))
                    .map(game -> game.gameId)
                    .reduce(0, Integer::sum);
            System.out.println("De som van de indices van geldige games is: " + value);
        } catch (IOException e) {
            System.err.println("Problem reading file " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void part2() throws IOException {
        System.out.println("Part 2");

        try (Stream<String> stream = Files.lines(Paths.get("./src/problems/Day02/input.txt"))) {
            int value = stream
                    .map(Game::new)
                    .map(Day02::findSetUnion)
                    .map(Day02::power)
                    .reduce(0, Integer::sum);
            System.out.println("De som van de power van alle minimale sets is: " + value);
        } catch (IOException e) {
            System.err.println("Problem reading file " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- Helper functions ---
    static boolean isGamePossible(Game game, Set actual) {
        return game.sets.stream()
                .noneMatch(set -> isSetImpossible(set, actual));
    }

    static boolean isSetImpossible(Set set, Set actual) {
        return set.red > actual.red
                || set.green > actual.green
                || set.blue > actual.blue;
    }

    static Set union(Set addToUnion, Set collector) {
        collector.red = Math.max(addToUnion.red, collector.red);
        collector.green = Math.max(addToUnion.green, collector.green);
        collector.blue = Math.max(addToUnion.blue, collector.blue);
        return collector;
    }

    static int power(Set set) {
        return set.red * set.green * set.blue;
    }

    static Set findSetUnion(Game game) {
        Set collector = new Set(0,0,0);
        game.sets.stream()
                .map(set -> union(set, collector))
                .collect(Collectors.toList());
        return collector;
    }
}

// --- Helper classes
class Set {
    public int red;
    public int green;
    public int blue;
    Set(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    Set(String setString) {
        Scanner scanner = new Scanner(setString);
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String next = scanner.next().strip();
            if(!("".equals(next))) {
                int amount = Integer.parseInt(next.split(" ")[0]);
                String colour = next.split(" ")[1];

                if ("red".equals(colour)) this.red = amount;
                else if ("green".equals(colour)) this.green = amount;
                else if ("blue".equals(colour)) this.blue = amount;
            }
        }
    }
}

class Game {
    int gameId;
    ArrayList<Set> sets;
    Game() {}

    Game(String gameString) {
        Scanner scanner = new Scanner(gameString);
        scanner.useDelimiter("Game |;|:");

        this.gameId = Integer.parseInt(scanner.next());
        sets = new ArrayList<>();

        while (scanner.hasNext()) {
            Set set = new Set(scanner.next());
            sets.add(set);
        }
    }
}