/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day13;

import adventofcode2015.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author carlos
 */
public class Day13 {

    private static Map<String, Map<String, Integer>> mapHappiness;

    public void main() {
        System.out.println("Day 12 - Part 1");
        List<String> input = Util.ReadFile("day13/input.txt");

        //Load input
        mapHappiness = loadHappinessInput(input);

        int maxHappiness = getMaxHappiness(new TreeSet<>(mapHappiness.keySet()));

        System.out.println(maxHappiness);

    }

    private Map<String, Map<String, Integer>> loadHappinessInput(List<String> listInput) {
        Map<String, Map<String, Integer>> mapHappiness = new HashMap<>();

        for (String input : listInput) {
            String[] inputTokens = input.split(" ");
            String firstPerson = inputTokens[0];
            String secondPerson = inputTokens[10].replace(".", "");

            Integer happinessValue = Integer.valueOf(inputTokens[3]);

            //If its lose, then happinessVluae is negative
            if (inputTokens[2].equals("lose")) {
                happinessValue = -happinessValue;
            }

            if (mapHappiness.containsKey(firstPerson)) {
                //Get value
                Map<String, Integer> mapValue = mapHappiness.get(firstPerson);
                //Add new value
                mapValue.put(secondPerson, happinessValue);
                mapHappiness.put(firstPerson, mapValue);
            } else {
                //Get value
                Map<String, Integer> mapValue = new HashMap<>();
                //Add new value
                mapValue.put(secondPerson, happinessValue);
                mapHappiness.put(firstPerson, mapValue);
            }

        }

        return mapHappiness;
    }

    private Integer getMaxHappiness(Set<String> people) {
        String currentPerson = people.iterator().next();
        people.remove(currentPerson);

        List<String> tablePositision = new ArrayList<>();
        tablePositision.add(currentPerson);

        return getMaxHappinessAux(people, currentPerson, 0, tablePositision);
    }

    private Integer getMaxHappinessAux(Set<String> people, String currentPerson, Integer currentHappiness, List<String> tablePositions) {
        if (!people.isEmpty()) {
            Set<Integer> maxValues = new TreeSet<>();
            //maxValues.add(currentHappiness); //not necessary
            for (String currentCandidate : people) {
                Set<String> candidates = new TreeSet(people);
                List<String> tableCandidate = new ArrayList(tablePositions);

                candidates.remove(currentCandidate);
                tableCandidate.add(currentCandidate);
                    //hay que seguirr pero no esta haciendo la suma bien, en este caso suma 2 de mas por la cara
                Integer candidateValue = mapHappiness.get(currentPerson).get(currentCandidate) + mapHappiness.get(currentCandidate).get(currentPerson);
                maxValues.add(getMaxHappinessAux(candidates, currentCandidate, currentHappiness + candidateValue, tableCandidate));
            }
            System.out.println("index: " + tablePositions.size() + "  -->  " + tablePositions.toString() + "  --> " + Collections.max(maxValues));
            return Collections.max(maxValues);
        } else {
            tablePositions.add(currentPerson);
                        String firstPerson = tablePositions.get(0);

            int hap = currentHappiness + mapHappiness.get(currentPerson).get(firstPerson);
            System.out.println("index: " + tablePositions.size() + "  -->  " + tablePositions.toString() + "  --> " + hap);
            return hap;
        }
    }

}
