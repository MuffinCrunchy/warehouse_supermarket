package com.muffincrunchy.warehouse_supermarket.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGoodsRequest {

    private String name;

    private Long price;

    private Integer stock;

    @JsonProperty("exp_date")
    private String expDate;

    @JsonProperty("warehouse_id")
    private String warehouseId;
}
