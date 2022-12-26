package me.elaamiri.queries.productQueries;

import lombok.Getter;

public class GetProductByIdQuery {

    @Getter
    private String id;

    public GetProductByIdQuery(String id) {
        this.id = id;
    }
}
