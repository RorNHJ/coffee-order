package com.hyunjina.coffeeorder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.CustomerEntity;
import com.hyunjina.coffeeorder.entity.PointEntity;
@Repository
public interface CustomerRepository  extends JpaRepository<CustomerEntity, String> {
}