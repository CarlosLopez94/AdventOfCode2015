/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day3;

import adventofcode2015.Util;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Carlos
 */
public class Day3 {

    private class Point implements Comparable<Point> {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object p) {
            if (getClass() != p.getClass()) {
                return false;
            }
            Point aux = (Point) p;
            return x == aux.getX() && y == aux.getY();
        }

        @Override
        public int compareTo(Point o) {
            if (x < o.x) {
                return -1;
            } else if (x > o.x) {
                return 1;
            } else {
                if (y < o.y) {
                    return -1;
                } else if (y > o.y) {
                    return 1;
                } else {//both are equal
                    return 0;
                }
            }
        }
    }

    Point santaPosition;
    Point roboSantaPosition;
    private SortedSet<Point> visitedHouses;

    public Day3() {
        init();
    }

    public void init() {
        visitedHouses = new TreeSet<>();
        santaPosition = new Point(0, 0);
        roboSantaPosition = new Point(0, 0);
        visitedHouses.add(santaPosition);
    }

    public void main() {
        System.out.println("Day 3 - Part 1");

        //Get input
        String steps = Util.ReadFileOneLine("day3/input.txt");
        //Follow the steps
        for (int i = 0; i < steps.length(); i++) {
            //Get next position
            santaPosition = nextPoint(santaPosition, steps.charAt(i));

            //Add to the visited positions (if its already inserted then it wont change anything)
            visitedHouses.add(santaPosition);
        }

        System.out.println("The number of visited houses is: " + visitedHouses.size());
        System.out.println("Part 2");
        init();
        for (int i = 0; i < steps.length(); i += 2) {
            //Get next position
            santaPosition = nextPoint(santaPosition, steps.charAt(i));
            visitedHouses.add(santaPosition);

            roboSantaPosition = nextPoint(roboSantaPosition, steps.charAt(i+1));
            visitedHouses.add(roboSantaPosition);

        }
        System.out.println("The number of visited houses (with roboSanta) this year is: " + visitedHouses.size());

    }

    private Point nextPoint(Point currentPoint, char step) {
        Point nextPoint = new Point(currentPoint.x, currentPoint.y);
        switch (step) {
            case '^':
                nextPoint.y--;
                break;
            case 'v':
                nextPoint.y++;
                break;
            case '>':
                nextPoint.x++;
                break;
            default:
                //'<'
                nextPoint.x--;
                break;
        }
        return nextPoint;
    }
}
