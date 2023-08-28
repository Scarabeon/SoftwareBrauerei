package sk.scarabeon.challenges;

import java.util.Arrays;

/**
 * Challenge 1 - Array Split
 * @author Michal Bielik
 */
public class ArraySplit {

    /* The capacity of arrays can be modified here if needed */
    private final int CAPACITY = 3;
    /* Troll resistance, numbers below 1 are not allowed as capacity ☺ */
    private final int REAL_CAPACITY = Math.max(1, CAPACITY);
    private final String ITEMS_SEPARATOR = ", ";

    public String split(String[] elements) {

        /* Computes into how many subarrays will be input divided */
        final int resultSize = (int) Math.ceil((double) elements.length / (double) REAL_CAPACITY);
        /* The main array that will contain subarrays */
        String[] results = new String[resultSize];
        /* Counter of ow many subarrays were already added */
        int counter = 0;
        /* Counter of which item was added into subarray */
        int partCounter = 0;
        /* Computes the effective capacity of the next subarray
         * If only one subarray occurs, no other null elements will be added
         */
        String[] resultPart = new String[nextSubarrayCapacity(elements.length, counter)];
        /* Here function is not advisable because of subarray reuse */
        for (String element : elements) {
            resultPart[partCounter++] = element;
            /* Elements into subarray will be added until reaching its capacity, then counter resets */
            if (partCounter == REAL_CAPACITY || element.equals(elements[elements.length - 1])) {
                partCounter = 0;
                /* The subarray is added into final array already as String */
                results[counter++] = String.join(ITEMS_SEPARATOR, resultPart);
                /* Counts if the next array will have full capacity or is the last one with possibly lower capacity
                 * To avoid null elements being part of the output
                 */
                resultPart = new String[nextSubarrayCapacity(elements.length, counter)];
            }
        }
        return output(results);
    }

    /**
     * Computes if the next subarray is full or limited
     * @param elements - number of all elements in input
     * @param counter - how many suarrays were added into main array
     * @return the effective capacity of the next subarray
     */
    private int nextSubarrayCapacity(int elements, int counter) {
        /* In case counter was not incremented yet, it cannot be 0 */
        counter = Math.max(1, counter);
        return elements - (counter * REAL_CAPACITY) >= REAL_CAPACITY ? REAL_CAPACITY : elements % (counter * REAL_CAPACITY);
    }

    /**
     * To make output readable, the output string is built here
     * @param result - the final array
     * @return readable output starting ad ending with [] and separated by defined separator
     */
    private String output(String[] result) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(result).forEach(part -> builder.append("[")
                .append(String.join(ITEMS_SEPARATOR, part))
                .append("]")
                .append(ITEMS_SEPARATOR));
        return builder.toString().endsWith(ITEMS_SEPARATOR) ? builder.substring(0, builder.toString().length() - ITEMS_SEPARATOR.length()) : builder.toString();
    }
}