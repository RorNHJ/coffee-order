package com.hyunjina.coffeeorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.PointDetailEntity;
@Repository
public interface PointDetailRepository  extends JpaRepository<PointDetailEntity, Long> {
}