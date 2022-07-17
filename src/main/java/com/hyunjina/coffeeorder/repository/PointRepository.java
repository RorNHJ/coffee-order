package com.hyunjina.coffeeorder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.PointEntity;
@Repository
public interface PointRepository  extends JpaRepository<PointEntity, String> {
	
	/* 고객id로 포인트 조회*/
	Optional<PointEntity> findOneByCustomerId(String customerId);
}