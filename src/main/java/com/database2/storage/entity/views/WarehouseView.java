package com.database2.storage.entity.views;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.View;

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

import java.sql.Date;


@Entity
@Getter
@Setter
@Immutable
@Table(name = "view_warehouse")
public class WarehouseView {

    @Id
    @Column(name = "Inventory ID", nullable = false)
    private Integer id;

    @Column(name = "Product ID")
    private String productID;

    @Column(name = "Product Name")
    private String productName;

    @Column(name = "Product Description")
    private String productDescription;

    @Column(name = "Product State")
    private String productState;

    @Column(name = "State Description")
    private String stateDescription;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Register Date")
    private Date registerDate;    

}

/*
SELECT 
    wi.inventory_id AS 'Inventory ID',
    wi.product_id AS 'Product ID', 
	pr.name AS 'Product Name', 
	pr.description AS 'Product Description', 
	wi.state AS 'Product State', 
	st.description AS 'State Description', 
	wi.quantity AS 'Quantity',
	wi.register_date AS 'Register Date'
*/