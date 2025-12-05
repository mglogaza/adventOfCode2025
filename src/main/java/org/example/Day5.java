package org.example;

import java.io.IOException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
    }

    private static void task1() throws IOException {
        List<Range> ranges = new LinkedList<>();
        int fresh = 0;
        try (var reader = Util.getInput("/day5.txt")) {
            String nextLine;
            while (!(nextLine = reader.readLine()).trim().isEmpty()) {
                ranges.add(new Range(nextLine.split("-")));
            }
            while ((nextLine = reader.readLine()) != null) {
                long id = Long.parseLong(nextLine);
                fresh += isFresh(id, ranges) ? 1 : 0;
            }

            System.out.println("Part 1 fresh: " + fresh);
        }
    }

    private static void task2() throws IOException {
        List<Range> ranges = new LinkedList<>();
        try (var reader = Util.getInput("/day5.txt")) {
            String nextLine;
            while (!(nextLine = reader.readLine()).trim().isEmpty()) {
                var split = nextLine.split("-");
                long low = Long.parseLong(split[0]);
                long high = Long.parseLong(split[1]);
                List<Range> toRemove = new LinkedList<>();
                for(var range : ranges){
                    if(range.low> low && range.high < high){
                        toRemove.add(range);
                    }
                    if(low >= range.low && low <= range.high){
                        low = range.high + 1;
                    }
                    if(high >= range.low && high <= range.high){
                        high = range.low -1;
                    }
                }
                ranges.removeAll(toRemove);
                toRemove.clear();
                if(low <= high){
                    ranges.add(new Range(low,high));
                }
            }
            long freshIds = 0;
            for(var range: ranges){
                freshIds += range.high - range.low + 1;
            }

            System.out.println(ranges);
            System.out.println("Part 2 fresh ids: " + freshIds);
        }
    }

    private static boolean isFresh(long id, List<Range> ranges){
        for(var range : ranges){
            if(id >= range.low && id <= range.high){
                return true;
            }
        }
        return false;
    }

    record Range(long low,long high){
        public Range(String[] split) {
            this(Long.parseLong(split[0]),Long.parseLong(split[1]));
        }
    };
}
