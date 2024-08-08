package com.muffincrunchy.warehouse_supermarket.controller;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateWarehouseRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.response.CommonResponse;
import com.muffincrunchy.warehouse_supermarket.model.dto.response.PagingResponse;
import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import com.muffincrunchy.warehouse_supermarket.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muffincrunchy.warehouse_supermarket.model.constant.ApiUrl.API_ID_PATH;
import static com.muffincrunchy.warehouse_supermarket.model.constant.ApiUrl.API_WH_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_WH_URL)
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Warehouse>>> getAllWarehouses(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String direction
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Warehouse> warehouses = warehouseService.getAll(pagingRequest);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(warehouses.getTotalPages())
                .totalElements(warehouses.getTotalElements())
                .page(warehouses.getPageable().getPageNumber()+1)
                .size(warehouses.getPageable().getPageSize())
                .hasNext(warehouses.hasNext())
                .hasPrevious(warehouses.hasPrevious())
                .build();
        CommonResponse<List<Warehouse>> response = CommonResponse.<List<Warehouse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(warehouses.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(API_ID_PATH)
    public ResponseEntity<CommonResponse<Warehouse>> getWarehouseById(@PathVariable("id") String id) {
        Warehouse warehouse = warehouseService.getById(id);
        CommonResponse<Warehouse> response = CommonResponse.<Warehouse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(warehouse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<Warehouse>> createWarehouse(@RequestBody CreateWarehouseRequest request) {
        Warehouse warehouse = warehouseService.create(request);
        CommonResponse<Warehouse> response = CommonResponse.<Warehouse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success save data")
                .data(warehouse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<CommonResponse<Warehouse>> updateWarehouse(@RequestBody Warehouse request) {
        Warehouse warehouse = warehouseService.update(request);
        CommonResponse<Warehouse> response = CommonResponse.<Warehouse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success update data")
                .data(warehouse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(API_ID_PATH)
    public ResponseEntity<CommonResponse<?>> createWarehouse(@PathVariable("id") String id) {
        warehouseService.deleteById(id);
        CommonResponse<Warehouse> response = CommonResponse.<Warehouse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success delete data")
                .build();
        return ResponseEntity.ok(response);
    }
}
