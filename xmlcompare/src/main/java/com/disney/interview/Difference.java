package com.disney.interview;

public class Difference {
    private String field;
    private String left;
    private String right;

    public Difference(String field, String left, String right) {
        this.field = field;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("Difference{field=%s, left='%s', right='%s'}", field, left, right);
    }
}