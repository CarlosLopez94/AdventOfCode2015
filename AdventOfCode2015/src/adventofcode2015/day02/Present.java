/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Carlos
 */
public class Present {

    private final int length;
    private final int width;
    private final int height;
    Pair<Integer, Integer> lowestSide;

    public Present(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;

        //Get the lowest Side
        List<Integer> orderedDimensions = new ArrayList<>();
        orderedDimensions.add(length);
        orderedDimensions.add(width);
        orderedDimensions.add(height);

        Collections.sort(orderedDimensions);
        lowestSide = new Pair<>(orderedDimensions.get(0), orderedDimensions.get(1));
    }

    public int getRequiredWrapping() {
        return 2 * length * width
                + 2 * width * height
                + 2 * height * length
                + lowestSide.getKey() * lowestSide.getValue();
    }

    public int getRequiredRibbon() {
        return 2 * lowestSide.getKey()
                + 2 * lowestSide.getValue()
                + length * width * height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
