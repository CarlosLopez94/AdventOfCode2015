/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day4;

import adventofcode2015.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Day4 {

    private MessageDigest messageDigest;

    public Day4() {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Day4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void main() {
        System.out.println("Day 4 - Part 1");

        String input = Util.ReadFileOneLine("day4/input.txt");

        System.out.println(input);
        int number = findNumberToStartWithZeros(input, 5);
        System.out.println("To start with 5 zeros number is: " + number + " --> " + getMD5(input + number));

        System.out.println("\nPart 2");
        number = findNumberToStartWithZeros(input, 6);
        System.out.println("To start with 6 zeros number is: " + number + " --> " + getMD5(input + number));

    }

    private int findNumberToStartWithZeros(String key, int numberOfZeros) {
        int numberToAdd = 0;
        String md5ToCheck;
        do {
            md5ToCheck = getMD5(key + numberToAdd);
            numberToAdd++;
        } while (!checkZeros(md5ToCheck, numberOfZeros));
        numberToAdd--;
        return numberToAdd;
    }

    private String getMD5(String key) {
        StringBuilder md5 = new StringBuilder();

        byte[] bytes = messageDigest.digest(key.getBytes()); // get bytes
        for (int i = 0; i < bytes.length; i++) { //transform to hex string
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                md5.append('0');
            }
            md5.append(hex);
        }
        return md5.toString();
    }

    private boolean checkZeros(String toCheck, int numberOfZeros) {
        boolean isCorrect = true;
        int i = 0;
        while (isCorrect && i < numberOfZeros) {
            if (toCheck.charAt(i) != '0') {
                isCorrect = false;
            }
            i++;
        }
        return isCorrect;
    }
}
