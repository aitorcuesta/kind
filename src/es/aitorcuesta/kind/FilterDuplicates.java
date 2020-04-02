package es.aitorcuesta.kind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterDuplicates {

    public static void main(String[] args) {
        
        // The arrays
        String[] keys = new String[] { "one", "two", "three", "four", "five" };

        Element[] elements = new Element[] { new Element("one", "value_1"), new Element("two", "value_2"), new Element("three", "value_3"), new Element("two", "value_2"), new Element("four", "value_4"), new Element("five", "value_5"),
                new Element("five", "value_5") };

        // For storing the result
        List<Element> result = new ArrayList<>();

        /*
         * SOLUTION 1: Without Set, Map, etc. interfaces. The longest one 
         * O(n^2) performance
         */

        // Iterate through keys
        for (String key : keys) {
            Element toInsert = null;
            // Iterate through elements looking for key
            for (Element element : elements) {
                if (key.equals(element.getKey())) {
                    toInsert = element;
                    break;
                }
            }
            if (null != toInsert) {
                // Is the key inserted previously?
                boolean previouslyInserted = false;
                for (Element element : result) {
                    if (key.equals(element.getKey())) {
                        previouslyInserted = true;
                        break;
                    }
                }
                if (!previouslyInserted) {
                    result.add(toInsert);
                }
            }
        }

        System.out.println(result);

        /*
         * SOLUTION 2: Using Map interface. Easiest but unsorted and probably not what we are looking for.
         * Let's think about an Element with key 6
         * O(n) performance
         */

        Map<String, Element> tempMap = new HashMap<>();

        for (Element element : elements) {
            if (!tempMap.containsKey(element.getKey())) {
                tempMap.put(element.getKey(), element);
            }
        }
        System.out.println(tempMap.values());

        /*
         * SOLUTION 3: Java8 functional programming
         * O(n^2) performance
         * This is probably the hardest to understand. It should be read like this:
         * For each key on keys array we do
         *      We get all the elements with the same key
         *      We get the first one
         *      And only if it is present we add it to our result
         *  
         *  The big advantage is that you use Java functions that you are sure that works.
         */

        result.clear();
        Arrays.stream(keys)
            .forEach(key -> Arrays.stream(elements)
                .filter(elem -> key.equals(elem.getKey()))
                .findFirst()
                .ifPresent(result::add));

        System.out.println(result);

    }

}
