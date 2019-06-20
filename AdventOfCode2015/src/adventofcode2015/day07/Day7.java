/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day7;

import adventofcode2015.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos
 */
public class Day7 {

    private final String REGEX_ONLY_DIGITS = "\\b\\d+\\b";
    private final Pattern onlyDigits;
    private Map<String, Integer> wires;
    private final int BITS_MASK = 0xFFFF;

    public Day7() {
        init();
        onlyDigits = Pattern.compile(REGEX_ONLY_DIGITS);
    }

    public void init() {
        wires = new TreeMap<>();
    }

    public void main() {
        System.out.println("Day 7 - Part 1");
        List<String> circuits = Util.ReadFile("day7/input.txt");

        circuits = sortCircuits(circuits);

        for (String circuit : circuits) {
            readCircuitInstruction(circuit);
        }

        System.out.println(wires);
        System.out.println("The wire 'a' has a signal of " + wires.get("a"));
    }

    private void readCircuitInstruction(String circuit) {
        System.out.println(circuit);
        String[] tokens = circuit.split(" -> ");
        String[] leftTokens = tokens[0].split(" ");
        String receiver = tokens[1];

        switch (leftTokens.length) {
            case 1:
                //init wire instruction
                int value = getWireValue(leftTokens[0]);
                init(value, receiver);
                break;
            case 2:
                //its a NOT instructtion
                String wire = leftTokens[1];
                not(wire, receiver);
                break;
            default:        //AND, OR, RSHITF, RSHIFT
                String wire1 = leftTokens[0];
                String instruction = leftTokens[1];
                String wire2 = leftTokens[2];
                switch (instruction) {
                    case "AND":
                        and(wire1, wire2, receiver);
                        break;
                    case "OR":
                        or(wire1, wire2, receiver);
                        break;
                    case "RSHIFT":
                        rshift(wire1, Integer.parseInt(wire2), receiver);
                        break;
                    case "LSHIFT":
                        lshift(wire1, Integer.parseInt(wire2), receiver);
                        break;
                }
                break;
        }

    }

    private void init(int value, String receiver) {
        wires.put(receiver, value);
    }

    private List<String> sortCircuits(List<String> circuit) {
        Map<String, String> dependent = new TreeMap<>();

        for (String c : circuit) {
            String[] tokens = c.split(" ");
            String dependencies = "";
            
            
            switch (tokens.length) {
                case 3:
                    dependent.put(tokens[2], "");
                    break;
                case 4:
                    dependent.put(tokens[3], tokens[1]);
                    break;
                case 5:
                    break;
            }

            dependent.put(tokens[4], tokens[0] + "/" + tokens[2]);

        }
        Map<String, Integer> order = new TreeMap<>();

        for (String d : dependent.keySet()) {
            sortDepedent(dependent, order, d);
        }

        return new ArrayList<>(order.keySet());
    }

    private void sortDepedent(Map<String, String> dependent, Map<String, Integer> order, String circuitToOrder) {
        if (!order.containsKey(circuitToOrder)) {
            sortDependentAux(dependent, order, circuitToOrder);
        }
    }

    private int sortDependentAux(Map<String, String> dependent, Map<String, Integer> order, String circuitToOrder) {
        if (order.containsKey(circuitToOrder)) {
            return order.get(circuitToOrder);
        }
        int orderIndex = 1;

        if (!dependent.get(circuitToOrder).isEmpty()) {
            String[] wiresDependent = dependent.get(circuitToOrder).split("/");
            if (wiresDependent.length == 1) {
                orderIndex = 1 + sortDependentAux(dependent, order, wiresDependent[0]);
            } else {
                orderIndex = 1 + Math.max(sortDependentAux(dependent, order, wiresDependent[0]), sortDependentAux(dependent, order, wiresDependent[1]));
            }
        }

        order.put(circuitToOrder, orderIndex);
        return orderIndex;
    }

    private void and(String wire1, String wire2, String receiver) {
        insertIfIsNotContain(wire1);
        insertIfIsNotContain(wire2);

        int andValue = getWireValue(wire1) & getWireValue(wire2);
        wires.put(receiver, andValue);
    }

    private void or(String wire1, String wire2, String receiver) {
        insertIfIsNotContain(wire1);
        insertIfIsNotContain(wire2);

        int orValue = getWireValue(wire1) | getWireValue(wire2);
        wires.put(receiver, orValue);
    }

    private void not(String wire, String receiver) {
        insertIfIsNotContain(wire);

        int notValue = (~getWireValue(wire)) & BITS_MASK;
        wires.put(receiver, notValue);
    }

    private void rshift(String wire, int value, String receiver) {
        insertIfIsNotContain(wire);

        int rShiftValue = (getWireValue(wire) >> value);
        wires.put(receiver, rShiftValue);
    }

    private void lshift(String wire, int value, String receiver) {
        insertIfIsNotContain(wire);

        int lShiftValue = (getWireValue(wire) << value);
        wires.put(receiver, lShiftValue);
    }

    private int getWireValue(String wireOrValue) {
        Matcher matcher = onlyDigits.matcher(wireOrValue);
        if (matcher.matches()) {
            return Integer.parseInt(wireOrValue);
        } else {
            return wires.get(wireOrValue);
        }
    }

    private void insertIfIsNotContain(String wire) {
        if (!wires.containsKey(wire)) {
            //   wires.put(wire, 0);
        }
    }
}
