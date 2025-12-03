package com.database2.storage.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "warehouse_inventory")
public class WarehouseInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "register_date")
    private Date registerDate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "state", nullable = false)
    private State state;
    
}

/*
CREATE TABLE WarehouseInventory (
inventory_id INT PRIMARY KEY IDENTITY(1,1),
product_id INT FOREIGN KEY REFERENCES Product(product_id) NOT NULL,
quantity INT DEFAULT 1,
state VARCHAR(32) FOREIGN KEY REFERENCES State(state_id) DEFAULT 'Regular',
register_date DATE DEFAULT(getdate())
CONSTRAINT WarehouseInventoryUniqueConstraint UNIQUE(product_id, state) 
-- Unique: It makes sure only new pairs between a product and its state can be registered at the database.
)
*/
