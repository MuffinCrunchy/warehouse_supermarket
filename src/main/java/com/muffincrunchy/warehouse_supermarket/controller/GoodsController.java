package com.muffincrunchy.warehouse_supermarket.controller;

import com.muffincrunchy.warehouse_supermarket.model.dto.request.CreateGoodsRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.request.PagingRequest;
import com.muffincrunchy.warehouse_supermarket.model.dto.response.CommonResponse;
import com.muffincrunchy.warehouse_supermarket.model.dto.response.PagingResponse;
import com.muffincrunchy.warehouse_supermarket.model.entity.Goods;
import com.muffincrunchy.warehouse_supermarket.model.entity.Warehouse;
import com.muffincrunchy.warehouse_supermarket.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muffincrunchy.warehouse_supermarket.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_GOODS_URL)
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Goods>>> getAllGoods(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "expDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String direction
            ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Goods> goods = goodsService.getAll(pagingRequest);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(goods.getTotalPages())
                .totalElements(goods.getTotalElements())
                .page(goods.getPageable().getPageNumber()+1)
                .size(goods.getPageable().getPageSize())
                .hasNext(goods.hasNext())
                .hasPrevious(goods.hasPrevious())
                .build();
        CommonResponse<List<Goods>> response = CommonResponse.<List<Goods>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(goods.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(API_ID_PATH)
    public ResponseEntity<CommonResponse<Goods>> getWarehouseById(@PathVariable("id") String id) {
        Goods goods = goodsService.getById(id);
        CommonResponse<Goods> response = CommonResponse.<Goods>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(goods)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<Goods>> createWarehouse(@RequestBody CreateGoodsRequest request) {
        Goods goods = goodsService.create(request);
        CommonResponse<Goods> response = CommonResponse.<Goods>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success save data")
                .data(goods)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<CommonResponse<Goods>> updateWarehouse(@RequestBody Goods request) {
        Goods goods = goodsService.update(request);
        CommonResponse<Goods> response = CommonResponse.<Goods>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success update data")
                .data(goods)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(API_ID_PATH)
    public ResponseEntity<CommonResponse<?>> createWarehouse(@PathVariable("id") String id) {
        goodsService.deleteById(id);
        CommonResponse<Warehouse> response = CommonResponse.<Warehouse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success delete data")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(API_SEARCH_PATH)
    public ResponseEntity<CommonResponse<List<Goods>>> getAllGoodsAfterExp(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "expDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String direction,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "after-date") String date
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Goods> goods = goodsService.afterExp(pagingRequest, name, date);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(goods.getTotalPages())
                .totalElements(goods.getTotalElements())
                .page(goods.getPageable().getPageNumber()+1)
                .size(goods.getPageable().getPageSize())
                .hasNext(goods.hasNext())
                .hasPrevious(goods.hasPrevious())
                .build();
        CommonResponse<List<Goods>> response = CommonResponse.<List<Goods>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(goods.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(API_SEARCH_PATH)
    public ResponseEntity<CommonResponse<List<Goods>>> getAllGoodsBeforeExp(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "expDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String direction,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "before-date") String date
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Goods> goods = goodsService.beforeExp(pagingRequest, name, date);
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(goods.getTotalPages())
                .totalElements(goods.getTotalElements())
                .page(goods.getPageable().getPageNumber()+1)
                .size(goods.getPageable().getPageSize())
                .hasNext(goods.hasNext())
                .hasPrevious(goods.hasPrevious())
                .build();
        CommonResponse<List<Goods>> response = CommonResponse.<List<Goods>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success fetch data")
                .data(goods.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
