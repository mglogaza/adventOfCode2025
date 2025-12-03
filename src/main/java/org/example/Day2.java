package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.LongPredicate;

public class Day2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Part 1 result: " + task(Day2::isCorrupted1));
        System.out.println("Part 2 result: " + task(Day2::isCorrupted2));
    }

    private static long task(LongPredicate isCorruptedPredicate) throws IOException {
        long corrupted = 0;
        try (var reader = getInput("/day2input.txt")) {
            var input = Arrays.stream(reader.readLine().split(","))
                    .map(range -> range.split("-"))
                    .map(Range::new)
                    .toList();
            for (var range : input) {
                for (long i = range.from; i <= range.to; i++) {
                    if (isCorruptedPredicate.test(i)) {
                        corrupted += i;
                    }
                }
            }
        }
        return corrupted;
    }

    public static boolean isCorrupted1(long i) {
        var string = Long.toString(i);
        if (string.length() % 2 == 0) {
            int halfLength = string.length() / 2;
            for (int j = 0; j < halfLength; j++) {
                if (string.charAt(j) != string.charAt(j + halfLength)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isCorrupted2(long l) {
        var string = Long.toString(l);
        var trimmed = (string + string).substring(1, string.length()*2 - 1);
        return trimmed.contains(string);
    }


    record Range(long from, long to) {
        public Range(String[] parsed) {
            this(Long.parseLong(parsed[0]), Long.parseLong(parsed[1]));
        }
    }

    private static BufferedReader getInput(String name) {
        return new BufferedReader(new InputStreamReader(Day2.class.getResourceAsStream(name)));
    }
}
