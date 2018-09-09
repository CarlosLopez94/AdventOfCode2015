/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day8;

import adventofcode2015.Util;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Day8 {

    public void main() {
        System.out.println("Day 8 - Part 1");
        List<String> inputs = Util.ReadFile("day8/input.txt");

        int charsInString = 0;
        int charsInCode = 0;
        for (String i : inputs) {
            charsInString += getCharsInString(i);
            charsInCode += getCharsInCode(i);
        }
        int difference = charsInString - charsInCode;
        System.out.println("Diference between chars in string (" + charsInString + ") and chars in code(" + charsInCode + "): " + difference);

        System.out.println("Part 2");
        int charsEncoded = 0;
        charsInCode = 0;
        for (String i : inputs) {
            int encodedAux = getEncodedChars(i);
            charsEncoded += encodedAux;
            charsInCode += getCharsInString(i);
        }

        int differencePart2 = charsEncoded - charsInCode;
        System.out.println("Diference between encoded chars(" + charsEncoded + ") and chars in code(" + charsInCode + "): " + differencePart2);
    }

    private int getCharsInString(String s) {
        return s.length();
    }

    private int getCharsInCode(String s) {
        int chars = 0;
        int index = 0;
        while (index < s.length()) {
            char currentChar = s.charAt(index);

            if (currentChar == '\\') {
                char nextChar = s.charAt(index + 1);
                chars++;
                if (nextChar == 'x') {
                    index += 3;
                } else {
                    index++;
                }
            } else if (currentChar != '\"') {
                chars++;
            }
            index++;
        }
        return chars;
    }

    private int getEncodedChars(String s) {
        int chars = 0;
        int index = 0;

        while (index < s.length()) {
            char currentChar = s.charAt(index);
            if (currentChar == '\"') {
                chars += 2;
            } else if (currentChar == '\\') {
                chars += 2;
            } else {
                chars++;
            }
            index++;
        }
        return chars + 2;
    }

}
