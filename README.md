# Warehouse Supermarket

## 1. Algorithm
Check it [here](https://github.com/MuffinCrunchy/warehouse_supermarket/tree/master/src/main/java/com/muffincrunchy/warehouse_supermarket/utils/algorithm)

## 2. Database
Check it [here](https://github.com/MuffinCrunchy/warehouse_supermarket/tree/master/src/main/java/com/muffincrunchy/warehouse_supermarket/utils/database)

## 3. API
### Warehouse
```
    <url>/api/v3/warehouses
```
* GET: Get All Warehouses
* POST: Create New Warehouse
* PUT: Update Existed Warehouse
```
    <url>/api/v3/warehouses/{id}
```
* GET: Get Specific Warehouse
* DELETE: Delete Specific Warehouse

### Goods
```
    <url>/api/v3/goods
```
* GET: Get All Goods
* POST: Create New Goods
* PUT: Update Existed Goods
```
    <url>/api/v3/warehouses/{id}
```
* GET: Get Specific Goods
* DELETE: Delete Specific Goods
```
    <url>/api/v3/warehouses/search?name=...&after-date=...
```
* GET: Get All Goods Where After Expiration Date
```
    <url>/api/v3/warehouses/search?name=...&before-date=...
```
* GET: Get All Goods Where Before Expiration Date
