package com.hyunjina.coffeeorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyunjina.coffeeorder.entity.MenuEntity;
@Repository
public interface MenuRepository  extends JpaRepository<MenuEntity, String> {
	
	/* 메뉴 사용여부(Y/N)에 따른 메뉴 목록 조회 */
	List<MenuEntity> findByUseYnOrderByMenuId(String useYn);
	
	/* 여러건의 메뉴Id로 조회 */
	List<MenuEntity> findByMenuIdIn(List<String> menuIdList);
	
}