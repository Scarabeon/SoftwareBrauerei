package sk.scarabeon.challenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Challenge 2 - Missing Element
 * @author Michal Bielik
 */
public class MissingElement {

    public String find(String[][] elements) {
        /* Unique set of all elements in all arrays */
        Set<String> allElements = new HashSet<>();
        Arrays.stream(elements).forEach(array -> allElements.addAll(Arrays.asList(array)));
        /* A working copy of the same set from where missing elements will be removed */
        Set<String> workingCopy = new HashSet<>(allElements);
        /* If an element is not present in any subarray will be removed */
        allElements.forEach(element -> Arrays.stream(elements).forEach(array -> {
            if (Arrays.stream(array).noneMatch(currentElement -> currentElement.equals(element))) {
                workingCopy.remove(element);
            }
        }));
        /* Now we have all elements that are present everywhere in working copy
         * We need the remaining ones so we remove there from the original elements set
         */
        allElements.removeAll(workingCopy);
        /* Lets sort the matching elements alphabetically before output */
        return output(allElements.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)));
    }

    /**
     * Makes coma separated output of matching elements
     * @param elements set of matching elements
     * @return coma separated string of matching elements
     */
    private String output(Set<String> elements) {
        return String.join(", ", elements);
    }
}