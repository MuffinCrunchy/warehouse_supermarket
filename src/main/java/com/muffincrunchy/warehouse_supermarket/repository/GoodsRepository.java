package com.muffincrunchy.warehouse_supermarket.repository;

import com.muffincrunchy.warehouse_supermarket.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findByWarehouseIdAndExpDateBefore(String warehouseId, Date expDate);
    List<Goods> findByWarehouseIdAndExpDateAfter(String warehouseId, Date expDate);
}
