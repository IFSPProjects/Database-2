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
@Table(name = "view_delivery")
public class DeliveryView {

    @Id
    @Column(name = "Delivery ID", nullable = false)
    private Integer id;

    @Column(name = "Product Delivered")
    private String productName;

    @Column(name = "Quantity Delivered")
    private Integer quantity;

    @Column(name = "Date of Delivery")
    private Date deliveryDate;

    @Column(name = "Client Associated")
    private String clientName;

    @Column(name = "Client CPF")
    private String CPF;

    @Column(name = "Location Sent")
    private String locationName;

    @Column(name = "Location Details")
    private String locationDetails;    



}



/*
SELECT dl.delivery_id AS 'Delivery ID',
	pr.name AS 'Product Delivered',
	dl.delivery_date AS 'Date of Delivery',
	dl.quantity AS 'Quantity Delivered',
	cl.name AS 'Client Associated',
	cl.cpf AS 'Client CPF',
	lc.name AS 'Location Sent',
	lc.description AS 'Location Details'
*/