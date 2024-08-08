package com.muffincrunchy.warehouse_supermarket.repository;

import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {

    Warehouse findByName(String name);
}
