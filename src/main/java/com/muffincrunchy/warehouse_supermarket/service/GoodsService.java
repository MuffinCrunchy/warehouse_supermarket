package com.muffincrunchy.warehouse_supermarket.service;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateGoodsRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.entity.Goods;
import org.springframework.data.domain.Page;

public interface GoodsService {

    Page<Goods> getAll(PagingRequest pagingRequest);
    Goods getById(String id);
    Goods create(CreateGoodsRequest request);
    Goods update(Goods request);
    void deleteById(String id);
    Page<Goods> afterExp(PagingRequest pagingRequest, String warehouseName, String expDate);
    Page<Goods> beforeExp(PagingRequest pagingRequest, String warehouseName, String expDate);
}
