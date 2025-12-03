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
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
}

/*
CREATE TABLE Delivery (
delivery_id INT PRIMARY KEY IDENTITY(1,1),
product_id INT FOREIGN KEY REFERENCES Product(product_id) NOT NULL,
location_id INT FOREIGN KEY REFERENCES Location(location_id) NOT NULL,
client_id INT FOREIGN KEY REFERENCES Client(client_id) NOT NULL,
quantity INT NOT NULL,
delivery_date DATE DEFAULT(getdate())
 */
