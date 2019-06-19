/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day06;

import adventofcode2015.Util;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class Day6 {

    private final int SIZE = 1000;
    private int[][] lights;

    public Day6() {
        init();
    }

    private void init() {
        lights = new int[SIZE][SIZE];
    }

    public void main() {
        System.out.println("Day 6 - Part 1");
        List<String> configurations = Util.ReadFile("day06/input.txt");
        readConfigurations(configurations, true);
        int lightsOn = countLightsOn();
        System.out.println("Number of lights On: " + lightsOn);

        System.out.println("Part 2");
        init();
        readConfigurations(configurations, false);
        int totalBrightness = getTotalbrightness();
        System.out.println("Total brightness of lights is: " + totalBrightness);

    }

    private void readConfigurations(List<String> configurations, boolean part1) {
        for (String configuration : configurations) {

            String[] tokens = configuration.split(" ");
            int x1, y1, x2, y2;
            String instruction = tokens[0];
            int instructionIndexAux = 0;
            if (instruction.equals("turn")) {
                instruction += " " + tokens[1];
                instructionIndexAux = 1;
            }
            String[] coordinate1Tokens = tokens[1 + instructionIndexAux].split(",");
            x1 = Integer.parseInt(coordinate1Tokens[0]);
            y1 = Integer.parseInt(coordinate1Tokens[1]);

            String[] coordinate2Tokens = tokens[3 + instructionIndexAux].split(",");
            x2 = Integer.parseInt(coordinate2Tokens[0]);
            y2 = Integer.parseInt(coordinate2Tokens[1]);

            applyConfiguration(x1, y1, x2, y2, instruction, part1);
        }
    }

    private void applyConfiguration(int x1, int y1, int x2, int y2, String instruction, boolean part1) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                switch (instruction) {
                    case "turn off":
                        turnOffLight(i, j, part1);
                        break;
                    case "turn on":
                        turnOnLight(i, j, part1);
                        break;
                    case "toggle":
                        toggleLight(i, j, part1);
                        break;
                }
            }
        }
    }

    private void turnOffLight(int x, int y, boolean part1) {
        if (part1) {
            lights[x][y] = 0;
        } else { //part 2
            if (lights[x][y] > 0) {
                lights[x][y]--;
            }
        }
    }

    private void turnOnLight(int x, int y, boolean part1) {
        if (part1) {
            lights[x][y] = 1;
        } else { //part 2
            lights[x][y]++;
        }
    }

    private void toggleLight(int x, int y, boolean part1) {
        if (part1) {
            if (lights[x][y] == 0) { //if its turn off
                turnOnLight(x, y, true);
            } else {//if not, its on so turn off
                turnOffLight(x, y, true);
            }
        } else {
            lights[x][y] += 2;
        }
    }

    private int countLightsOn() {
        int cont = 0;
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[0].length; j++) {
                if (lights[i][j] == 1) {
                    cont++;
                }
            }
        }
        return cont;
    }

    private int getTotalbrightness() {
        int brightnessCont = 0;
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[0].length; j++) {
                brightnessCont += lights[i][j];
            }
        }
        return brightnessCont;
    }

    private void printLights() {
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[0].length; j++) {
                System.out.print(lights[i][j]);
            }
            System.out.println();
        }
    }
}
