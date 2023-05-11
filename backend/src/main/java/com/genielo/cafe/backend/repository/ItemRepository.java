package com.genielo.cafe.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genielo.cafe.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByType(String type);
}
