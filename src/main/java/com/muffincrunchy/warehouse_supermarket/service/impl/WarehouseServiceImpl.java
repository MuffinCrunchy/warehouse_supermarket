package com.muffincrunchy.warehouse_supermarket.service.impl;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateWarehouseRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import com.muffincrunchy.warehouse_supermarket.repository.WarehouseRepository;
import com.muffincrunchy.warehouse_supermarket.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getAll(PagingRequest pagingRequest) {
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), pagingRequest.getSortBy());
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        return warehouseRepository.findAll(pageable);
    }

    @Override
    public Warehouse getById(String id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse create(CreateWarehouseRequest request) {
        Warehouse warehouse = Warehouse.builder()
                .name(request.getName())
                .build();
        return warehouseRepository.saveAndFlush(warehouse);
    }

    @Override
    public Warehouse update(Warehouse request) {
        getById(request.getId());
        return warehouseRepository.saveAndFlush(request);
    }

    @Override
    public void deleteById(String id) {
        getById(id);
        warehouseRepository.deleteById(id);
    }

    @Override
    public Warehouse getByName(String name) {
        return warehouseRepository.findByName(name);
    }
}
