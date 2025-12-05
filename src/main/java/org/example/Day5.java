package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
    }

    private static void task1() throws IOException {
        try (var reader = Util.getInput("/day4.txt")) {
            int moveablePiles = 0;
            String previousLine = reader.readLine(), line = reader.readLine(), nextLine;
            moveablePiles += getMoveablePiles(null, previousLine, line).piles;
            while ((nextLine = reader.readLine()) != null) {
                moveablePiles += getMoveablePiles(previousLine, line, nextLine).piles;
                previousLine = line;
                line = nextLine;
            }
            moveablePiles += getMoveablePiles(previousLine, line, null).piles;

            System.out.println("Part 1 moveable piles: " + moveablePiles);
        }
    }

    private static void task2() throws IOException {

        try (var reader = Util.getInput("/day4.txt")) {
            List<String> input = new ArrayList<>();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                input.add(inputLine);
            }
            int totalMoveablePiles = 0, moveablePiles;
            do {
                moveablePiles = 0;
                List<String> nextInput = new ArrayList<>();
                String previousLine = input.get(0), line = input.get(1), nextLine;
                PilesAndNewLine lineAndPiles = getMoveablePiles(null, previousLine, line);
                moveablePiles += lineAndPiles.piles;
                nextInput.add(lineAndPiles.newLine);
                for (int i = 2; i < input.size(); i++) {
                    nextLine = input.get(i);
                    lineAndPiles = getMoveablePiles(previousLine, line, nextLine);
                    moveablePiles += lineAndPiles.piles;
                    nextInput.add(lineAndPiles.newLine);
                    previousLine = line;
                    line = nextLine;
                }
                lineAndPiles = getMoveablePiles(previousLine, line, null);
                moveablePiles += lineAndPiles.piles;
                nextInput.add(lineAndPiles.newLine);
                totalMoveablePiles += moveablePiles;
                input = nextInput;
            } while (moveablePiles != 0);
            System.out.println("Part 2 moveable piles: " + totalMoveablePiles);
        }
    }

    private static PilesAndNewLine getMoveablePiles(String previousLine, String line, String nextLine) {
        int moveablePiles = 0;
        StringBuilder newLine = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '@') {
                int piles = 0;
                piles += previousLine != null ? countPiles(i, previousLine) : 0;
                piles += countPiles(i, line) - 1;
                piles += nextLine != null ? countPiles(i, nextLine) : 0;
                if (piles < 4) {
                    moveablePiles++;
                    newLine.append('X');
                } else {
                    newLine.append(line.charAt(i));
                }
            } else {
                newLine.append(line.charAt(i));
            }
        }
        return new PilesAndNewLine(moveablePiles, newLine.toString());
    }

    private static int countPiles(int i, String line) {
        int piles = 0;
        if (i - 1 >= 0) piles += (line.charAt(i - 1) == '@') ? 1 : 0;
        piles += (line.charAt(i) == '@') ? 1 : 0;
        if (i + 1 < line.length()) piles += (line.charAt(i + 1) == '@') ? 1 : 0;
        return piles;
    }

    record PilesAndNewLine(int piles, String newLine) {
    }

    ;
}
