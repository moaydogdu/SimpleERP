package org.example.simpleerp.common.model.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.simpleerp.common.model.CustomPage;

import java.util.List;

@Getter
@Builder
public class CustomPagingResponse<T> {

    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElementCount;

    private Integer totalPageCount;

    public static class CustomPagingResponseBuilder<T> {
        public <C> CustomPagingResponseBuilder<T> of(
                final CustomPage<C> customPage
        ) {
            return CustomPagingResponse.<T>builder()
                    .pageNumber(customPage.getPageNumber())
                    .pageSize(customPage.getPageSize())
                    .totalElementCount(customPage.getTotalElementCount())
                    .totalPageCount(customPage.getTotalPageCount());
        }
    }
}
