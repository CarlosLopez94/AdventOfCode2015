/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day02;

import adventofcode2015.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class Day2 {

    public void main() {
        System.out.println("Day 2 - Part 1");
        //Get presents
        List<String> input = Util.ReadFile("day02/input.txt");
        List<Present> presents = parseToPresentList(input);
        //Calculate total wrapping
        int wrapping = 0;
        int ribbon = 0;
        for (Present p : presents) {
            wrapping += p.getRequiredWrapping();
            ribbon += p.getRequiredRibbon();
        }
        System.out.println("The required wrapping for the presents is: " + wrapping);
        System.out.println("Part 2");

       System.out.println("The required ribbon for the presents is: " + ribbon);
    }

    private List<Present> parseToPresentList(List<String> input) {
        List<Present> presents = new ArrayList<>();
        for (String line : input) {
            String[] presentTokens = line.split("x");
            int length = Integer.parseInt(presentTokens[0]);
            int width = Integer.parseInt(presentTokens[1]);
            int height = Integer.parseInt(presentTokens[2]);
            presents.add(new Present(length, width, height));
        }
        return presents;
    }

}
