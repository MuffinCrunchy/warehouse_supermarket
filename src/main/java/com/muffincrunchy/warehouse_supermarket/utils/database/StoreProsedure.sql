-- Function
CREATE OR REPLACE FUNCTION list_goods (total INT = 24, page INT = 1)
RETURNS SETOF goods AS $func$
DECLARE
    query TEXT;
BEGIN
    query := 'SELECT' ||
                 'warehouses.id AS warehouse_id,' ||
                 'warehouses.name AS warehouse_name,' ||
                 'goods.id AS goods_id,' ||
                 'goods.name AS goods_name,' ||
                 'goods.price AS price,' ||
                 'goods.stock AS stock,' ||
                 'goods.exp_date AS exp_date' ||
             'FROM warehouse' ||
             'JOIN goods' ||
             'ON goods.id = wareouses.id' ||
             'ORDER BY goods.exp_date' ||
             'LIMIT $1' ||
             'OFFSET $2';
    RETURN QUERY EXECUTE query
    USING total, total*(page-1);
END;
$func$ LANGUAGE plpsql;

-- Execute
SELECT * FROM list_goods(24, 1)