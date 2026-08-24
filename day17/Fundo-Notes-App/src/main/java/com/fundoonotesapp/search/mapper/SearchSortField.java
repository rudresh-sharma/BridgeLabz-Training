package com.fundoonotesapp.search.mapper;

public enum SearchSortField {

    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt"),
    TITLE("title");

    private final String field;

    SearchSortField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}