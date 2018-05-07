/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day5;

import adventofcode2015.Util;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class Day5 {

    public final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    public final String[] FORBIDDEN_STRINGS = {"ab", "cd", "pq", "xy"};

    public void main() {
        System.out.println("Day 5 - Part 1");
        List<String> strings = Util.ReadFile("day5/input.txt");

        int niceNumber = 0;
        for (String s : strings) {
            if (isNicePart1(s)) {
                niceNumber++;
            }
        }
        System.out.println("Number of nice strings is : " + niceNumber);

        System.out.println("\nPart 2");
        niceNumber = 0;
        for (String s : strings) {
            if (isNicePart2(s)) {
                niceNumber++;
            }
        }
        System.out.println("Number of nice strings is : " + niceNumber);
    }

    /**
     * Returns if its nice using part 1 rules
     *
     * @param string
     * @return
     */
    private boolean isNicePart1(String string) {
        return containsThreeVowels(string)
                && hasLetterTwice(string)
                && !hasForbiddenStrings(string);
    }

    /**
     * Returns if its nice using part 2 rules
     *
     * @param string
     * @return
     */
    private boolean isNicePart2(String string) {
        return appearsTwiceWithouthOverlapping(string)
                && letterRepeatsWithOneSpace(string);
    }

    /**
     * Check if the string has at least three vowels
     *
     * @param string
     * @return
     */
    private boolean containsThreeVowels(String string) {
        int vowelsCont = 0;
        int i = 0;
        while (vowelsCont < 3 && i < string.length()) {
            if (isVowel(string.charAt(i))) {
                vowelsCont++;
            }
            i++;
        }
        return vowelsCont >= 3;
    }

    /**
     * Checks if at least one letter appears twice in the string
     *
     * @return
     */
    private boolean hasLetterTwice(String string) {
        boolean appearTwice = false;
        int i = 0;
        while (!appearTwice && (i + 1) < string.length()) {
            appearTwice = string.charAt(i) == string.charAt(i + 1);
            i++;
        }
        return appearTwice;
    }

    private boolean hasForbiddenStrings(String string) {
        boolean hasForbiddenString = false;
        int i = 0;
        while (!hasForbiddenString && i < FORBIDDEN_STRINGS.length) {
            hasForbiddenString = string.contains(FORBIDDEN_STRINGS[i]);
            i++;
        }
        return hasForbiddenString;
    }

    private boolean appearsTwiceWithouthOverlapping(String string) {
        boolean isCorrect = false;
        int pairIndex = 0;
        while (!isCorrect && pairIndex + 2 < string.length()) {
            String pairToCheck = string.substring(pairIndex, pairIndex + 2); // get the pair to check
            isCorrect = string.substring(pairIndex + 2, string.length()).contains(pairToCheck); // check if its contained in the rest of the string
            pairIndex++;
        }

        return isCorrect;
    }

    private boolean letterRepeatsWithOneSpace(String string) {
        boolean appearTwice = false;
        int i = 0;
        while (!appearTwice && (i + 2) < string.length()) {
            appearTwice = string.charAt(i) == string.charAt(i + 2);
            i++;
        }
        return appearTwice;
    }

    private boolean isVowel(char character) {
        boolean isVowel = false;
        int i = 0;
        while (!isVowel && i < VOWELS.length) {
            isVowel = character == VOWELS[i];
            i++;
        }
        return isVowel;
    }
}
