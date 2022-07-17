package com.hyunjina.coffeeorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.SequenceEntity;
@Repository
public interface SequenceRepository  extends JpaRepository<SequenceEntity, String> {
}