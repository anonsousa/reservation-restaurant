CREATE TABLE tbl_reserves (
    id_reserve BIGSERIAL PRIMARY KEY,
    spot_id BIGINT NOT NULL,
    reserve_owner VARCHAR(100) NOT NULL,
    reserve_cpf VARCHAR(14) UNIQUE,
    people_number INTEGER NOT NULL,
    reserve_status VARCHAR(50),
    reserve_date DATE,
    reserve_effective_date TIMESTAMP,
    notes TEXT,
    CONSTRAINT fk_spot FOREIGN KEY (spot_id) REFERENCES tbl_spots(id_table)
);