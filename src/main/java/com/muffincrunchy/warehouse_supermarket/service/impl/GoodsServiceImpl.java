package com.muffincrunchy.warehouse_supermarket.service.impl;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateGoodsRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.entity.Goods;
import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import com.muffincrunchy.warehouse_supermarket.repository.GoodsRepository;
import com.muffincrunchy.warehouse_supermarket.service.GoodsService;
import com.muffincrunchy.warehouse_supermarket.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;
    private final WarehouseService warehouseService;

    @Override
    public Page<Goods> getAll(PagingRequest pagingRequest) {
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), pagingRequest.getSortBy());
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        return goodsRepository.findAll(pageable);
    }

    @Override
    public Goods getById(String id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public Goods create(CreateGoodsRequest request) {
        Warehouse warehouse = warehouseService.getById(request.getWarehouseId());
        Goods goods = Goods.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .expDate(Date.valueOf(request.getExpDate()))
                .warehouse(warehouse)
                .build();
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public Goods update(Goods request) {
        getById(request.getId());
        return goodsRepository.saveAndFlush(request);
    }

    @Override
    public void deleteById(String id) {
        getById(id);
        goodsRepository.deleteById(id);
    }

    @Override
    public Page<Goods> afterExp(PagingRequest pagingRequest, String warehouseName, String expDate) {
        Warehouse warehouse = warehouseService.getByName(warehouseName);
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), pagingRequest.getSortBy());
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        List<Goods> goods = goodsRepository.findByWarehouseIdAndExpDateAfter(warehouse.getId(), Date.valueOf(expDate));
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), goods.size());
        return new PageImpl<>(goods.subList(start, end), pageable, goods.size());
    }

    @Override
    public Page<Goods> beforeExp(PagingRequest pagingRequest, String warehouseName, String expDate) {
        Warehouse warehouse = warehouseService.getByName(warehouseName);
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), pagingRequest.getSortBy());
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        List<Goods> goods = goodsRepository.findByWarehouseIdAndExpDateBefore(warehouse.getId(), Date.valueOf(expDate));
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), goods.size());
        return new PageImpl<>(goods.subList(start, end), pageable, goods.size());
    }
}
