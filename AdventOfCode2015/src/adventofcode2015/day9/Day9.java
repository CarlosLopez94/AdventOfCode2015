/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day9;

import adventofcode2015.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author carlos
 */
public class Day9 {

    private Map<String, List<Transition>> transitionMap;
    private SortedMap<Integer, String> distances;

    public void main() {
        System.out.println("Day 9 - Part 1");
        List<String> inputs = Util.ReadFile("day9/input.txt");
        
        // inits transition Map
        transitionMap = createTransitionsMap(inputs);

        // calculate distances
        distances = new TreeMap<>();

        distances.put(Integer.MAX_VALUE, "DUMMY");
        for (String startVertex : transitionMap.keySet()) {
            calculateLowestDistancesFromVertex(startVertex);
        }

        System.out.println("The Lowest path is " + distances.get(distances.firstKey()) + " with a weight of: " + distances.firstKey());

        
        System.out.println("Day 9 - Part 2");
        
        //Clear distances
        distances.clear();
        distances.put(0, "DUMMY");
        for (String startVertex : transitionMap.keySet()) {
            calculateGreatestDistancesFromVertex(startVertex);
        }
        System.out.println("The Greatest path is " + distances.get(distances.lastKey()) + " with a weight of: " + distances.lastKey());
    }

    public void calculateLowestDistancesFromVertex(String fromVertex) {
        Set<String> notVisitedVertex = new TreeSet<>();

        notVisitedVertex.addAll(transitionMap.keySet());

        calculateLowestDistancesFromVertexAux(fromVertex, notVisitedVertex, 0, "[" + fromVertex, 1);
    }

    public void calculateLowestDistancesFromVertexAux(String fromVertex, Set<String> notVisitedVertex, int currentWeight, String vertexPath, int deep) {
        notVisitedVertex.remove(fromVertex);
        if (notVisitedVertex.isEmpty()) {
            distances.put(currentWeight, vertexPath + "]");
        } else if (currentWeight <= distances.firstKey()) {
            List<Transition> transitionsOfVertex = transitionMap.get(fromVertex);

            //If there is a possible transition (not visited yet), then recursive call
            for (Transition candidateTrans : transitionsOfVertex) {
                if (notVisitedVertex.contains(candidateTrans.getLabel())) {
                    int nextWeight = currentWeight + candidateTrans.getWeight();
                    String nexPath = vertexPath + " -> " + candidateTrans.getLabel();
                    
                    Set<String> nextNotVisitedVertex = new TreeSet<>();
                    nextNotVisitedVertex.addAll(notVisitedVertex);
                    
                    calculateLowestDistancesFromVertexAux(candidateTrans.getLabel(), nextNotVisitedVertex, nextWeight, nexPath, deep+1);
                }
            }
        }
        //otherwhise, this path is discarded
    }

 
       public void calculateGreatestDistancesFromVertex(String fromVertex) {
        Set<String> notVisitedVertex = new TreeSet<>();

        notVisitedVertex.addAll(transitionMap.keySet());

        calculateGreatestDistancesFromVertexAux(fromVertex, notVisitedVertex, 0, "[" + fromVertex, 1);
    }

    public void calculateGreatestDistancesFromVertexAux(String fromVertex, Set<String> notVisitedVertex, int currentWeight, String vertexPath, int deep) {
        notVisitedVertex.remove(fromVertex);
        if (notVisitedVertex.isEmpty()) {
            distances.put(currentWeight, vertexPath + "]");
        } else{
            List<Transition> transitionsOfVertex = transitionMap.get(fromVertex);

            //If there is a possible transition (not visited yet), then recursive call. If there isnt any, then this path is ended
            for (Transition candidateTrans : transitionsOfVertex) {
                if (notVisitedVertex.contains(candidateTrans.getLabel())) {
                    int nextWeight = currentWeight + candidateTrans.getWeight();
                    String nexPath = vertexPath + " -> " + candidateTrans.getLabel();
                    
                    Set<String> nextNotVisitedVertex = new TreeSet<>();
                    nextNotVisitedVertex.addAll(notVisitedVertex);
                    
                    calculateGreatestDistancesFromVertexAux(candidateTrans.getLabel(), nextNotVisitedVertex, nextWeight, nexPath, deep+1);
                }
            }
        }
    }
    
    private Map<String, List<Transition>> createTransitionsMap(List<String> input) {
        Map<String, List<Transition>> map = new HashMap<>();

        for (String line : input) {
            //Separate into tokens and init Vertex and Transition
            String[] tokensTo = line.split("to");
            String originalVertex = tokensTo[0].trim();

            String[] tokensEqual = tokensTo[1].split("=");
            String transitionVertex = tokensEqual[0].trim();
            int transitionWeight = Integer.valueOf(tokensEqual[1].trim());

            Transition newTransition = new Transition(transitionVertex, transitionWeight);

            //Set into map
            if (map.containsKey(originalVertex)) {
                map.get(originalVertex).add(newTransition);
            } else {
                List<Transition> transitionList = new ArrayList<>();
                transitionList.add(newTransition);

                map.put(originalVertex, transitionList);
            }
            
            Transition inverseNewTransition = new Transition(originalVertex, transitionWeight);
             if (map.containsKey(transitionVertex)) {
                map.get(transitionVertex).add(inverseNewTransition);
            } else {
                List<Transition> transitionList = new ArrayList<>();
                transitionList.add(inverseNewTransition);

                map.put(transitionVertex, transitionList);
            }
        }
        
        
        return map;
    }

    private class Transition {

        private final String label;
        private final int weight;

        public Transition(String label, int weight) {
            this.label = label;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 13 * hash + this.weight;
            hash = 13 * hash + Objects.hashCode(this.label);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Transition other = (Transition) obj;
            if (this.weight != other.weight) {
                return false;
            }
            if (!Objects.equals(this.label, other.label)) {
                return false;
            }
            return true;
        }

    }
}
