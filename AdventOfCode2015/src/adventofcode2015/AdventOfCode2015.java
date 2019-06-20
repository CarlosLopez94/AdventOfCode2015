/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015;

import adventofcode2015.day01.Day1;
import adventofcode2015.day02.Day2;
import adventofcode2015.day03.Day3;
import adventofcode2015.day04.Day4;
import adventofcode2015.day05.Day5;
import adventofcode2015.day06.Day6;
import adventofcode2015.day07.Day7;
import adventofcode2015.day08.Day8;
import adventofcode2015.day09.Day9;
import adventofcode2015.day10.Day10;
import adventofcode2015.day11.Day11;
import adventofcode2015.day12.Day12;
import adventofcode2015.day13.Day13;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class AdventOfCode2015 {

    public static void main(String[] args) {
        System.out.println("Welcome to Advent of Code 2015! Please choose a day between 1 and 25:");
        int day;
        do {
            //day = ReadDay();
            day = 13; //DELETE
            switch (day) {
                case 1:
                    Day1 day1 = new Day1();
                    day1.main();
                    break;
                case 2:
                    Day2 day2 = new Day2();
                    day2.main();
                    break;
                case 3:
                    Day3 day3 = new Day3();
                    day3.main();
                    break;
                case 4:
                    Day4 day4 = new Day4();
                    day4.main();
                    break;
                case 5:
                    Day5 day5 = new Day5();
                    day5.main();
                    break;
                case 6:
                    Day6 day6 = new Day6();
                    day6.main();
                    break;
                case 7:
                    Day7 day7 = new Day7();
                    day7.main();
                    break;
                case 8:
                    Day8 day8 = new Day8();
                    day8.main();
                    break;
                case 9:
                    Day9 day9 = new Day9();
                    day9.main();
                    break;
                case 10:
                    Day10 day10 = new Day10();
                    day10.main();
                    break;
                case 11:
                    Day11 day11 = new Day11();
                    day11.main();
                    break;
                case 12:
                    Day12 day12 = new Day12();
                    day12.main();
                    break;
                 case 13:
                    Day13 day13 = new Day13();
                    day13.main();
                    break;
                default:
                    System.out.println("ups, this day isn't avaliable yet! Try again");
            }
        } while (day < 0 && day > 25);
    }

    public static int ReadDay() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

}
