package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {
    public static BufferedReader getInput(String name) {
        return new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream(name)));
    }

}
