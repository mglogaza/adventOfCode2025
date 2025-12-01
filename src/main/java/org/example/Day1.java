package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1 {
    public static void main(String[] args) throws IOException {
        part1();
    }

    private static void part1() throws IOException {
        int position = 50, password = 0;
        try (var reader = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream("/day1input1.txt")))) {
            String line = reader.readLine();
            while (line != null) {
                int direction = line.charAt(0) == 'R' ? 1 : -1;
                int previousPosition = position;
                position += Integer.parseInt(line.substring(1)) * direction;
                if (Integer.signum(previousPosition) != Integer.signum(position) && position != 0 && previousPosition != 0) {
                    password++;
                }
                if (position == 0) {
                    password++;
                }
                password += Math.abs(position / 100);
                position = position % 100;
                if (position < 0) {
                    position += 100;
                }
                line = reader.readLine();
            }
            System.out.println("Secret password: " + password);
        }
    }
}
