package sk.scarabeon;

import sk.scarabeon.challenges.ArraySplit;
import sk.scarabeon.challenges.MissingElement;
import sk.scarabeon.challenges.SortingObjects;
import sk.scarabeon.data.Inputs;

/**
 * Main executable class for all challenges
 * @author Michal Bielik
 */
public class Main {
    public static void main(String[] args) {
        ArraySplit arraySplit = new ArraySplit();
        MissingElement missingElement = new MissingElement();
        SortingObjects sortingObjects = new SortingObjects();

        System.out.println(arraySplit.split(Inputs.ARRAY_SPLIT));
        System.out.println(missingElement.find(Inputs.MISSING_ELEMENT));
        System.out.println(sortingObjects.sort());
    }
}