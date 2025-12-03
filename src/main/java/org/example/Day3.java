package org.example;

import java.io.IOException;

import static java.lang.Character.getNumericValue;

public class Day3 {
    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        try (var reader = Util.getInput("/day3.txt")) {
            String line;
            int sum = 0;
            while ((line = reader.readLine()) != null) {
                int max = 0, firstMaxIndex = 0;
                var characters = line.toCharArray();
                for (int i = 0; i < characters.length - 1; i++) {
                    if (getNumericValue(characters[i]) > max) {
                        firstMaxIndex = i;
                        max = getNumericValue(characters[i]);
                    }
                }
                int secondDigit = 0;
                for (int i = firstMaxIndex + 1; i < characters.length; i++) {
                    int numericValue = getNumericValue(characters[i]);
                    if (numericValue > secondDigit) {
                        secondDigit = numericValue;
                    }
                }
                sum += max * 10 + secondDigit;
            }
            System.out.println("Part 1: " + sum);
        }
    }

    private static void part2() throws IOException {
        try (var reader = Util.getInput("/day3.txt")) {
            String line;
            long sum = 0;
            while ((line = reader.readLine()) != null) {
                int max = 0, maxIndex = 0;
                for (int d = 11; d >= 0; d--) {
                    var characters = line.toCharArray();
                    for (int i = maxIndex; i < characters.length - d; i++) {
                        if (getNumericValue(characters[i]) > max) {
                            maxIndex = i;
                            max = getNumericValue(characters[i]);
                        }
                    }
                    sum += (long) (Math.pow(10, d) * max);
                    maxIndex++;
                    max = 0;
                }
            }
            System.out.println("Part 2: " + sum);
        }
    }

}
