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
            //System.out.println(i + " ÑÑÑÑÑÑ string:" + stringAux + "  code:" + stringCode);
        }
        System.out.println(charsInString - charsInCode);
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
                }else{
                    index++;
                }
            } else if (currentChar != '\"') {
                chars++;
            }
            index++;
        }
        return chars;
    }
}
