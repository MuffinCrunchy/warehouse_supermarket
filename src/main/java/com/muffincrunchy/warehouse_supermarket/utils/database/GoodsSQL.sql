CREATE TABLE IF NOT EXISTS goods (
    id uuid PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    price BIGINT NOT NULL,
    stock INT NOT NULL,
    exp_date DATE NOT NULL,
    warehouse_id uuid REFERENCES warehouses (id)
)