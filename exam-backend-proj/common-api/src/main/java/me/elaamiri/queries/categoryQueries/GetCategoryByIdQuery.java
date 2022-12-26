package me.elaamiri.queries.categoryQueries;

import lombok.Getter;

public class GetCategoryByIdQuery {

    @Getter
    private String id;

    public GetCategoryByIdQuery(String id) {
        this.id = id;
    }
}
