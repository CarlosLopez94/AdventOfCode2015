/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day11;

import adventofcode2015.Util;

/**
 *
 * @author carlos
 */
public class Day11 {

    public void main() {
        System.out.println("Day 11 - Part 1");
        String inputPassword = Util.ReadFileOneLine("day11/input.txt");

        //Look for new Password
        int cont = 0;
        String passwordCandidate = inputPassword;
        while (!isValidPassword(passwordCandidate)) {
            passwordCandidate = incrementPassword(passwordCandidate);
            cont++;
        }

        System.out.println("After " + cont + " iterations, last password was: '" + inputPassword + "' and "
                + " the new password is: '" + passwordCandidate + "'");

        System.out.println("Day 11 - Part 2");
        //Calculate next password and repeat process
        passwordCandidate = incrementPassword(passwordCandidate);
        cont = 0;
        while (!isValidPassword(passwordCandidate)) {
            passwordCandidate = incrementPassword(passwordCandidate);
            cont++;
        }

        System.out.println("Again, after " + cont + " iterations more, next password is: '" + passwordCandidate + "'");

    }

    //Validate Password
    private boolean isValidPassword(String passwordCandidate) {
        return firstRequirement(passwordCandidate)
                && secondRequirement(passwordCandidate)
                && thirdRequirement(passwordCandidate);
    }

    private boolean firstRequirement(String password) {
        int i = 0;

        char nextCharToCheck = ' ';
        int checkCont = 0;
        while (checkCont <= 1 && i < password.length()) {
            char currentChar = password.charAt(i);
            if (currentChar == nextCharToCheck) {
                checkCont++;
            } else {
                checkCont = 0;
            }
            nextCharToCheck = (char) (currentChar + 1);
            i++;
        }

        return checkCont > 1;
    }

    private boolean secondRequirement(String password) {
        return !password.contains("i") && !password.contains("o") && !password.contains("l");
    }

    private boolean thirdRequirement(String password) {
        return thirdRequirementAux(password, 2);
    }

    private boolean thirdRequirementAux(String password, int deepCont) {
        if (deepCont == 0) {
            return true;
        } else {
            int i = 0;
            boolean found = false;
            char lastChar = ' ';

            while (!found && i < password.length()) {
                char currentChar = password.charAt(i);
                if (lastChar == currentChar) {
                    return thirdRequirementAux(password.substring(i + 1), deepCont - 1);
                } else {
                    lastChar = currentChar;
                }
                i++;
            }
            return false;
        }
    }

    //Increment Password
    private String incrementPassword(String password) {
        return incrementPasswordAux(password);
    }

    private String incrementPasswordAux(String password) {
        int lastPosition = password.length() - 1;
        if (password.charAt(lastPosition) != 'z') {
            return password.substring(0, lastPosition) + (char) (password.charAt(lastPosition) + 1);
        } else {
            return incrementPasswordAux(password.substring(0, lastPosition)) + 'a';
        }
    }
}
