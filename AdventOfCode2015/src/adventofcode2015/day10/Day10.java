/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day10;

import adventofcode2015.Util;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Day10 {

    final int CONT_DAY1 = 40;
    final int CONT_DAY2 = 50;

    public void main() {
        System.out.println("Day 9 - Part 1");
        String inputSequence = Util.ReadFile("day10/input.txt").get(0);

        String sequence = inputSequence;
        for (int i = 0; i < CONT_DAY1; i++) {
            sequence = transformSequence(sequence);
        }
        
        System.out.println("The length of '" + inputSequence + "' after " + CONT_DAY1 + " iterations, it's : " + sequence.length() + ". The final sequence is:\n'" + sequence + "'");

        
        System.out.println("Day 9 - Part 2");

        sequence = inputSequence;
        for (int i = 0; i < CONT_DAY2; i++) {
            sequence = transformSequence(sequence);
        }
        
        System.out.println("The length of '" + inputSequence + "' after " + CONT_DAY2 + " iterations, it's : " + sequence.length() + ". The final sequence is:\n'" + sequence + "'");

        
    }

    private String transformSequence(String inputSequence) {
        StringBuilder newSequence = new StringBuilder();

        int i = 0;
        char lastChar = inputSequence.charAt(0); //first char
        int currentCharOcurrence = 0;
        while (i < inputSequence.length()) {
            char currentChar = inputSequence.charAt(i);
            if (currentChar == lastChar) {
                currentCharOcurrence++;
            } else {
                newSequence.append(currentCharOcurrence).append(lastChar);
                currentCharOcurrence = 1;
            }

            //Prepare for next iteration
            lastChar = currentChar;
            i++;
        }

                newSequence.append(currentCharOcurrence).append(lastChar);

        return newSequence.toString();
    }

}
