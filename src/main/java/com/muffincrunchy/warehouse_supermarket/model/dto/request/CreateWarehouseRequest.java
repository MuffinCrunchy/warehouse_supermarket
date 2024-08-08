package com.muffincrunchy.warehouse_supermarket.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateWarehouseRequest {

    private String name;
}
