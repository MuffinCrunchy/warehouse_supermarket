package com.muffincrunchy.warehouse_supermarket.service;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateWarehouseRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import org.springframework.data.domain.Page;

public interface WarehouseService {

    Page<Warehouse> getAll(PagingRequest pagingRequest);
    Warehouse getById(String id);
    Warehouse create(CreateWarehouseRequest request);
    Warehouse update(Warehouse request);
    void deleteById(String id);
    Warehouse getByName(String name);
}
