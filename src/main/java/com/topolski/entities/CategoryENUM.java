package com.topolski.entities;

public enum CategoryENUM {
    ROCK("Rock"),
    ALTERNATIVE("Alternative"),
    R_AND_B("R&B"),
    OTHER("Other");
    private final String categoryValue;
    CategoryENUM(final String value) {
        this.categoryValue = value;
    }
    public String getText() {
        return categoryValue;
    }
}
