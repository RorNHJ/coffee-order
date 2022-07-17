package com.hyunjina.coffeeorder.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.OrderEntity;
@Repository
public interface OrderRepository  extends JpaRepository<OrderEntity, String> {
	List<OrderEntity> findByOrdertDtBetween(LocalDateTime start, LocalDateTime end);
}