CREATE TABLE IF NOT EXISTS quantity_measurement_entity (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    measurement_type VARCHAR(50),

    operation_type VARCHAR(50),

    value1 DOUBLE,

    unit1 VARCHAR(50),

    value2 DOUBLE,

    unit2 VARCHAR(50),

    result BOOLEAN,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);