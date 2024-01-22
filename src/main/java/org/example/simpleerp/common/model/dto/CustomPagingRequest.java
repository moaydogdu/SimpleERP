package org.example.simpleerp.common.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.simpleerp.common.model.CustomPaging;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public abstract class CustomPagingRequest {

    private CustomPaging pagination;

    public Pageable toPageable() {
        return PageRequest.of(
                Math.toIntExact(pagination.getPageNumber()),
                Math.toIntExact(pagination.getPageSize())
        );
    }

}
