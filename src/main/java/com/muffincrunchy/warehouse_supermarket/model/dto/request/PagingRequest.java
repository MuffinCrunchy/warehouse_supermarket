package com.muffincrunchy.warehouse_supermarket.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingRequest {

    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
