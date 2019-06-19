/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015.day12;

import adventofcode2015.Util;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author carlos
 */
public class Day12 {

    public void main(){
        System.out.println("Day 12 - Part 1");
        String inputJSON = Util.ReadFileOneLine("day12/input.txt");
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(inputJSON).getAsJsonObject();
       
        System.out.println("Sum of all values is: " + getSumNoRedContent(jsonObject, true));

        System.out.println("Day 12 - Part 2");

        System.out.println("The sum of all the values (ignored red values) is " + getSumNoRedContent(jsonObject, false));

    }

   private int getSumNoRedContent(JsonObject objectJson, boolean ignoreRedValue) {
        Integer sum = 0;
        if (ignoreRedValue || !hasRedValue(objectJson)) {
            for (Map.Entry<String, JsonElement> pair : objectJson.entrySet()) {
                sum += getSumJsonElement(pair.getValue(), ignoreRedValue);
            }
        }
        return sum;
    }

    private int getSumJsonElement(JsonElement element, boolean ignoreRedValue) {
        int sum = 0;
        System.out.println(element);
        if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
            sum += element.getAsJsonPrimitive().getAsNumber().intValue();
        } else if (element.isJsonObject()) {
            sum += getSumNoRedContent(element.getAsJsonObject(), ignoreRedValue);
        } else if (element.isJsonArray()) {
            Iterator<JsonElement> arrayIt = element.getAsJsonArray().iterator();
            while (arrayIt.hasNext()) {
               sum+= getSumJsonElement(arrayIt.next(), ignoreRedValue);
            }

        }
        return sum;
    }

    private boolean hasRedValue(JsonObject object) {
        Iterator<Map.Entry<String, JsonElement>> it = object.entrySet().iterator();

        boolean hasRedValue = false;
        while (!hasRedValue && it.hasNext()) {
            Map.Entry<String, JsonElement> pair = it.next();
            hasRedValue = pair.getValue().isJsonPrimitive() && pair.getValue().getAsJsonPrimitive().isString() && pair.getValue().getAsJsonPrimitive().getAsString().equals("red");
        }

        return hasRedValue;
    }

}
