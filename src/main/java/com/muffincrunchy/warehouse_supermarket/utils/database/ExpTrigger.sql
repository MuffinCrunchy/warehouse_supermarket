-- Function
CREATE OR REPLACE FUNCTION select_goods_after_insert()
RETURNS TRIGGER AS $$
BEGIN
    RETURN QUERY
        SELECT *
        FROM goods
        WHERE goods.exp_date > CURRENT_DATE;
END;
$$ LANGUAGE plpsql;

-- Activate
CREATE TRIGGER after_insert_goods_trigger
AFTER INSERT ON goods
FOR EACH ROW
EXECUTE FUNCTION select_goods_after_insert();