package com.disney.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompareResult {
    public boolean areEqual;
    public List<Difference> differences;

    public CompareResult() {
        differences = new ArrayList<Difference>();
        areEqual = true;
    }

    public void addDifference(String field, String left, String right) {
        areEqual = false;
        differences.add(new Difference(field, left, right));
    }

    @Override
    public String toString() {
        List<String> listOfStringDifferencies = differences.stream().map(item -> item.toString()).collect(Collectors.toList());
        String difString = String.join("\n", listOfStringDifferencies);
        return String.format("CompareResult{Files Are Equal:%s, differences=\n%s}", areEqual, difString);
    }
}