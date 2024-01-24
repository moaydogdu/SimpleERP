package org.example.simpleerp.builder.dto;

import org.example.simpleerp.builder.BaseBuilder;
import org.example.simpleerp.common.model.CustomPaging;

public class CustomPagingBuilder extends BaseBuilder<CustomPaging> {
    public CustomPagingBuilder() {
        super(CustomPaging.class);
    }

    public CustomPagingBuilder withValidFields() {
        return this
                .withPageNumber(1)
                .withPageSize(5);
    }

    public CustomPagingBuilder withPageNumber(
            final Integer pageNumber
    ) {
        data.setPageNumber(pageNumber);
        return this;
    }

    public CustomPagingBuilder withPageSize(
            final Integer pageSize
    ) {
        data.setPageSize(pageSize);
        return this;
    }

    @Override
    public CustomPaging build() {
        return super.build();
    }
}
