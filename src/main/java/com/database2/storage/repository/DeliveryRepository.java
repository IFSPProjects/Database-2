package com.database2.storage.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database2.storage.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{
    Optional<Delivery> findDeliveryByid(Integer id);
    Optional<List<Delivery>> findDeliveriesBydeliveryDate(Date date);
}
