/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day01;

import adventofcode2015.Util;

/**
 *
 * @author Carlos
 */
public class Day1 {

    private int floor;

    public Day1() {
        floor = 0;
    }

    public void main() {
        System.out.println("Day 1 - Part 1");
        String input = Util.ReadFileOneLine("day01/input.txt");
        System.out.println("Your input is: " + input);

        //GoUpstairs and downstairs
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                goUpstairs();
            } else {
                goDownstairs();
            }
        }
        System.out.println("The final floor is: " + getFloor());
        System.out.println("Part 2");
        floor = 0;
        //GoUpstairs and downstairs
        boolean find = false;
        int basementPos=0;
        for (int i = 0; i < input.length() && !find; i++) {
            if (input.charAt(i) == '(') {
                goUpstairs();
            } else {
                goDownstairs();
            }
            
            if (getFloor()<0) {
                find=true;
                basementPos = i+1;
            }
        }
        System.out.println("The basement is reached on position: " + basementPos);
    }

    public void goUpstairs() {
        floor++;
    }

    public void goDownstairs() {
        floor--;
    }

    public int getFloor() {
        return floor;
    }
}
