package com.hyunjina.coffeeorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.OrderMenuEntity;
import com.hyunjina.coffeeorder.entity.OrderMenuId;
@Repository
public interface OrderMenuRepository  extends JpaRepository<OrderMenuEntity, OrderMenuId> {
}