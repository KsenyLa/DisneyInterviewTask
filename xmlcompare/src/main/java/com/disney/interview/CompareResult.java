package com.disney.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompareResult {
    public boolean areEqual;
    public List<Difference> differences;

    public CompareResult() {
        differences = new ArrayList<>();
        areEqual = true;
    }

    /**
     * Adding differences and marking compare result not equal
     * @param field name of element (node, attribute, text) with difference
     * @param left value of field in the left (first) file
     * @param right value of field in the right (second) file
     */
    public void addDifference(String field, String left, String right) {
        areEqual = false;
        differences.add(new Difference(field, left, right));
    }

    @Override
    public String toString() {
        List<String> listOfStringDifferences = differences
                .stream()
                .map(item -> item.toString())
                .collect(Collectors.toList());
        String difString = String.join("\n", listOfStringDifferences);
        return String.format("CompareResult{Files Are Equal:%s, differences=\n%s}", areEqual, difString);
    }
}